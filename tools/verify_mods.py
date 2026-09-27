"""Verifies the mod profiles: every id against the en_us files of all studied mod versions, and every
craft_item/smelt_item target against the mod's own recipe data (Farmer's Delight 1.20 and Create
mc1.20.1 sources, sparse clones in the research dir). Usage: python3 tools/verify_mods.py [research_dir]"""
import json, os, sys, glob
R = sys.argv[1] if len(sys.argv) > 1 else '/home/user/research'
RES = os.path.join(os.path.dirname(__file__), '..', 'justquests-generator-v2', 'src', 'main', 'resources', 'justquests_genv2')
LANGS = {'farmersdelight': ['fd-1.18.2.json', 'fd-1.19.json', 'fd-1.20.json', 'fd-1.20.4.json', 'fd-1.21.json', 'fd-26.1.json'],
         'create': ['create-0.5.1-1.18.json', 'createfabric-1.18.json', 'create-0.5.1-1.19.json', 'create-1.20.1.json',
                    'createfabric-1.20.1.json', 'create-1.21.1.json', 'createfabric-1.21.1.json']}
SRC = {'farmersdelight': 'fd-1.20/src', 'create': 'create-1.20.1/src'}

def lang_ids(f):
    j = json.load(open(f'{R}/lang/{f}'))
    s = set()
    for k in j:
        p = k.split('.')
        if len(p) >= 3 and p[0] in ('item', 'block', 'entity'):
            s.add(p[1] + ':' + p[2])
    return s

def recipes(src):
    out = {'craft': set(), 'smelt': set()}
    for p in glob.glob(f'{R}/{src}/**/recipes/**/*.json', recursive=True):
        try:
            j = json.load(open(p))
        except Exception:
            continue
        t = j.get('type', '')
        r = j.get('result')
        rid = r if isinstance(r, str) else (r.get('item') or r.get('id')) if isinstance(r, dict) else None
        if not rid or 'conditions' in j:
            continue
        if t.startswith('minecraft:crafting_shaped') or t.startswith('minecraft:crafting_shapeless'):
            out['craft'].add(rid)
        elif t in ('minecraft:smelting', 'minecraft:blasting', 'minecraft:smoking'):
            out['smelt'].add(rid)
    return out

problems = []
for pid in ('farmersdelight', 'create'):
    prof = json.load(open(f'{RES}/catalog/profiles/{pid}.json'))
    langs = {f: lang_ids(f) for f in LANGS[pid]}
    rec = recipes(SRC[pid])
    ids = []
    n = 0
    for e in prof['entries']:
        for t in e['targets']:
            n += 1
            ids.append(t['id'])
            if t['type'] in ('craft_item', 'smelt_item'):
                key = 'craft' if t['type'] == 'craft_item' else 'smelt'
                if t['id'] not in rec[key] and not t['id'].startswith('minecraft:'):
                    problems.append(f"{pid} {e['key']} {t['type']} {t['id']}: no {key} recipe in the mod data")
    for r in prof.get('rewards', {}).get('items', []):
        ids.append(r['id'])
    for i in sorted(set(ids)):
        if i.startswith('minecraft:'):
            continue
        missing = [f for f, s in langs.items() if i not in s]
        if len(missing) == len(langs):
            problems.append(f"{pid} {i}: in NO version")
        elif missing:
            print(f"  note {pid} {i}: missing in {missing}")
    print(f"{pid}: {n} targets checked")
print(f"{len(problems)} problems"); [print('  P', p) for p in problems]

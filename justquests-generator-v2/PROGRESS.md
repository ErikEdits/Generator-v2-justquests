# Work progress (internal, not part of the ZIP)

Spec: `../docs/generator-v2-spec.md`. Branch: `claude/blissful-ramanujan-n7j46b`.
Auto-resume routine: `trig_01VkvYQ75m7uPB1RPLGp5Diq` (every 2 h) — delete when done or after 2026-09-28 10:00 UTC.

## Decisions
- Mods: **Farmer's Delight** (MIT; `farmersdelight`, Forge/NeoForge + Fabric Refabricated)
  and **Create** (code MIT, assets ARR — we only reference ids; `create`, Forge/NeoForge + Create Fabric).
  Rejected for license: Biomes O' Plenty (ARR), Supplementaries (custom ARR), Friends&Foes (CC BY-NC-ND).
- Modrinth is blocked in this environment → research via GitHub sources (git ls-remote / raw).
- Deliverable: project in `justquests-generator-v2/`, ZIP built at the end; no PR.

## Milestones
- [x] 1 Gradle skeleton + api package
- [x] 2 Mod research (FD 1.20/1.21 + Create 0.5.1/6.0 lang/recipes/worldgen checked via git sparse clones in /home/user/research — not committed)
- [x] 3 Data files (generated from tools/*.py: vanilla 291 targets, FD 98, Create 49)
- [x] 4 Core engine — internal/{catalog,gen,state,stats,util}, Core.java, Generation.java, facade
- [x] 5 Tests (17 groups + stats/calibration) — 70 tests green
- [x] 6 Docs, samples, reference adapter, ZIP (dist/justquests-generator-v2.zip)

## Log
- 2026-09-27 ~09:00 UTC: started, spec read, mods chosen.
- 2026-09-27 ~10:00 UTC: API + core engine compiled. Next: data files (vanilla.json, profiles, rewards, templates, themes, balance, tags), then tests.
- 2026-09-27 ~10:45 UTC: data + tests done, build green. Next: review pass, samples (SampleWriter), docs, reference adapter, ZIP.
- 2026-09-27 ~13:00 UTC: INTEGRATION.md, DESIGN.md, MODS.md written. Next: README.md, CHANGELOG-snippet.md, samples/README.md, reference adapter (reference-adapter/neoforge-1.21.1, REFERENCE ONLY), §19 checklist pass, ZIP (./gradlew deliverableZip), send ZIP, delete trigger trig_01VkvYQ75m7uPB1RPLGp5Diq, German summary.
- User note (13:00): size of the deliverable/state does not matter ("20 MB, 300 MB egal").
- 2026-09-27 ~13:10 UTC: §19 acceptance checklist complete:
  build green on clean clone and from the unzipped ZIP (71 tests), --release 17, purity test green,
  facade/api per §8 + javadoc (doclint:all clean), no forbidden types/commands/tags, 17 test groups,
  vanilla 291 targets / FD 98 / Create 49, themes FD 7 / Create 5, v1-bug test, samples with explain,
  all docs in English, no secrets/network, no copied code/assets. DONE.

## Extension phase (user: ">= 3 more hours, ZIP up to 5 MB") — started 2026-09-27 13:36 UTC, work until >= 16:40 UTC
Routine: "Generator v2 extension auto-resume" (hourly) — delete when done.
- [x] E1 Content: vanilla catalog 458 targets (verified by tools/verify_vanilla.py vs misode/mcmeta registries+recipes across 17 MC versions; since-guard for experimental leaks), 83 reward items, title/hint variety (wording variants, family nouns)
- [x] E2 Themes: vanilla 30, FD 12, Create 10 (+rewards 83 vanilla items, effects, messages)
- [x] E3 Mod profiles: FD 121, Create 70 targets; tools/verify_mods.py (ids vs all lang files + recipe types) 0 problems
- [x] E4 Tools: `./gradlew catalogReport` -> samples/catalog-report.md (every target: types, count range, minutes, difficulties); `./gradlew simulate` -> samples/simulation/ (14 days of rotations with claims, stats text)
- [x] E5 Robustness tests: HostFuzzTest (random TriStates/ids/exceptions/hostile names, broken world/caps/validator), ClaimsPropertyTest (12 configs x 300 random ops vs a model, coverage-checked), OldVersionTest (11 versions 1.18.2..26.1, since-guard), StateFuzzTest (250 mutated state files, 3000 mutated quests, removed-mod restart). Fixes found: SchemaCheck NPE on reward without type; stored quests are now re-validated at start (dropped + reported dead + set topped up); host display names are cleaned (formatting codes, braces, untranslated keys, >40 chars)
- [x] E6 Reference adapters (REFERENCE ONLY): fabric-1.21.1, forge-1.20.1, legacy-1.18.2 (Forge+Fabric), neoforge-1.21.4-plus notes, index README with the API matrix 1.18.2..1.21.11 (verified against the real trees: registries, ResourceLocation.parse from 1.21, network package presence, chat API); status() now names Minecraft version + loader
- [x] E8 Third mod profile: Mekanism (MIT), 31 targets (ores, raw metals, ingots, fluorite, salt, first machines, hazmat gear, hard-only machines), 9 rewards, 4 themes, 3 tag concepts; verified against Mekanism 1.18.x/1.19.x/1.20.x/1.21.x lang + recipe data (tools/verify_mods.py, 0 problems); explicit English names for inverted ids (dedicated servers have no mod lang files); samples/mc-1.18.2 added
- [x] E9 Fourth mod profile: The Twilight Forest (LGPL-2.1), 28 targets in its own dimension (unlock: 25 % online with twilightforest:root or day 12; portal trip, wood, forage, venison, breeding, monsters, Hard-only bosses Naga/Lich, ironwood, magic maps), 6 rewards (tier 3+), 4 themes; verified vs TF 1.18.x/1.19.x/1.20.1/1.21.1 lang + 1.20.1/1.21.1 recipes, loot tables, tempt tags, dimension file (0 problems); plural fixes (osmium, tin, uranium, venison, liveroot, ironwood, meef, sculk, nylium, lichen, steel) + EnglishTest
- [x] E10 Fifth mod profile: Botania (Botania License, attribution Vazkii), 17 targets (mystical flowers, petals, lexicon, apothecary, flower pouch, shimmering mushrooms; Pure Daisy work Normal+/Hard), 4 rewards, 2 themes; verified vs Botania 1.18.x/1.19.x/1.20.x lang + 1.20.x recipes/loot (0 problems). TextQualityTest (3600 quests, 0 findings). Per-mod guarantee capped at maxPerModShare (0.5) of the set with a rotating subset when more mods are active than half the set (relaxation 'per_mod'); literal for N >= 2 x mods
- [ ] E7 Docs/samples refresh, README numbers, rebuild ZIP, send, delete routine, German summary

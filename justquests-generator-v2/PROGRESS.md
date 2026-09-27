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
- [ ] 3 Data files
- [~] 4 Core engine (compiles; untested) — internal/{catalog,gen,state,stats,util}, Core.java, facade
- [ ] 5 Tests (17 groups)
- [ ] 6 Docs, samples, reference adapter, ZIP

## Log
- 2026-09-27 ~09:00 UTC: started, spec read, mods chosen.
- 2026-09-27 ~10:00 UTC: API + core engine compiled. Next: data files (vanilla.json, profiles, rewards, templates, themes, balance, tags), then tests.

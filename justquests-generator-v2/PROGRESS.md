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
- [ ] 6 Docs, samples, reference adapter, ZIP

## Log
- 2026-09-27 ~09:00 UTC: started, spec read, mods chosen.
- 2026-09-27 ~10:00 UTC: API + core engine compiled. Next: data files (vanilla.json, profiles, rewards, templates, themes, balance, tags), then tests.
- 2026-09-27 ~10:45 UTC: data + tests done, build green. Next: review pass, samples (SampleWriter), docs, reference adapter, ZIP.
- 2026-09-27 ~13:00 UTC: INTEGRATION.md, DESIGN.md, MODS.md written. Next: README.md, CHANGELOG-snippet.md, samples/README.md, reference adapter (reference-adapter/neoforge-1.21.1, REFERENCE ONLY), §19 checklist pass, ZIP (./gradlew deliverableZip), send ZIP, delete trigger trig_01VkvYQ75m7uPB1RPLGp5Diq, German summary.
- User note (13:00): size of the deliverable/state does not matter ("20 MB, 300 MB egal").

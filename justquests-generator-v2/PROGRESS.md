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
- [~] E1 Content: vanilla catalog 463 targets DONE (verified by tools/verify_vanilla.py vs misode/mcmeta registries+recipes); rewards/titles pending — (more ores/blocks/mobs/crafts/foods/smelts/places/breeds), more reward items/effects, more title/hint variety
- [x] E2 Themes: vanilla 30, FD 12, Create 10 (+rewards 83 vanilla items, effects, messages)
- [x] E3 Mod profiles: FD 122, Create 70 targets; tools/verify_mods.py (ids vs all lang files + recipe types) 0 problems
- [ ] E4 Tools: `./gradlew catalogReport` -> samples/catalog-report.md (every target: types, count range, minutes, difficulties); `./gradlew simulate` -> samples/simulation/ (14 days of rotations with claims, stats text)
- [ ] E5 Robustness tests: host fuzz (random TriStates, missing ids, throwing host), claims property test (random op sequences + invariants), old-version test (1.18.2: ids with since > 1.18.2 missing)
- [ ] E6 Reference adapters for other API eras (Fabric 1.21.1, Forge 1.20.1, Forge/Fabric 1.18.2, NeoForge 1.21.4+ notes) — REFERENCE ONLY, from the real mod trees in /home/user/erikedits/justquests
- [ ] E7 Docs/samples refresh, README numbers, rebuild ZIP, send, delete routine, German summary

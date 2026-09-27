# JustQuests — Generator v2 (core)

A version-neutral, procedural quest generator for the JustQuests mod: plain Java 17 + Gson 2.8.8,
no Minecraft or loader classes. The identical core is copied into all 34 builds of the mod; each
build adds a thin host adapter (see `INTEGRATION.md`).

What it does

- Generates a shared set of quests every 12 hours (real clock, catch-up after downtime), with no
  repeats within 6 days.
- **Achievable by construction:** every target passes a 7-step pipeline (type supported, profile
  active, id exists on this build, hook-compatible on all loaders, tier/progression, history, the
  mod's real codec).
- **Effort-based counts and value-based rewards:** a catalog of 291 vanilla targets with minutes per
  unit, biome/tool/dimension modifiers, nice round counts, rewards worth the time spent.
- **Varied sets:** ten objective types, 14 vanilla themes + 12 mod themes, at most one quest per
  family, type share cap, a quick quest in every set, graceful rule relaxation.
- **Exclusive claiming** (first come, first served), **one active generated quest per player**,
  claimed quests survive rotations until finished (the v1 bug is fixed).
- **Difficulty** Easy / Normal / Hard, **progression awareness** (Nether/End unlock per world),
  **explainability** (`explain`), anonymous **test-phase statistics** and optional
  **self-calibration**.
- **Mod profiles** for Farmer's Delight (98 targets, 7 themes) and Create (49 targets, 5 themes),
  active only when the mod is installed; more mods are a data-only addition.

## Build and test

Requirements: JDK 17 or newer (the build emits Java 17 bytecode via `options.release = 17`).

```
./gradlew build          # compile, run all tests (~40 s), build jars
./gradlew test           # tests only
./gradlew samples        # regenerate samples/ (seeds 1-3 x difficulty, vanilla and modded, with explain output)
./gradlew javadoc        # API documentation into build/docs/javadoc
./gradlew deliverableZip # build/dist/justquests-generator-v2.zip
```

Dependencies: `compileOnly com.google.code.gson:gson:2.8.8` (Minecraft provides Gson at runtime);
tests use Gson 2.8.8 and JUnit 5. Nothing else is on the main classpath.

## File tour

```
README.md                this file
INTEGRATION.md           exact wiring contract for the mod (host adapter, call order, settings, commands)
DESIGN.md                algorithms, formulas, data model, tuning guide, assumptions, limitations
MODS.md                  the two chosen mods: selection, licences, coverage, included/excluded content
CHANGELOG-snippet.md     player-facing release notes
build.gradle, settings.gradle, gradlew, gradlew.bat, gradle/wrapper/

src/main/java/com/erikedits/justquests/generator/v2/
  QuestGeneratorV2.java        the facade (the only class the mod calls)
  api/                         host interfaces (GeneratorHost, ContentView, WorldContext, StateStore,
                               QuestValidator, HostCapabilities, GenLog) and result types
  internal/                    implementation (free to change): Core, Generation, catalog/, gen/,
                               state/, stats/, util/

src/main/resources/justquests_genv2/
  catalog/vanilla.json         vanilla content model (135 entries, 291 targets)
  catalog/profiles/            farmersdelight.json, create.json, index.json
  rewards.json                 reward items/effects/loot tables with values
  templates.json               English titles, phrases, hints
  themes.json                  multi-objective archetypes
  balance.json                 every tunable number
  tags.json                    tag concepts with per-loader candidates

src/test/java/com/erikedits/justquests/generator/v2/
  FakeHost.java                configurable fake host with a fixed clock
  *Test.java                   the 17 test groups of the specification (+ stats/calibration)
  SampleWriter.java            writes samples/

reference-adapter/neoforge-1.21.1/   REFERENCE ONLY — NOT COMPILED: a complete GeneratorHost for NeoForge 1.21.1
samples/                     generated example sets with explain output (see samples/README.md)
```

## Using it (short version)

```java
QuestGeneratorV2 gen = new QuestGeneratorV2(host, GeneratorConfig.builder()
        .difficulty(Difficulty.NORMAL).questsPerCycle(5).build());
StartResult start = gen.startWithHolders(activeGeneratedQuestsByPlayer);  // or start(Map<String, UUID>)
register(gen.servedQuests());                                              // questId -> quest JSON

RotationResult r = gen.tick();                  // every ~5 minutes; re-register when r.changed()
ClaimResult c = gen.tryClaim(questId, player);   // in /quest accept, after the mod's own checks
gen.onAbandon(questId, player);                  // /quest abandon
gen.onComplete(questId, player);                 // after rewards were granted
String why = gen.explain(questId);               // OP debugging
gen.stop();                                      // server stopping
```

Everything is single-threaded (server thread), deterministic for identical inputs, and never throws
during normal operation. Details: `INTEGRATION.md`.

## Licence

Written for JustQuests (LGPL-3.0-only). The mod profiles contain only ids of the referenced mods (see
`MODS.md`); no code or assets of other mods are included.

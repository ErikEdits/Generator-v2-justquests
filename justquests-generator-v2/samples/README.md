# Samples

Produced by `./gradlew samples` (`src/test/java/.../SampleWriter.java`) with the `FakeHost`:

| Setting | Value |
|---|---|
| World seeds | 1, 2, 3 |
| Difficulties | easy, normal, hard |
| Quests per set | 10 |
| Clock | 2026-09-27 12:00 UTC (cycle 1790510400), zone UTC |
| World | game day 30, 2 players online, 100 % have entered the Nether, 50 % the End (so Hard may use Nether/End content) |
| Host answers | every TriState `UNKNOWN`, English names `null`, stack sizes unknown (the hardest case for the core) |
| `vanilla/` | no mods loaded |
| `modded/` | Farmer's Delight and Create loaded, all their ids exist; default modded share 0.35 |

Files per set:

- `seed<N>-<difficulty>.json` — exactly what `servedQuests()` returns: quest id → quest JSON (§6
  format), in serve order (`sort` = estimated minutes).
- `seed<N>-<difficulty>.explain.txt` — `explain()` for every quest: effort per unit and modifiers,
  target time and range, tier and unlock reason, family, reward values vs. budget, rejected
  alternatives.

The generator is deterministic: running `./gradlew samples` again reproduces these files byte for byte.

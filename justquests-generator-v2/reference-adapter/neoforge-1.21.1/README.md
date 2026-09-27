# Reference host adapter — NeoForge 1.21.1

**REFERENCE ONLY — NOT COMPILED.** These files are not part of the Gradle build of the core (the core
must not see Minecraft classes, and this environment has no Minecraft toolchain). They show a
complete `GeneratorHost` for the NeoForge 1.21.1 tree of JustQuests and the wiring glue, written
against that tree's classes (`JustQuests.LOG`, `QuestManager`, `Quest.CODEC`, `QuestNetwork`,
`WorldQuestStore`, `WorldSettings`). Copy them into `neoforge/1.21.1/src/main/java/com/erikedits/justquests/generator/`,
compile, and adapt per build.

| File | Role |
|---|---|
| `GenV2Host.java` | `GeneratorHost` + all six views (registries, tags, recipes, advancements, world files, codec, capabilities, log) |
| `GenV2.java` | Holder of the one instance per server + the glue from `INTEGRATION.md` §3 (start, tick, register, expired claims, config from settings) |

Per-version notes for porting (see also spec §18):

- 1.18.2–1.19.2: `Registry.ITEM` instead of `BuiltInRegistries.ITEM`; tags via `Registry.ITEM.getTag(TagKey)`;
  seed via `server.getWorldData().worldGenSettings().seed()`; `new ResourceLocation(ns, path)`.
- ≤ 1.20.1: recipes are `Recipe<?>` (no `RecipeHolder`); `getResultItem(RegistryAccess)` from 1.19.4,
  `getResultItem()` before. If in doubt, return `TriState.UNKNOWN` from the recipe methods.
- ≤ 1.20.4: `item.getMaxStackSize()`; 1.20.5+: `item.getDefaultMaxStackSize()`.
- ≤ 1.20.1: `server.getAdvancements().getAdvancement(rl)` returns `Advancement`.
- 1.21.2+: `RecipeManager` was reworked (recipes are synced differently, `getAllRecipesFor` changed);
  returning `UNKNOWN` there is fine — the catalog carries the truth.
- Fabric: `FabricLoader.getInstance().isModLoaded(id)`, lifecycle via `ServerLifecycleEvents`, same Mojmap names.

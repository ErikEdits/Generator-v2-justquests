# MODS — the two supported mods

Generator v2 ships data profiles for **Farmer's Delight** and **Create**. A profile only references
item/block/entity **ids** (facts); no code, textures, models, recipes or other assets of either mod
are bundled, copied or depended on. The profiles activate only when the mod is installed; every id is
existence-checked at runtime.

Research note: Modrinth (`modrinth.com`, `api.modrinth.com`) and CurseForge were blocked by this
environment's network policy. All facts below come from the mods' public **GitHub source
repositories** (license files, `gradle.properties`, generated recipe/loot/worldgen data and `en_us`
language files of every release branch), read in September 2026. Download figures are approximate
and from general knowledge; everything that could not be verified is marked as such.

Contents

1. [Selection](#1-selection)
2. [Farmer's Delight](#2-farmers-delight)
3. [Create](#3-create)
4. [How the ids were verified](#4-how-the-ids-were-verified)

---

## 1. Selection

Criteria from the specification (§14.1) plus the maintainer's explicit request: **open source with a
licence that causes no problems**. Scoring 0–3 per criterion:

| Mod | Popularity & health | Loader/version coverage | Quest-friendliness (our hooks) | Stable ids | License (code) | Verdict |
|---|---|---|---|---|---|---|
| **Farmer's Delight** (+ Refabricated) | 3 — one of the most downloaded content mods, active | 3 — Forge, NeoForge, Fabric (Refabricated, same namespace) | 3 — crops to collect, wild plants to mine, crafting-table food, smelting, many foods to eat | 3 | **MIT** | **chosen** |
| **Create** (+ Create Fabric) | 3 — top technology mod, active | 2 — Forge 1.18.2–1.20.1, NeoForge 1.21.1, Fabric 1.18.2–1.20.1 (same namespace) | 2 — ores, stone layers, many crafting-table parts; machine outputs are not countable | 3 | **MIT** (assets ARR — we use no assets) | **chosen** |
| Biomes O' Plenty | 3 | 3 | 2 — mostly blocks/wood | 3 | All Rights Reserved | rejected (licence) |
| Supplementaries | 3 | 3 | 2 | 2 | custom "Supplementaries Team License", All Rights Reserved | rejected (licence) |
| Friends & Foes | 2 | 3 | 2 | 3 | CC BY-NC-ND 4.0 | rejected (licence) |
| Alex's Mobs | 3 | 1 — Forge only, ends at 1.20.1 | 3 | 3 | no licence file found | rejected (coverage, licence unclear) |
| Twilight Forest | 3 | 1 — Forge/NeoForge only | 3 | 2 | LGPL | rejected (no Fabric) |
| The Aether | 2 | 1 — Forge/NeoForge only | 2 | 2 | LGPL-3.0 | rejected (no Fabric) |
| Mekanism | 3 | 1 — Forge/NeoForge only | 2 | 3 | MIT | rejected (no Fabric) |
| Naturalist | 2 | 2 | 2 — animals | 2 | split licence (resources restricted) | rejected |
| Oh The Biomes We've Gone | 2 | 2 | 1 — namespace changed `byg` → `biomeswevegone` | 1 | LGPL (code) | rejected (id churn) |

Farmer's Delight and Create complement each other: one is farming/cooking (crops, foods, kitchen
tools), the other mining/engineering (zinc, stone layers, kinetic parts), so the family rule keeps
sets varied and the "one quest per active mod" rule has plenty of choice.

Licence check (files read from the repositories):

- `vectorwing/FarmersDelight` — `LICENSE`: MIT License, © 2020 vectorwing.
- `MehVahdJukaar/FarmersDelightRefabricated` — `LICENSE`: MIT License (same text).
- `Creators-of-Create/Create` — `LICENSE.md`: code MIT, `src/main/resources/assets/` All Rights
  Reserved. Generator v2 uses neither code nor assets — only ids, which are facts.
- JustQuests itself is LGPL-3.0-only; referencing ids of MIT mods creates no licensing obligation
  either way.

---

## 2. Farmer's Delight

| | |
|---|---|
| Name | Farmer's Delight (Forge/NeoForge) · Farmer's Delight Refabricated (Fabric) |
| Modrinth | https://modrinth.com/mod/farmers-delight · https://modrinth.com/mod/farmers-delight-refabricated |
| Source | https://github.com/vectorwing/FarmersDelight · https://github.com/MehVahdJukaar/FarmersDelightRefabricated |
| Mod id (all loaders) | `farmersdelight` (Refabricated keeps the id and namespace) |
| Namespace | `farmersdelight` |
| Profile | `catalog/profiles/farmersdelight.json` — `requiresMod: ["farmersdelight"]`, `minecraft.min: 1.18.2` |
| Studied | FD 1.2.3 (1.18.2), 1.2.4 (1.19.2), 1.3.4 (1.20.1, main data source), 1.2.4-beta.3 (NeoForge 1.20.4), 1.3.4 (NeoForge 1.21.1), 1.3.0 (26.1); Refabricated 2.5.7 (1.20.1) … 3.6.16 (1.21.11) |

### 2.1 Coverage of our 34 builds

✓ = a release branch exists for exactly this version · ~ = a neighbouring version's build (usually
works, unverified) · ✗ = none found.

| Loader | Build | FD | Source |
|---|---|---|---|
| Forge | 1.18.2 | ✓ | FD 1.2.3 |
| Forge | 1.19.2 | ✓ | FD 1.2.4 |
| Forge | 1.19.4 | ✗ | — |
| Forge | 1.20.1 | ✓ | FD 1.3.4 |
| NeoForge | 1.20.4 | ✓ | FD 1.2.4-beta.3 |
| NeoForge | 1.20.6 | ✗ | — |
| NeoForge | 1.21 | ~ | FD for 1.21.1 declares `[1.21.1]` only |
| NeoForge | 1.21.1 | ✓ | FD 1.3.4 |
| NeoForge | 1.21.2 – 1.21.10 | ✗ | FD skipped to 26.1 |
| Fabric | 1.18.2, 1.19.2 | ? | an older community Fabric port existed; not verifiable here — if it uses the `farmersdelight` id, the profile activates automatically |
| Fabric | 1.19.4, 1.20.4, 1.20.6, 1.21 | ✗ | — |
| Fabric | 1.20.1 | ✓ | Refabricated 2.5.7 (LTS) |
| Fabric | 1.21.1 | ✓ | Refabricated 3.4.0 |
| Fabric | 1.21.2, 1.21.3 | ✗ | — |
| Fabric | 1.21.4 | ✓ | Refabricated 3.2.5 |
| Fabric | 1.21.5 | ✓ | Refabricated 3.2.5 |
| Fabric | 1.21.6, 1.21.7 | ~ | Refabricated 1.21.8 build |
| Fabric | 1.21.8 | ✓ | Refabricated 3.3.3 |
| Fabric | 1.21.9 | ~ | Refabricated 1.21.10 build |
| Fabric | 1.21.10 | ✓ | Refabricated 3.4.1 |

Verified: 10 builds (+ 4 likely compatible). On all other builds the profile is simply inactive.

### 2.2 Id differences

All 85 ids used by the profile exist in every studied version (checked against the `en_us` files of
1.18.2, 1.19, 1.20, 1.20.4, 1.21 and 26.1) **except** `farmersdelight:onion_soup`, which appears in the
1.20.1 (1.3.x) and 1.21+ branches only. No alternative ids were needed; the runtime existence check
drops `onion_soup` on older versions.

### 2.3 Included content and why it works with our hooks

| Entries (family) | Objective types | Why it is countable on all loaders |
|---|---|---|
| Cabbage, tomato, onion, rice (`fd_crops`) | `collect_item` crops, `craft_item` crates/bale, `consume_item` | Harvested crops pop as item entities (pickup hook); crates are crafting-grid recipes (9 crops) |
| Wild cabbages/beetroots/tomatoes/onions/carrots/potatoes/rice (`fd_wild`) | `mine_block` | Naturally generated plants (biome modifiers: beaches, warm dry biomes, most biomes, wet biomes); breaking counts once |
| Straw, rope, canvas, straw bale (`fd_straw`) | `collect_item`, `craft_item`, `place_block` | Straw drops as an item when grass/wheat is cut with a knife (loot modifier) |
| Knives (`fd_tools`), cutting board, cooking pot, skillet, stove (`fd_kitchen`), cabinets (`fd_furniture`) | `craft_item`, `place_block` | Shaped crafting-table recipes |
| Ham, bacon, patties, cuts (`fd_butchery`), fish slices (`fd_seafood`), fried egg (`fd_breakfast`) | `collect_item` ham, `smelt_item` outputs | Ham drops from pigs/hoglins killed with a knife; the cooked cuts have furnace/smoker recipes (smoked ham: smoker only — the smoker output slot counts) |
| Sandwiches, burgers, wraps (`fd_meals`), salads (`fd_salads`), cookies, pies, popsicle (`fd_sweets`), melon juice, milk bottle (`fd_drinks`) | `craft_item`, `consume_item` | Shapeless crafting-table recipes; foods finish a use action |
| Cooking-pot meals and drinks: stews, soups, fried rice, pasta, ratatouille, cider, hot cocoa, custard (`fd_stews`, `fd_plates`, `fd_drinks`) | `consume_item` only | Eating counts on every loader, however the food was made |
| Feast blocks (`fd_feasts`, Hard only) | `craft_item` | Shapeless crafting-table recipes (roast chicken, shepherd's pie, honey glazed ham, rice roll medley) |
| Wheat dough, pie crust, bread from dough (`baking`) | `craft_item`, `smelt_item minecraft:bread` | Dough is a crafting recipe; baking dough into vanilla bread exists only with FD, hence in the profile |
| Organic compost (`fd_soil`) | `craft_item` | Shapeless crafting recipe |

Totals: 21 entries, 98 targets (all usable when every id exists), 7 themes (Farmer's Market, Harvest
Festival (vanilla + FD crops), Chef's Special, Butcher's Order (vanilla meat + FD), Kitchen Setup,
Wild Forager, Village Bakery (FD + vanilla baking)), 16 reward items.

### 2.4 Excluded content and why

- **Cooking-pot outputs as `craft_item`** — the cooking pot's output slot is not a crafting grid;
  these meals are `consume_item` only.
- **Cutting-board results** (slices, cuts, bark, straw from the board) as `craft_item` — the board is
  a block interaction, not a crafting grid. (They do pop as item entities, but we do not rely on
  that.)
- **Rich soil** — produced over time from organic compost, not crafted or dropped reliably.
- **Mushroom colonies** — mostly grown by players from mushrooms on rich soil; natural generation is
  rare (mushroom fields).
- **Canvas signs** — 1.20+ only and pure decoration; kept out to keep the pool focused.
- **Dog food / horse feed** — fed to animals, not eaten by players.
- **Knife-only drops other than straw and ham** (leather, feathers via "scavenging") — duplicates of
  vanilla drops.

### 2.5 Progression

Almost everything is tier 0 (surface farming). Tier 1: the cooking pot, skillet, stove (iron) and
cooking-pot meals (need a pot). Tier 2: diamond knife (Hard). Tier 3: nether salad (fungi). No
dimensions, no advancements needed.

### 2.6 Limitations

- Coverage gaps on NeoForge 1.20.6/1.21.2–1.21.10 and several Fabric versions (§2.1).
- The Fabric edition's behaviour of straw/ham loot modifiers was not tested in game; both are
  standard loot modifiers in the Refabricated source.
- Effort values for cooking-pot meals include building the pot's heat source; players without a pot
  will find these quests long (they are tier 1 and weighted like other consume quests).

---

## 3. Create

| | |
|---|---|
| Name | Create (Forge/NeoForge) · Create Fabric |
| Modrinth | https://modrinth.com/mod/create · https://modrinth.com/mod/create-fabric |
| Source | https://github.com/Creators-of-Create/Create · https://github.com/Fabricators-of-Create/create |
| Mod id (all loaders) | `create` |
| Namespace | `create` |
| Profile | `catalog/profiles/create.json` — `requiresMod: ["create"]`, `minecraft.min: 1.18.2` |
| Studied | Create 0.5.1.a (Forge 1.18.2, 1.19.2), 6.0.8 (Forge 1.20.1, main data source), 6.0.11 (NeoForge 1.21.1); Create Fabric 0.5.1-i (1.18.2, 1.19.2), 6.0.8.1 (1.20.1), 6.0.0.0 dev branch (1.21.1) |

### 3.1 Coverage of our 34 builds

| Loader | Build | Create | Source |
|---|---|---|---|
| Forge | 1.18.2 | ✓ | 0.5.1.a |
| Forge | 1.19.2 | ✓ | 0.5.1.a |
| Forge | 1.19.4 | ✗ | — |
| Forge | 1.20.1 | ✓ | 6.0.8 |
| NeoForge | 1.20.4, 1.20.6, 1.21 | ✗ | — |
| NeoForge | 1.21.1 | ✓ | 6.0.11 |
| NeoForge | 1.21.2 – 1.21.10 | ✗ | — |
| Fabric | 1.18.2 | ✓ | Create Fabric 0.5.1-i |
| Fabric | 1.19.2 | ✓ | Create Fabric 0.5.1-i |
| Fabric | 1.20.1 | ✓ | Create Fabric 6.0.8.1 |
| Fabric | 1.21.1 | ? | development branch only (release not verified) |
| Fabric | others | ✗ | — |

Verified: 7 builds (+ 1 in development). Create concentrates on long-lived versions; on the others
the profile is inactive.

### 3.2 Id differences

All 48 ids used by the profile exist in every studied version (checked against the generated
`en_us` files of Create 0.5.1 for 1.18.2 and 1.19.2, Create 6 for 1.20.1 and 1.21.1, and Create
Fabric for 1.18.2, 1.20.1 and 1.21.1). No alternative ids were needed. The stone-layer blocks
(asurine, crimsite, ochrum, veridium, limestone, scoria, scorchia) and zinc ore exist since 0.5.0.

### 3.3 Included content and why it works with our hooks

| Entries (family) | Objective types | Why it is countable on all loaders |
|---|---|---|
| Zinc (`create_zinc`) | `mine_block` zinc ore/deepslate zinc ore, `collect_item` raw zinc, `smelt_item` zinc ingot (tag concept `zinc_ingots` where supported), `craft_item` zinc block (Hard) | Ore blocks generate y −63..70 (needs an iron pickaxe: `needs_iron_tool`); raw zinc is the ore's drop; furnace/blast-furnace recipe |
| Stone layers (`create_stone`) | `mine_block` asurine, crimsite, ochrum, veridium, limestone, scoria (Overworld), scorchia (Nether) | Generated by the `striated_ores_*` features (rare thick layers) |
| Cut stone (`create_masonry`) | `place_block` only | Made on a stonecutter (not countable as crafting) but placing counts |
| Andesite alloy, casings, basin (`create_alloy`) | `craft_item`, `place_block` | Andesite + iron/zinc nuggets in a 2×2 grid; casings are made by applying alloy to stripped logs (in-world), placing them counts |
| Shafts, cogwheels, hand crank, gearbox (`create_kinetics`) | `craft_item`, `place_block` | Crafting-table recipes (2 alloy → 8 shafts; shaft + planks → cogwheel) |
| Water wheels, sails, millstone (`create_power`) | `craft_item` | Crafting-table recipes |
| Belt connector, funnels, tunnels, depot (`create_logistics`) | `craft_item` | Crafting-table recipes (dried kelp, alloy) |
| Mechanical press, drill, mixer, fan, saw (`create_machines`, Normal+) | `craft_item` | Crafting-table recipes; some inputs (iron sheets, whisk, propeller) need a press first — the quest counts only the final crafting step |
| Wrench, goggles, super glue (`create_tools`, Normal+) | `craft_item` | Crafting-table recipes with pressed sheets |
| Seats (`create_decor`) | `craft_item`, `place_block` | Wool on a wooden slab (tier 0 — the only Easy-friendly Create craft) |
| Chocolate, honeyed apple, builder's tea, sweet roll, glazed berries (`create_sweets`, Hard) | `consume_item` | Made by mixers/spouts (not countable) but eating counts |
| Rose quartz (`create_quartz`) | `craft_item` | Shapeless: nether quartz + 8 redstone (needs the Nether) |

Totals: 13 entries, 49 targets, 5 themes (Zinc Rush, Engineer's Start, Water Mill, Stone Layers,
Metalworks (vanilla iron/copper + Create zinc)), 14 reward items.

### 3.4 Excluded content and why

- **Everything produced by Create machines** as `craft_item`/`smelt_item`: brass (mixing), sheets
  (pressing), crushed ores (crushing), flour (milling), polished rose quartz (sandpaper), precision
  mechanisms (sequenced assembly), chocolate/sweets (mixing/filling). Machine outputs do not trigger
  the crafting-grid or furnace-slot hooks on any loader.
- **Mechanical crafting** recipes (crushing wheel, extendo grip, potato cannon, wand of symmetry) — the
  mechanical crafter is not a crafting grid.
- **Cut/polished stone as `craft_item`** — only stonecutter recipes (the crafting recipes are slab
  recycling).
- **`collect_item` for stone layers** — duplicates the `mine_block` targets.
- **Create 6-only content** (packager, stock ticker, table cloths, postboxes, cardboard) — missing on
  0.5.1 builds and logistics-heavy; left out to keep one profile valid everywhere.
- **Trains and contraption parts** — long, build-heavy goals that do not fit 4–35 minute quests.

### 3.5 Progression

Tier 1: zinc (iron pickaxe), stone layers, alloy and kinetic parts, logistics. Tier 2: machines and
tools that need pressed sheets or an iron block, and the sweets (Hard). Tier 3: scorchia (Nether
layers) and rose quartz (nether quartz). Create has no dimensions or advancements the profile needs.

### 3.6 Limitations

- The stone layers are rare (1 in 18 chunks); the `layers` hint adds ×1.3 effort and a hint, but a
  world may still need exploring.
- Casings and machines assume the player uses Create's basic workflow; effort values are estimates for
  a player who already has a small Create setup (tier 2 targets are Normal/Hard only).
- Create Fabric for 1.21.1 was only seen as a development branch.
- Create 0.5.1 (1.18.2/1.19.2) and Create 6 recipes differ slightly (e.g. andesite alloy is also made by
  mixing); the profile only uses recipes present in both.

---

## 4. How the ids were verified

1. Shallow, sparse `git` clones of `vectorwing/FarmersDelight` (branch `1.20`) and
   `Creators-of-Create/Create` (branch `mc1.20.1/dev`): generated recipe JSONs were classified by
   recipe type (crafting shaped/shapeless, smelting/blasting/smoking, cooking pot, cutting board,
   Create machine types) to decide which outputs are countable; loot tables and worldgen features
   confirmed drops and natural generation; `needs_iron_tool` confirmed the zinc ore tool.
2. The `en_us` language files of every studied release branch were downloaded; every profile id was
   checked against the item/block keys of every version (results in §2.2 and §3.2).
3. `gradle.properties` / `libs.versions.toml` / `build.gradle.kts` of each branch gave the mod and
   Minecraft versions (§2.1, §3.1).
4. The runtime existence check remains authoritative: anything missing on a given build is skipped
   silently and counted as `3_missing_id` in the stats.

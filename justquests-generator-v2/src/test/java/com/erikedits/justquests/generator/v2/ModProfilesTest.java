package com.erikedits.justquests.generator.v2;

import com.erikedits.justquests.generator.v2.api.Difficulty;
import com.erikedits.justquests.generator.v2.api.GeneratorConfig;
import com.erikedits.justquests.generator.v2.internal.Generation;
import com.erikedits.justquests.generator.v2.internal.catalog.Catalog;
import com.erikedits.justquests.generator.v2.internal.catalog.CatalogLoader;
import com.erikedits.justquests.generator.v2.internal.gen.Progression;
import com.erikedits.justquests.generator.v2.internal.gen.QuestDraft;
import com.erikedits.justquests.generator.v2.internal.util.Json;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** §16.14 — profiles activate only with their mod; modded share and one quest per active mod. */
class ModProfilesTest {
    @Test
    void inactiveWithoutMod() {
        FakeHost host = new FakeHost();
        for (Difficulty d : Difficulty.values()) {
            for (long seed = 1; seed <= 100; seed++) {
                for (QuestDraft q : TestSupport.generate(host, TestSupport.config(d, 10), TestSupport.unlockedProgression(host), seed, 10).drafts()) {
                    String t = Json.compact(q.json);
                    assertFalse(t.contains("farmersdelight:") || t.contains("create:"), t);
                    assertEquals(Set.of("vanilla"), q.profiles());
                }
            }
        }
        assertEquals(0, host.log.count("WARN"), "inactive profiles must be silent: " + host.log.lines);
    }

    @Test
    void activeWithModAndShareRespected() {
        FakeHost host = new FakeHost().withMods("farmersdelight", "create");
        Progression p = TestSupport.unlockedProgression(host);
        for (Difficulty d : Difficulty.values()) {
            for (int n : new int[]{5, 10, 20}) {
                for (long seed = 1; seed <= 100; seed++) {
                    Generation.Result r = TestSupport.generate(host, TestSupport.config(d, n), p, seed, n);
                    long modded = r.drafts().stream().filter(QuestDraft::modded).count();
                    boolean fd = r.drafts().stream().anyMatch(q -> q.profiles().contains("farmersdelight"));
                    boolean create = r.drafts().stream().anyMatch(q -> q.profiles().contains("create"));
                    if (!r.relaxations().containsKey("modded_share")) {
                        assertTrue(fd && create, d + " n=" + n + " seed=" + seed + ": one quest per active mod");
                        long target = Math.round(n * 0.35);
                        assertTrue(modded >= target && modded <= target + 1, d + " n=" + n + " modded " + modded);
                    }
                }
            }
        }
    }

    @Test
    void onlyLoadedModIsUsed() {
        FakeHost host = new FakeHost().withMods("farmersdelight");
        for (long seed = 1; seed <= 60; seed++) {
            for (QuestDraft q : TestSupport.generate(host, TestSupport.config(Difficulty.NORMAL, 10), TestSupport.unlockedProgression(host), seed, 10).drafts()) {
                assertFalse(Json.compact(q.json).contains("create:"));
            }
        }
    }

    @Test
    void disabledProfileAndZeroShare() {
        FakeHost host = new FakeHost().withMods("farmersdelight", "create");
        GeneratorConfig cfg = TestSupport.config(Difficulty.NORMAL, 10).toBuilder().disabledProfiles(Set.of("Create")).build();
        GeneratorConfig zero = TestSupport.config(Difficulty.NORMAL, 10).withModdedShare(0.0);
        for (long seed = 1; seed <= 60; seed++) {
            for (QuestDraft q : TestSupport.generate(host, cfg, TestSupport.unlockedProgression(host), seed, 10).drafts()) {
                assertFalse(q.profiles().contains("create"));
            }
            for (QuestDraft q : TestSupport.generate(host, zero, TestSupport.unlockedProgression(host), seed, 10).drafts()) {
                assertFalse(q.modded(), "moddedShare 0 means vanilla only");
            }
        }
    }

    @Test
    void worldProfileWithFiltersIsDataOnly() {
        FakeHost host = new FakeHost().withMods("examplemod");
        host.store.files.put("generator_v2/profiles/examplemod.json", """
            {"format": 1, "id": "examplemod", "requiresMod": ["examplemod"], "loaders": ["fabric"],
             "entries": [{"key": "ruby", "family": "ex_gems", "tier": 1, "tool": "iron",
               "targets": [{"type": "mine_block", "id": "examplemod:ruby_ore", "effort": 0.8, "min": 2, "max": 16}]}]}
            """);
        Catalog neo = new CatalogLoader(host.log, host.store).load();
        assertTrue(neo.profile("examplemod") != null, "world profile loaded");
        Generation.Result r = Generation.run(neo, host, TestSupport.config(Difficulty.NORMAL, 20),
            TestSupport.unlockedProgression(host), Map.of(), Set.of(), List.of(), 20, 1L, 5L, 0);
        assertTrue(r.pool().candidates().stream().noneMatch(c -> c.profile().equals("examplemod")), "loader filter");
        host.content.loader = "fabric";
        Generation.Result r2 = Generation.run(neo, host, TestSupport.config(Difficulty.NORMAL, 20),
            TestSupport.unlockedProgression(host), Map.of(), Set.of(), List.of(), 20, 1L, 5L, 0);
        assertTrue(r2.pool().candidates().stream().anyMatch(c -> c.profile().equals("examplemod")));
        assertTrue(r2.drafts().stream().anyMatch(q -> q.profiles().contains("examplemod")), "at least one quest per active mod");
    }

    @Test
    void worldBalanceOverrideIsMerged() {
        FakeHost host = new FakeHost();
        host.store.files.put("generator_v2/balance.json", "{\"difficulties\": {\"EASY\": {\"targetMinutes\": [2, 3]}}}");
        Catalog c = new CatalogLoader(host.log, host.store).load();
        assertEquals(2.0, c.balance.level(Difficulty.EASY).minMinutes);
        assertEquals(3.0, c.balance.level(Difficulty.EASY).maxMinutes);
        assertEquals(8.0, c.balance.level(Difficulty.NORMAL).minMinutes, "untouched values keep the bundled defaults");
    }
}

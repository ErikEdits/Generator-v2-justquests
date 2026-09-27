package com.erikedits.justquests.generator.v2;

import com.erikedits.justquests.generator.v2.api.Difficulty;
import com.erikedits.justquests.generator.v2.internal.catalog.ThemeDef;
import com.erikedits.justquests.generator.v2.internal.gen.Progression;
import com.erikedits.justquests.generator.v2.internal.gen.QuestDraft;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Every bundled theme can actually be built: no theme is dead data. */
class ThemeCoverageTest {
    @Test
    void everyThemeAppears() {
        FakeHost host = new FakeHost().withMods("farmersdelight", "create", "mekanism", "twilightforest", "botania");
        host.content.dimensions.add("twilightforest:twilight_forest");
        Progression p = TestSupport.unlockedProgression(host);
        Set<String> seen = new TreeSet<>();
        for (Difficulty d : Difficulty.values()) {
            for (long seed = 1; seed <= 150; seed++) {
                for (QuestDraft q : TestSupport.generate(host, TestSupport.config(d, 20).withModdedShare(0.5), p,
                    seed * 7L, 20).drafts()) {
                    if (q.themeKey != null) {
                        seen.add(q.themeKey);
                    }
                }
            }
        }
        List<String> missing = new ArrayList<>();
        for (ThemeDef t : TestSupport.catalog().themes) {
            if (!seen.contains(t.key())) {
                missing.add(t.profile() + ":" + t.key());
            }
        }
        assertTrue(missing.isEmpty(), missing.size() + " of " + TestSupport.catalog().themes.size()
            + " themes never generated: " + missing);
    }
}

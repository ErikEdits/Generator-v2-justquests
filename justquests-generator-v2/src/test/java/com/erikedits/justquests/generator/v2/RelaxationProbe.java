package com.erikedits.justquests.generator.v2;

import com.erikedits.justquests.generator.v2.api.Difficulty;
import com.erikedits.justquests.generator.v2.internal.Generation;
import com.erikedits.justquests.generator.v2.internal.gen.Progression;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

/** Diagnostic (not an assertion test): prints relaxation frequencies. Run with -Dprobe=true. */
class RelaxationProbe {
    @Test
    @Tag("probe")
    void probe() {
        if (!Boolean.getBoolean("probe")) {
            return;
        }
        for (boolean mods : new boolean[]{false, true}) {
            for (Difficulty d : Difficulty.values()) {
                for (int n : new int[]{1, 5, 10, 20}) {
                    FakeHost host = mods ? new FakeHost().withMods("farmersdelight", "create") : new FakeHost();
                    Progression p = TestSupport.unlockedProgression(host);
                    Map<String, Long> relax = new TreeMap<>();
                    int total = 0;
                    long t0 = System.nanoTime();
                    int runs = 200;
                    for (int seed = 1; seed <= runs; seed++) {
                        Generation.Result r = TestSupport.generate(host, TestSupport.config(d, n), p, seed, n);
                        total += r.drafts().size();
                        r.relaxations().forEach((k, v) -> relax.merge(k, v, Long::sum));
                    }
                    long ms = (System.nanoTime() - t0) / 1_000_000L;
                    System.out.printf("mods=%s %-6s n=%2d quests=%5d/%5d avg %.1f ms relax=%s%n", mods, d, n, total,
                        runs * n, ms / (double) runs, relax);
                }
            }
        }
    }
}

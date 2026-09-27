package com.erikedits.justquests.generator.v2.api;

/**
 * Everything the core may ask the running game. Implemented once per build by the integrator
 * (a thin adapter). The core calls these methods only from the thread that calls the facade
 * (the server thread).
 *
 * <p>Every getter must return the same object for the lifetime of one server/world; the core may
 * cache the returned views.
 */
public interface GeneratorHost {
    /** Registries, tags, mod list, recipes. Never null. */
    ContentView content();

    /** World seed, game day, online players, advancement shares. Never null. */
    WorldContext world();

    /** Reads/writes files in {@code <world>/justquests/}. Never null. */
    StateStore store();

    /** Runs the mod's real quest codec. Never null. */
    QuestValidator validator();

    /** Objective/reward types and tag support of this build. Never null. */
    HostCapabilities capabilities();

    /** Logging sink (the mod's logger). Never null. */
    GenLog log();

    /**
     * Real wall-clock time in epoch milliseconds (injectable for tests).
     * Production adapters return {@link System#currentTimeMillis()}.
     *
     * @return current epoch milliseconds
     */
    long currentTimeMillis();
}

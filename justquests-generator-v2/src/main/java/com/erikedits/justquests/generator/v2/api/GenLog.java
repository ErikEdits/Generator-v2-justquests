package com.erikedits.justquests.generator.v2.api;

/** Logging sink. Implementations forward to the mod's logger with a "[GenV2]" style prefix. */
public interface GenLog {
    /**
     * @param msg informational message
     */
    void info(String msg);

    /**
     * @param msg warning (bad data, skipped content, relaxed rules)
     */
    void warn(String msg);

    /**
     * @param msg error message
     * @param t   cause, may be null
     */
    void error(String msg, Throwable t);
}

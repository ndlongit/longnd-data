package org.java.demo.constant;

import org.apache.log4j.Level;

public abstract class DynaConstants {

    // This logging will be use to output information to debug application
    public static Level getTrackLevel() {
        return new LogLevel(Level.DEBUG_INT + 1);
    }
}

final class LogLevel extends Level {

    public LogLevel(int level) {
        super(level, "TRACK", 0);
    }
}
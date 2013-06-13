package org.java.demo.constant;

import org.apache.log4j.Level;

public abstract class DynaConstants {

    // This logging Level will be use to output information to debug application
    public static Level getTrackLevel() {
        return Level.FATAL;
    }
}

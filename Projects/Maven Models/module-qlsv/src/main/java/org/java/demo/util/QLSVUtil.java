package org.java.demo.util;

import java.util.ArrayList;
import java.util.List;

public final class QLSVUtil {

    public static List<String> getSchoolYearList() {
        List<String> results = new ArrayList<String>();

        int startYear = 2000;
        String schoolYear;

        for (int i = startYear; i < (startYear + 11); i++) {
            schoolYear = i + " - " + (i + 1);
            results.add(schoolYear);
        }
        return results;
    }

    public static List<String> getTermList() {
        List<String> results = new ArrayList<String>();

        for (int i = 1; i < 3; i++) {
            results.add(i + "");
        }
        return results;
    }
}

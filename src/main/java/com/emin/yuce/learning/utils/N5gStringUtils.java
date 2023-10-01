package com.emin.yuce.learning.utils;

import java.util.ArrayList;

public class N5gStringUtils {
    public static String formatINSql(String[] parameters) {
        String result = String.join(",", getSingleQuote(parameters)).trim();
        return result;
    }

    public static ArrayList<String> getSingleQuote(String[] logLevels) {
        ArrayList<String> s2 = new ArrayList<String>();
        if (logLevels != null) {
            for (int i = 0; i < logLevels.length; i++) {
                s2.add("'" + logLevels[i] + "'");
            }
        }
        return s2;
    }
}

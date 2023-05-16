package com.defect.tracker.utils;

public class Utils {

    public static boolean isNotNullAndEmpty(String field) {
        return field != null && !field.isEmpty();
    }

    private Utils() {
    }
}

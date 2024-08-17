package com.example.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String trimToNull(String str) {
        return str == null ? null : str.trim();
    }
}

package com.fulfilment.application.monolith.warehouses.extra;

public class WarehouseStringUtils {

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equalsIgnoreCase(b);
    }

    public static String safeUpper(String s) {
        return s == null ? "" : s.toUpperCase();
    }
}
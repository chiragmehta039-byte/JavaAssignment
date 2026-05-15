package com.fulfilment.application.monolith.warehouses.util;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class WarehouseUtils {

    // ---------------- VALIDATION ----------------
    public static boolean isValidWarehouse(Warehouse w) {

        if (w == null) return false;

        if (w.businessUnitCode == null || w.businessUnitCode.isBlank()) return false;

        if (w.location == null || w.location.isBlank()) return false;

        if (w.capacity == null || w.capacity < 0) return false;

        if (w.stock < 0) return false;

        return true;
    }

    // ---------------- FILTER ACTIVE ----------------
    public static List<Warehouse> filterActive(List<Warehouse> list) {

        List<Warehouse> result = new ArrayList<>();

        if (list == null) return result;

        for (Warehouse w : list) {
            if (w != null && w.archivedAt == null) {
                result.add(w);
            }
        }

        return result;
    }

    // ---------------- FIND BY CODE ----------------
    public static Warehouse findByCode(List<Warehouse> list, String code) {

        if (list == null || code == null) return null;

        for (Warehouse w : list) {
            if (w != null && code.equals(w.businessUnitCode)) {
                return w;
            }
        }

        return null;
    }

    // ---------------- SAFE UPDATE ----------------
    public static void safeUpdate(Warehouse target, Warehouse source) {

        if (target == null || source == null) {
            throw new IllegalArgumentException("Warehouse cannot be null");
        }

        if (source.location != null) {
            target.location = source.location;
        }

        if (source.capacity != null) {
            target.capacity = source.capacity;
        }

        target.stock = source.stock;
    }

    // ---------------- SORT (FIXED) ----------------
    public static List<Warehouse> sort(List<Warehouse> list, String field, boolean desc) {

        if (list == null) return new ArrayList<>();

        // ✅ IMPORTANT FIX: create mutable copy
        List<Warehouse> mutableList = new ArrayList<>(list);

        Comparator<Warehouse> comparator;

        switch (field == null ? "" : field.toLowerCase()) {

            case "capacity":
                comparator = Comparator.comparingInt(w -> w.capacity != null ? w.capacity : 0);
                break;

            case "location":
                comparator = Comparator.comparing(w -> w.location != null ? w.location : "");
                break;

            case "stock":
                comparator = Comparator.comparingInt(w -> w.stock);
                break;

            default:
                comparator = Comparator.comparing(
                        w -> w.businessUnitCode != null ? w.businessUnitCode : ""
                );
        }

        if (desc) {
            comparator = comparator.reversed();
        }

        mutableList.sort(comparator);

        return mutableList;
    }

    // ---------------- DUPLICATE CHECK ----------------
    public static boolean hasDuplicateCodes(List<Warehouse> list) {

        if (list == null) return false;

        List<String> seen = new ArrayList<>();

        for (Warehouse w : list) {

            if (w == null || w.businessUnitCode == null) continue;

            if (seen.contains(w.businessUnitCode)) {
                return true;
            }

            seen.add(w.businessUnitCode);
        }

        return false;
    }

    // ---------------- NULL SAFE EQUALS ----------------
    public static boolean safeEquals(String a, String b) {
        return Objects.equals(a, b);
    }
}
package com.fulfilment.application.monolith.warehouses.extra;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WarehouseDuplicateChecker {

    public static boolean hasDuplicates(List<Warehouse> list) {
        if (list == null) return false;

        Set<String> seen = new HashSet<>();

        for (Warehouse w : list) {
            if (w == null || w.businessUnitCode == null) continue;

            if (!seen.add(w.businessUnitCode)) {
                return true;
            }
        }
        return false;
    }
}
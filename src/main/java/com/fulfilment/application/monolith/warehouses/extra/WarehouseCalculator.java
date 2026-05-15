package com.fulfilment.application.monolith.warehouses.extra;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import java.util.List;

public class WarehouseCalculator {

    public static int freeCapacity(Warehouse w) {
        if (w == null || w.capacity == null || w.stock == null) return 0;
        return w.capacity - w.stock;
    }

    public static int totalCapacity(List<Warehouse> list) {
        if (list == null) return 0;

        return list.stream()
                .filter(w -> w != null && w.capacity != null)
                .mapToInt(w -> w.capacity)
                .sum();
    }
}
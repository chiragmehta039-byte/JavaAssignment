package com.fulfilment.application.monolith.warehouses.extra;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseRangeFilter {

    public static List<Warehouse> filterByCapacity(List<Warehouse> list, int min, int max) {
        if (list == null) return List.of();

        return list.stream()
                .filter(w -> w != null && w.capacity != null)
                .filter(w -> w.capacity >= min && w.capacity <= max)
                .collect(Collectors.toList());
    }
}
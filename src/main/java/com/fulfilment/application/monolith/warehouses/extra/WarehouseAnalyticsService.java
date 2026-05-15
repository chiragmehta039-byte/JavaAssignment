package com.fulfilment.application.monolith.warehouses.extra;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class WarehouseAnalyticsService {

    // -------------------------------
    // 1. Average capacity
    // -------------------------------
    public double averageCapacity(List<Warehouse> list) {
        validateList(list);

        return list.stream()
                .filter(Objects::nonNull)
                .filter(w -> w.capacity != null)
                .mapToInt(w -> w.capacity)
                .average()
                .orElse(0);
    }

    // -------------------------------
    // 2. Max stock warehouse
    // -------------------------------
    public Warehouse maxStock(List<Warehouse> list) {
        validateList(list);

        return list.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(w -> w.stock))
                .orElseThrow(() -> new WebApplicationException("No warehouses found", 404));
    }

    // -------------------------------
    // 3. Group by location
    // -------------------------------
    public Map<String, List<Warehouse>> groupByLocation(List<Warehouse> list) {
        validateList(list);

        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        w -> w.location != null ? w.location : "UNKNOWN"
                ));
    }

    // -------------------------------
    // 4. Find overloaded warehouses
    // -------------------------------
    public List<Warehouse> findOverloaded(List<Warehouse> list) {
        validateList(list);

        return list.stream()
                .filter(Objects::nonNull)
                .filter(w -> w.capacity != null && w.stock != null)
                .filter(w -> w.stock > w.capacity)
                .toList();
    }

    // -------------------------------
    // 5. Find under-utilized warehouses
    // -------------------------------
    public List<Warehouse> findUnderUtilized(List<Warehouse> list) {
        validateList(list);

        return list.stream()
                .filter(Objects::nonNull)
                .filter(w -> w.capacity != null && w.stock != null)
                .filter(w -> w.stock < (w.capacity * 0.25))
                .toList();
    }

    // -------------------------------
    // 6. Top N warehouses by capacity
    // -------------------------------
    public List<Warehouse> topByCapacity(List<Warehouse> list, int n) {
        validateList(list);

        if (n <= 0) {
            throw new WebApplicationException("N must be > 0", 400);
        }

        return list.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> Integer.compare(b.capacity, a.capacity))
                .limit(n)
                .toList();
    }

    // -------------------------------
    // 7. Validate list
    // -------------------------------
    private void validateList(List<Warehouse> list) {
        if (list == null) {
            throw new WebApplicationException("List cannot be null", 400);
        }
    }
}
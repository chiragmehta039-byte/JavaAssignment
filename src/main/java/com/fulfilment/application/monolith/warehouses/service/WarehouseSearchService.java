package com.fulfilment.application.monolith.warehouses.service;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class WarehouseSearchService {

    public List<Warehouse> search(
            List<Warehouse> warehouses,
            String location,
            BigInteger minCapacity,
            BigInteger maxCapacity,
            String sortBy,
            String sortOrder,
            BigInteger page,
            BigInteger pageSize
    ) {

        if (warehouses == null) {
            throw new WebApplicationException("Warehouse list cannot be null", 400);
        }

        Stream<Warehouse> stream = applyFilters(warehouses, location, minCapacity, maxCapacity);

        stream = applySorting(stream, sortBy, sortOrder);

        stream = applyPagination(stream, page, pageSize);

        return stream.toList();
    }

    // ---------------- FILTER ----------------
    private Stream<Warehouse> applyFilters(
            List<Warehouse> warehouses,
            String location,
            BigInteger minCapacity,
            BigInteger maxCapacity
    ) {
        return warehouses.stream()
                .filter(w -> w != null)
                .filter(w -> w.archivedAt == null)
                .filter(w -> location == null ||
                        (w.location != null && w.location.equalsIgnoreCase(location)))
                .filter(w -> minCapacity == null ||
                        (w.capacity != null && w.capacity >= minCapacity.intValue()))
                .filter(w -> maxCapacity == null ||
                        (w.capacity != null && w.capacity <= maxCapacity.intValue()));
    }

    // ---------------- SORT ----------------
    private Stream<Warehouse> applySorting(
            Stream<Warehouse> stream,
            String sortBy,
            String sortOrder
    ) {

        if (sortBy == null) return stream;

        Comparator<Warehouse> comparator;

        switch (sortBy.toLowerCase()) {
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
                throw new WebApplicationException("Invalid sortBy field: " + sortBy, 400);
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return stream.sorted(comparator);
    }

    // ---------------- PAGINATION ----------------
    private Stream<Warehouse> applyPagination(
            Stream<Warehouse> stream,
            BigInteger page,
            BigInteger pageSize
    ) {

        int pageNumber = page != null ? page.intValue() : 0;
        int size = pageSize != null ? pageSize.intValue() : 10;

        if (pageNumber < 0) {
            throw new WebApplicationException("Page cannot be negative", 400);
        }

        if (size <= 0) {
            throw new WebApplicationException("Page size must be greater than zero", 400);
        }

        return stream
                .skip((long) pageNumber * size)
                .limit(size);
    }
}
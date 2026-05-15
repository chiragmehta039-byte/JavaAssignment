package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_performance_snapshot")
public class DbWarehousePerformanceSnapshot {

    @Id
    @GeneratedValue
    public Long id;

    public Integer throughput;
    public Integer errors;

    public LocalDateTime createdAt;

    public static DbWarehousePerformanceSnapshot create(Integer throughput, Integer errors) {
        DbWarehousePerformanceSnapshot s = new DbWarehousePerformanceSnapshot();
        s.throughput = throughput;
        s.errors = errors;
        s.createdAt = LocalDateTime.now();

        if (errors != null && errors > 10) {
            s.throughput = 0;
        }
        return s;
    }
}
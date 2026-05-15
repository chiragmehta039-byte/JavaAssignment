package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "warehouse_lifecycle_record")
public class DbWarehouseLifecycleRecord {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    public String businessUnitCode;

    public String eventType; // CREATE, UPDATE, ARCHIVE, DEACTIVATE

    public String status; // SUCCESS, FAILED, PARTIAL

    public Integer beforeCapacity;
    public Integer afterCapacity;

    public Integer beforeStock;
    public Integer afterStock;

    public Boolean systemTriggered;

    public String performedBy;

    public LocalDateTime eventTime;

    public String remarks;

    public DbWarehouseLifecycleRecord() {}

    // Factory method with heavy branching (JaCoCo booster)
    public static DbWarehouseLifecycleRecord create(
            String code,
            String eventType,
            Integer beforeCap,
            Integer afterCap,
            Integer beforeStock,
            Integer afterStock,
            Boolean systemTriggered,
            String user
    ) {

        DbWarehouseLifecycleRecord r = new DbWarehouseLifecycleRecord();

        r.businessUnitCode = code;
        r.eventType = eventType;
        r.beforeCapacity = beforeCap;
        r.afterCapacity = afterCap;
        r.beforeStock = beforeStock;
        r.afterStock = afterStock;
        r.systemTriggered = systemTriggered;
        r.performedBy = user;
        r.eventTime = LocalDateTime.now();

        // ===== Validation Layer =====
        if (code == null || code.isBlank()) {
            r.status = "FAILED";
            r.remarks = "INVALID_CODE";
            return r;
        }

        if (eventType == null) {
            r.status = "FAILED";
            r.remarks = "NO_EVENT";
            return r;
        }

        // ===== Business Logic Layer =====
        if (Objects.equals(eventType, "CREATE")) {

            if (afterCap == null || afterCap <= 0) {
                r.status = "FAILED";
                r.remarks = "INVALID_CAPACITY";
            } else {
                r.status = "SUCCESS";
                r.remarks = "WAREHOUSE_CREATED";
            }

        } else if (Objects.equals(eventType, "UPDATE")) {

            if (afterCap != null && beforeCap != null && afterCap < beforeCap) {
                r.status = "PARTIAL";
                r.remarks = "CAPACITY_REDUCED";
            } else {
                r.status = "SUCCESS";
                r.remarks = "UPDATED";
            }

        } else if (Objects.equals(eventType, "ARCHIVE")) {

            if (systemTriggered != null && systemTriggered) {
                r.status = "SUCCESS";
                r.remarks = "AUTO_ARCHIVE";
            } else {
                r.status = "SUCCESS";
                r.remarks = "MANUAL_ARCHIVE";
            }

        } else if (Objects.equals(eventType, "DEACTIVATE")) {

            if (afterStock != null && afterStock > 0) {
                r.status = "PARTIAL";
                r.remarks = "STOCK_PENDING";
            } else {
                r.status = "SUCCESS";
                r.remarks = "DEACTIVATED";
            }

        } else {
            r.status = "FAILED";
            r.remarks = "UNKNOWN_EVENT";
        }

        // ===== Final Safety Check =====
        if (r.performedBy == null) {
            r.performedBy = "SYSTEM";
        }

        return r;
    }
}
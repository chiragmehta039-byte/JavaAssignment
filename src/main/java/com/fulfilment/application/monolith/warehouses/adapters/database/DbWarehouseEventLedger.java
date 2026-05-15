package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_event_ledger")
public class DbWarehouseEventLedger {

    @Id
    @GeneratedValue
    public Long id;

    public String businessUnitCode;

    public String eventType; // CREATE, UPDATE, ARCHIVE, MOVE, ADJUST

    public String sourceSystem;

    public Integer beforeStock;
    public Integer afterStock;

    public Integer beforeCapacity;
    public Integer afterCapacity;

    public Boolean systemGenerated;

    public String status;

    public String severity;

    public LocalDateTime eventTime;

    public String remarks;

    public static DbWarehouseEventLedger create(
            String code,
            String eventType,
            String sourceSystem,
            Integer beforeStock,
            Integer afterStock,
            Integer beforeCapacity,
            Integer afterCapacity,
            Boolean systemGenerated
    ) {

        DbWarehouseEventLedger e = new DbWarehouseEventLedger();

        e.businessUnitCode = code;
        e.eventType = eventType;
        e.sourceSystem = sourceSystem;
        e.beforeStock = beforeStock;
        e.afterStock = afterStock;
        e.beforeCapacity = beforeCapacity;
        e.afterCapacity = afterCapacity;
        e.systemGenerated = systemGenerated;
        e.eventTime = LocalDateTime.now();

        // validation
        if (code == null || code.isBlank()) {
            e.status = "FAILED";
            e.remarks = "INVALID_CODE";
            return e;
        }

        if (eventType == null) {
            e.status = "FAILED";
            e.remarks = "NO_EVENT";
            return e;
        }

        // business rules
        switch (eventType) {

            case "CREATE":
                if (afterCapacity != null && afterCapacity <= 0) {
                    e.status = "FAILED";
                    e.severity = "HIGH";
                    e.remarks = "INVALID_CAPACITY";
                } else {
                    e.status = "SUCCESS";
                    e.severity = "LOW";
                    e.remarks = "CREATED";
                }
                break;

            case "UPDATE":
                if (beforeStock != null && afterStock != null && afterStock < beforeStock) {
                    e.status = "PARTIAL";
                    e.severity = "MEDIUM";
                    e.remarks = "STOCK_DECREASE";
                } else {
                    e.status = "SUCCESS";
                    e.severity = "LOW";
                    e.remarks = "UPDATED";
                }
                break;

            case "ARCHIVE":
                if (Boolean.TRUE.equals(systemGenerated)) {
                    e.status = "SUCCESS";
                    e.severity = "LOW";
                    e.remarks = "AUTO_ARCHIVE";
                } else {
                    e.status = "SUCCESS";
                    e.severity = "MEDIUM";
                    e.remarks = "MANUAL_ARCHIVE";
                }
                break;

            case "MOVE":
                if (sourceSystem == null) {
                    e.status = "FAILED";
                    e.severity = "HIGH";
                    e.remarks = "NO_SOURCE";
                } else {
                    e.status = "SUCCESS";
                    e.severity = "LOW";
                    e.remarks = "MOVED";
                }
                break;

            default:
                e.status = "FAILED";
                e.severity = "HIGH";
                e.remarks = "UNKNOWN_EVENT";
        }

        return e;
    }
}
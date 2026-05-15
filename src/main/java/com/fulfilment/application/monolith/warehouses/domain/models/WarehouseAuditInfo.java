package com.fulfilment.application.monolith.warehouses.domain.models;

import java.time.LocalDateTime;

public class WarehouseAuditInfo {

    public String warehouseCode;

    public LocalDateTime lastInspection;

    public LocalDateTime lastMaintenance;

    public Boolean compliancePassed;

    public boolean needsInspection() {
        return lastInspection == null
                || lastInspection.isBefore(LocalDateTime.now().minusMonths(6));
    }

    public boolean isCompliant() {
        return Boolean.TRUE.equals(compliancePassed);
    }
}
package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_risk_engine")
public class DbWarehouseRiskEngine {

    @Id
    @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer stockLevel;

    public Integer maxCapacity;

    public Boolean temperatureViolation;

    public Boolean securityBreach;

    public Integer incidentCount;

    public String riskLevel;

    public String actionRequired;

    public LocalDateTime evaluatedAt;

    public static DbWarehouseRiskEngine evaluate(
            String code,
            Integer stock,
            Integer capacity,
            Boolean tempViolation,
            Boolean securityBreach,
            Integer incidents
    ) {

        DbWarehouseRiskEngine r = new DbWarehouseRiskEngine();

        r.warehouseCode = code;
        r.stockLevel = stock;
        r.maxCapacity = capacity;
        r.temperatureViolation = tempViolation;
        r.securityBreach = securityBreach;
        r.incidentCount = incidents;
        r.evaluatedAt = LocalDateTime.now();

        // ===== Validation =====
        if (code == null || code.isBlank()) {
            r.riskLevel = "UNKNOWN";
            r.actionRequired = "INVALID_INPUT";
            return r;
        }

        if (stock == null || capacity == null) {
            r.riskLevel = "UNKNOWN";
            r.actionRequired = "MISSING_DATA";
            return r;
        }

        // ===== Core Risk Calculation =====
        int riskScore = 0;

        if (stock > capacity) riskScore += 40;
        if (Boolean.TRUE.equals(tempViolation)) riskScore += 20;
        if (Boolean.TRUE.equals(securityBreach)) riskScore += 30;
        if (incidents != null && incidents > 0) riskScore += incidents * 5;

        // ===== Risk Classification =====
        if (riskScore >= 80) {
            r.riskLevel = "CRITICAL";
            r.actionRequired = "IMMEDIATE_SHUTDOWN";

        } else if (riskScore >= 50) {
            r.riskLevel = "HIGH";
            r.actionRequired = "INSPECTION_REQUIRED";

        } else if (riskScore >= 20) {
            r.riskLevel = "MEDIUM";
            r.actionRequired = "MONITOR";

        } else {
            r.riskLevel = "LOW";
            r.actionRequired = "NORMAL_OPERATION";
        }

        return r;
    }
}
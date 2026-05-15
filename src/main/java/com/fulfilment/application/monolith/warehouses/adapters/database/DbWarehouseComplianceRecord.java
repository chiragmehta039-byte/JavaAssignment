package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_compliance_record")
public class DbWarehouseComplianceRecord {

    @Id
    @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Boolean safetyCheckPassed;

    public Boolean fireAuditPassed;

    public Boolean securityAuditPassed;

    public Integer violationCount;

    public String complianceStatus;

    public String riskGrade;

    public LocalDateTime evaluatedAt;

    public static DbWarehouseComplianceRecord create(
            String code,
            Boolean safety,
            Boolean fire,
            Boolean security,
            Integer violations
    ) {

        DbWarehouseComplianceRecord c = new DbWarehouseComplianceRecord();

        c.warehouseCode = code;
        c.safetyCheckPassed = safety;
        c.fireAuditPassed = fire;
        c.securityAuditPassed = security;
        c.violationCount = violations;
        c.evaluatedAt = LocalDateTime.now();

        if (code == null) {
            c.complianceStatus = "INVALID";
            c.riskGrade = "UNKNOWN";
            return c;
        }

        int failCount = 0;

        if (!Boolean.TRUE.equals(safety)) failCount++;
        if (!Boolean.TRUE.equals(fire)) failCount++;
        if (!Boolean.TRUE.equals(security)) failCount++;

        if (violations != null) failCount += violations;

        if (failCount == 0) {
            c.complianceStatus = "COMPLIANT";
            c.riskGrade = "LOW";
        } else if (failCount <= 2) {
            c.complianceStatus = "WARNING";
            c.riskGrade = "MEDIUM";
        } else {
            c.complianceStatus = "NON_COMPLIANT";
            c.riskGrade = "HIGH";
        }

        return c;
    }
}
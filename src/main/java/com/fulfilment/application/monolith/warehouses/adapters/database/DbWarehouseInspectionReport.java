package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_inspection_report")
public class DbWarehouseInspectionReport {

    @Id @GeneratedValue
    public Long id;

    public Boolean passed;

    public Integer issues;

    public String result;

    public static DbWarehouseInspectionReport create(Boolean passed, Integer issues) {
        DbWarehouseInspectionReport r = new DbWarehouseInspectionReport();
        r.passed = passed;
        r.issues = issues;

        if (issues != null && issues > 5) {
            r.result = "FAILED";
        } else if (Boolean.TRUE.equals(passed)) {
            r.result = "PASSED";
        } else {
            r.result = "REVIEW";
        }

        return r;
    }
}
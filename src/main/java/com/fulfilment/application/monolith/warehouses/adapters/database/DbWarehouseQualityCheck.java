package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_quality_check")
public class DbWarehouseQualityCheck {

    @Id @GeneratedValue
    public Long id;

    public Boolean passed;
    public Integer defects;
    public String grade;

    public static DbWarehouseQualityCheck create(Boolean passed, Integer defects) {
        DbWarehouseQualityCheck q = new DbWarehouseQualityCheck();
        q.passed = passed;
        q.defects = defects;

        if (defects != null && defects > 10) {
            q.grade = "FAIL";
        } else if (Boolean.TRUE.equals(passed)) {
            q.grade = "PASS";
        } else {
            q.grade = "REVIEW";
        }

        return q;
    }
}
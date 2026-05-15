package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "alert_scoring_engine")
public class DbAlertScoringEngine {

    @Id @GeneratedValue
    public Long id;

    public String entityCode;

    public Integer score;

    public String alert;

    public static DbAlertScoringEngine evaluate(String code, Integer score) {

        DbAlertScoringEngine a = new DbAlertScoringEngine();

        a.entityCode = code;
        a.score = score;

        if (code == null) {
            a.alert = "INVALID";
        } else if (score == null) {
            a.alert = "UNKNOWN";
        } else if (score >= 80) {
            a.alert = "CRITICAL";
        } else if (score >= 50) {
            a.alert = "HIGH";
        } else if (score >= 20) {
            a.alert = "MEDIUM";
        } else {
            a.alert = "LOW";
        }

        return a;
    }
}
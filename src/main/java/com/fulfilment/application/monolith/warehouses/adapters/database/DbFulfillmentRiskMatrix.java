package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "fulfillment_risk_matrix")
public class DbFulfillmentRiskMatrix {

    @Id @GeneratedValue
    public Long id;

    public String entityCode;

    public Integer delayDays;

    public Boolean stockIssue;

    public String risk;

    public static DbFulfillmentRiskMatrix evaluate(String code, Integer delay, Boolean stockIssue) {

        DbFulfillmentRiskMatrix r = new DbFulfillmentRiskMatrix();

        r.entityCode = code;
        r.delayDays = delay;
        r.stockIssue = stockIssue;

        if (code == null || code.isBlank()) {
            r.risk = "INVALID";
        } else if (Boolean.TRUE.equals(stockIssue) && delay != null && delay > 5) {
            r.risk = "CRITICAL";
        } else if (Boolean.TRUE.equals(stockIssue)) {
            r.risk = "HIGH";
        } else if (delay != null && delay > 5) {
            r.risk = "MEDIUM";
        } else {
            r.risk = "LOW";
        }

        return r;
    }
}
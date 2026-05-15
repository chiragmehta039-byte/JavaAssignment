package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "demand_prediction_engine")
public class DbDemandPredictionEngine {

    @Id @GeneratedValue
    public Long id;

    public String itemCode;

    public Integer pastDemand;

    public Integer predictedDemand;

    public String prediction;

    public static DbDemandPredictionEngine predict(String code, Integer past) {

        DbDemandPredictionEngine d = new DbDemandPredictionEngine();

        d.itemCode = code;
        d.pastDemand = past;

        if (code == null) {
            d.prediction = "INVALID";
        } else if (past == null) {
            d.prediction = "UNKNOWN";
        } else if (past >= 100) {
            d.prediction = "HIGH_DEMAND";
        } else if (past >= 50) {
            d.prediction = "MEDIUM_DEMAND";
        } else {
            d.prediction = "LOW_DEMAND";
        }

        d.predictedDemand = (past == null) ? 0 : past + 10;

        return d;
    }
}
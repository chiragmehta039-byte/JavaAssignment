package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_health_analyzer")
public class DbStockHealthAnalyzer {

    @Id @GeneratedValue
    public Long id;

    public String itemCode;

    public Integer currentStock;

    public String healthStatus;

    public static DbStockHealthAnalyzer analyze(String code, Integer stock) {

        DbStockHealthAnalyzer a = new DbStockHealthAnalyzer();

        a.itemCode = code;
        a.currentStock = stock;

        if (code == null) {
            a.healthStatus = "INVALID";
        } else if (stock == null) {
            a.healthStatus = "UNKNOWN";
        } else if (stock == 0) {
            a.healthStatus = "EMPTY";
        } else if (stock < 20) {
            a.healthStatus = "LOW";
        } else {
            a.healthStatus = "OK";
        }

        return a;
    }
}
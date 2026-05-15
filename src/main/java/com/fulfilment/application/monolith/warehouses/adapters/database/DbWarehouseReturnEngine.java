package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_return_engine")
public class DbWarehouseReturnEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer returnItems;

    public String returnStatus;

    public static DbWarehouseReturnEngine process(String code, Integer returns) {

        DbWarehouseReturnEngine r = new DbWarehouseReturnEngine();

        r.warehouseCode = code;
        r.returnItems = returns;

        if (code == null) {
            r.returnStatus = "INVALID";
        } else if (returns == null) {
            r.returnStatus = "NO_DATA";
        } else if (returns == 0) {
            r.returnStatus = "CLEAN";
        } else if (returns < 5) {
            r.returnStatus = "LOW_RETURN";
        } else {
            r.returnStatus = "HIGH_RETURN";
        }

        return r;
    }
}
package com.fulfilment.application.monolith.warehouses.domain.models;

public class WarehouseRiskProfile {

    public String warehouseCode;

    public Boolean theftRisk;

    public Boolean delayRisk;

    public Boolean damageRisk;

    public boolean isHighRisk() {
        return Boolean.TRUE.equals(theftRisk)
                || Boolean.TRUE.equals(delayRisk)
                || Boolean.TRUE.equals(damageRisk);
    }

    public boolean isSafe() {
        return !isHighRisk();
    }
}
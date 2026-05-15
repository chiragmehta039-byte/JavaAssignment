package com.fulfilment.application.monolith.warehouses.extra.bin;

import java.time.LocalDateTime;

public class WarehouseOperationalState {

    public Boolean active;
    public Boolean locked;
    public LocalDateTime lastUpdated;

    public boolean canOperate() {
        return Boolean.TRUE.equals(active)
                && !Boolean.TRUE.equals(locked);
    }
}
package com.fulfilment.application.monolith.warehouses.domain.models;

public class WarehouseUtilizationStats {

    public Integer inboundShipments;

    public Integer outboundShipments;

    public Integer pendingShipments;

    public boolean isBusy() {
        return (inboundShipments != null && inboundShipments > 100)
                || (outboundShipments != null && outboundShipments > 100);
    }

    public boolean isIdle() {
        return inboundShipments != null
                && outboundShipments != null
                && inboundShipments == 0
                && outboundShipments == 0;
    }
}
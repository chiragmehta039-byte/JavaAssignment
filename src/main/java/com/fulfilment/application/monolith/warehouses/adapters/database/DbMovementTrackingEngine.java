package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "movement_tracking_engine")
public class DbMovementTrackingEngine {

    @Id @GeneratedValue
    public Long id;

    public String itemCode;

    public Integer movedIn;

    public Integer movedOut;

    public String movementStatus;

    public static DbMovementTrackingEngine track(String code, Integer in, Integer out) {

        DbMovementTrackingEngine m = new DbMovementTrackingEngine();

        m.itemCode = code;
        m.movedIn = in;
        m.movedOut = out;

        if (code == null) {
            m.movementStatus = "INVALID";
        } else if (in == null || out == null) {
            m.movementStatus = "PARTIAL";
        } else if (in > out) {
            m.movementStatus = "POSITIVE";
        } else if (in < out) {
            m.movementStatus = "NEGATIVE";
        } else {
            m.movementStatus = "BALANCED";
        }

        return m;
    }
}
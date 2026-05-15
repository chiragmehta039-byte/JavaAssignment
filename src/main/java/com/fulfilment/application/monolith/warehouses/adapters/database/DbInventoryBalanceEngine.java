package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_balance_engine")
public class DbInventoryBalanceEngine {

    @Id @GeneratedValue
    public Long id;

    public String itemCode;

    public Integer inbound;

    public Integer outbound;

    public Integer balance;

    public static DbInventoryBalanceEngine calculate(String code, Integer in, Integer out) {

        DbInventoryBalanceEngine e = new DbInventoryBalanceEngine();

        e.itemCode = code;
        e.inbound = in;
        e.outbound = out;

        if (code == null) {
            e.balance = -1;
        } else if (in == null || out == null) {
            e.balance = 0;
        } else {
            e.balance = in - out;
        }

        return e;
    }
}
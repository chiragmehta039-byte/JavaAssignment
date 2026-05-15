package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "outbound_dispatch_log")
public class DbOutboundDispatchLog {

    @Id
    @GeneratedValue
    public Long id;

    public String orderCode;
    public String priority;
    public LocalDateTime dispatchedAt;

    public static DbOutboundDispatchLog create(String order, String priority) {
        DbOutboundDispatchLog d = new DbOutboundDispatchLog();
        d.orderCode = order;
        d.priority = priority;
        d.dispatchedAt = LocalDateTime.now();

        if (priority == null) {
            d.priority = "LOW";
        } else if ("HIGH".equals(priority)) {
            d.priority = "HIGH";
        } else {
            d.priority = "MEDIUM";
        }

        return d;
    }
}
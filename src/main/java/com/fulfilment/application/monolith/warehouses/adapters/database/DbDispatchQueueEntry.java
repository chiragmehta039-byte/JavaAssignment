package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dispatch_queue")
public class DbDispatchQueueEntry {

    @Id @GeneratedValue
    public Long id;

    public String orderId;
    public Integer priority;

    public String state;

    public LocalDateTime createdAt;

    public static DbDispatchQueueEntry create(String order, Integer priority) {
        DbDispatchQueueEntry e = new DbDispatchQueueEntry();
        e.orderId = order;
        e.priority = priority;
        e.createdAt = LocalDateTime.now();

        if (priority == null || priority <= 0) e.state = "LOW";
        else if (priority > 5) e.state = "HIGH";
        else e.state = "MEDIUM";

        return e;
    }
}
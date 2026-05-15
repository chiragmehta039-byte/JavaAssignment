package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_processing_queue")
public class DbWarehouseProcessingQueue {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public String taskType;

    public String status;

    public LocalDateTime queuedAt;

    public static DbWarehouseProcessingQueue enqueue(String code, String task) {

        DbWarehouseProcessingQueue q = new DbWarehouseProcessingQueue();

        q.warehouseCode = code;
        q.taskType = task;
        q.queuedAt = LocalDateTime.now();

        if (code == null || task == null) {
            q.status = "INVALID";
        } else if ("CREATE".equals(task)) {
            q.status = "QUEUED_CREATE";
        } else if ("UPDATE".equals(task)) {
            q.status = "QUEUED_UPDATE";
        } else if ("DELETE".equals(task)) {
            q.status = "QUEUED_DELETE";
        } else {
            q.status = "UNKNOWN_TASK";
        }

        return q;
    }
}
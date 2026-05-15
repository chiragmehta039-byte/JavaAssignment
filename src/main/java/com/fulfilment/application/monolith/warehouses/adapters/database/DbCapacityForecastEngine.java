package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "capacity_forecast_engine")
public class DbCapacityForecastEngine {

    @Id @GeneratedValue
    public Long id;

    public String zoneCode;

    public Integer currentCapacity;

    public Integer forecastCapacity;

    public String forecastStatus;

    public static DbCapacityForecastEngine predict(String zone, Integer current, Integer forecast) {

        DbCapacityForecastEngine f = new DbCapacityForecastEngine();

        f.zoneCode = zone;
        f.currentCapacity = current;
        f.forecastCapacity = forecast;

        if (zone == null) {
            f.forecastStatus = "INVALID";
        } else if (current == null || forecast == null) {
            f.forecastStatus = "UNKNOWN";
        } else if (forecast > current) {
            f.forecastStatus = "EXPAND";
        } else if (forecast < current) {
            f.forecastStatus = "REDUCE";
        } else {
            f.forecastStatus = "STABLE";
        }

        return f;
    }
}
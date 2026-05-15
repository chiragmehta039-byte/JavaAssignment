package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.extra.WarehouseAdvancedValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarehouseAdvancedValidatorTest {

    private Warehouse base() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "W1";
        w.location = "DELHI";
        w.capacity = 10;
        w.stock = 5;
        return w;
    }

    @Test void valid() {
        assertDoesNotThrow(() -> WarehouseAdvancedValidator.validate(base()));
    }

    @Test
    void nullWarehouse() {
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(null));
    }

    @Test void invalidCode() {
        Warehouse w = base();
        w.businessUnitCode = "";
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(w));
    }

    @Test void invalidLocation() {
        Warehouse w = base();
        w.location = "";
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(w));
    }

    @Test void invalidCapacity() {
        Warehouse w = base();
        w.capacity = 0;
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(w));
    }

    @Test void negativeStock() {
        Warehouse w = base();
        w.stock = -1;
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(w));
    }

    @Test void stockExceedsCapacity() {
        Warehouse w = base();
        w.stock = 20;
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseAdvancedValidator.validate(w));
    }
}
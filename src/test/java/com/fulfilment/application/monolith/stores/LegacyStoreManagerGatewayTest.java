package com.fulfilment.application.monolith.stores;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class LegacyStoreManagerGatewayTest {

    @Test
    void shouldCreateStoreOnLegacySystem() {

        LegacyStoreManagerGateway gateway =
                new LegacyStoreManagerGateway();

        Store store = new Store();
        store.name = "StoreA";
        store.quantityProductsInStock = 100;

        assertDoesNotThrow(() -> {
            gateway.createStoreOnLegacySystem(store);
        });
    }

    @Test
    void shouldUpdateStoreOnLegacySystem() {

        LegacyStoreManagerGateway gateway =
                new LegacyStoreManagerGateway();

        Store store = new Store();
        store.name = "StoreB";
        store.quantityProductsInStock = 200;

        assertDoesNotThrow(() -> {
            gateway.updateStoreOnLegacySystem(store);
        });
    }
}
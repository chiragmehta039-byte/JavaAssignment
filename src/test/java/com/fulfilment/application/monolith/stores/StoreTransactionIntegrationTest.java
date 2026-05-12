package com.fulfilment.application.monolith.stores;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@QuarkusTest
public class StoreTransactionIntegrationTest {

  @InjectMock
  LegacyStoreManagerGateway legacyGateway;

  private String safeName(String prefix) {
    String shortId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    return prefix + "_" + shortId;
  }

  @Test
  public void should_notify_legacy_only_on_successful_store_creation() {

    String uniqueName = safeName("IT_STORE");

    // IMPORTANT: clean state BEFORE execution
    clearInvocations(legacyGateway);

    // =========================
    // SUCCESS CASE
    // =========================
    given()
            .contentType("application/json")
            .body("{\"name\": \"" + uniqueName + "\", \"quantityProductsInStock\": 5}")
            .when()
            .post("/store")
            .then()
            .statusCode(201);

    await()
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
              verify(legacyGateway, times(1))
                      .createStoreOnLegacySystem(any(Store.class));
            });

    clearInvocations(legacyGateway);

    // =========================
    // FAILURE CASE (duplicate)
    // =========================
    given()
            .contentType("application/json")
            .body("{\"name\": \"" + uniqueName + "\", \"quantityProductsInStock\": 10}")
            .when()
            .post("/store")
            .then()
            .statusCode(500);

    await()
            .atMost(Duration.ofSeconds(3))
            .untilAsserted(() -> {
              verify(legacyGateway, times(1)) // still only first call exists
                      .createStoreOnLegacySystem(any(Store.class));
            });
  }
}
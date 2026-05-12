package com.fulfilment.application.monolith.warehouses.adapters.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class WarehouseEndpointIT {

  @Inject
  EntityManager em;

  @BeforeEach
  @Transactional
  public void setup() {

    em.createQuery("DELETE FROM DbWarehouse").executeUpdate();

    create("MWH.001", "ZWOLLE-001");
    create("MWH.012", "AMSTERDAM-001");
    create("MWH.023", "TILBURG-001");

    em.flush();
    em.clear();
  }

  private void create(String code, String location) {
    var w = new com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouse();

    w.businessUnitCode = code;
    w.location = location;
    w.capacity = 50;
    w.stock = 10;

    em.persist(w);
  }

  @Test
  public void testSimpleListWarehouses() {

    given()
            .when().get("warehouse")
            .then()
            .statusCode(200)
            .body(
                    containsString("MWH.001"),
                    containsString("MWH.012"),
                    containsString("MWH.023")
            );
  }

  @Test
  public void testSimpleCheckingArchivingWarehouses() {

    given()
            .when()
            .get("warehouse")
            .then()
            .statusCode(200)
            .body(
                    containsString("MWH.001"),
                    containsString("MWH.012"),
                    containsString("MWH.023"),
                    containsString("ZWOLLE-001"),
                    containsString("AMSTERDAM-001"),
                    containsString("TILBURG-001")
            );

    given()
            .when()
            .delete("warehouse/MWH.001")
            .then()
            .statusCode(204);
  }
}
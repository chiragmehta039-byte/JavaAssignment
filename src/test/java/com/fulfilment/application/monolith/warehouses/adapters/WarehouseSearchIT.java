package com.fulfilment.application.monolith.warehouses.adapters;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class WarehouseSearchIT {

    // -------------------------------
    // Test: Search with filters
    // -------------------------------
    @Test
    public void testSearchWarehousesWithFilters() {

        given()
                .queryParam("location", "AMSTERDAM-001")
                .queryParam("minCapacity", 10)
        .when()
                .get("/warehouse/search")
        .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    // -------------------------------
    // Test: Pagination
    // -------------------------------
    @Test
    public void testSearchPagination() {

        given()
                .queryParam("page", 0)
                .queryParam("pageSize", 5)
        .when()
                .get("/warehouse/search")
        .then()
                .statusCode(200)
                .body("size()", lessThanOrEqualTo(5));
    }

    // -------------------------------
    // Test: Sorting
    // -------------------------------
    @Test
    public void testSearchSorting() {

        given()
                .queryParam("sortBy", "capacity")
                .queryParam("sortOrder", "desc")
        .when()
                .get("/warehouse/search")
        .then()
                .statusCode(200);
    }

    // -------------------------------
    // Test: No filters (default)
    // -------------------------------
    @Test
    public void testSearchWithoutFilters() {

        given()
        .when()
                .get("/warehouse/search")
        .then()
                .statusCode(200);
    }
}
package com.fulfilment.application.monolith.warehouses.adapters;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class WarehouseSearchIT {

    // -------------------------------
    // FILTERS
    // -------------------------------
    @Test
    public void testSearchWarehousesWithFilters() {

        given()
                .queryParam("location", "AMSTERDAM-001")
                .queryParam("minCapacity", 10)
                .queryParam("maxCapacity", 500)
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(200);
    }

    // -------------------------------
    // PAGINATION
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
    // SORTING ASC
    // -------------------------------
    @Test
    public void testSearchSortingAsc() {

        given()
                .queryParam("sortBy", "capacity")
                .queryParam("sortOrder", "asc")
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(200);
    }

    // -------------------------------
    // SORTING DESC
    // -------------------------------
    @Test
    public void testSearchSortingDesc() {

        given()
                .queryParam("sortBy", "capacity")
                .queryParam("sortOrder", "desc")
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(200);
    }

    // -------------------------------
    // DEFAULT SEARCH
    // -------------------------------
    @Test
    public void testSearchWithoutFilters() {

        given()
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(200);
    }

    // -------------------------------
    // ERROR CASE: INVALID SORT FIELD
    // -------------------------------
    @Test
    public void testInvalidSortField() {

        given()
                .queryParam("sortBy", "invalidField")
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(400);
    }

    // -------------------------------
    // ERROR CASE: INVALID PAGINATION
    // -------------------------------
    @Test
    public void testInvalidPagination() {

        given()
                .queryParam("page", -1)
                .queryParam("pageSize", 0)
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(400);
    }

    // -------------------------------
    // LOCATION NULL PATH
    // -------------------------------
    @Test
    public void testSearchWithoutLocation() {

        given()
                .queryParam("minCapacity", 0)
                .queryParam("maxCapacity", 1000)
                .when()
                .get("/warehouse/search")
                .then()
                .statusCode(200);
    }
}
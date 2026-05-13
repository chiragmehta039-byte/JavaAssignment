package com.fulfilment.application.monolith.products;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testGetAllProducts() {
        given()
            .when().get("/product")
            .then()
            .statusCode(200);
    }

    @Test
    public void testCreateProduct() {

        String requestBody = """
        {
            "name": "Laptop",
            "description": "Gaming laptop",
            "price": 50000,
            "stock": 10
        }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().post("/product")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", is("Laptop"));
    }

    @Test
    public void testGetSingleProduct() {

        // First create product
        Integer id =
            given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "name": "Mouse",
                    "description": "Wireless mouse",
                    "price": 500,
                    "stock": 5
                }
                """)
            .when()
                .post("/product")
            .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Then fetch it
        given()
            .when().get("/product/" + id)
            .then()
            .statusCode(200)
            .body("name", is("Mouse"));
    }

    @Test
    public void testUpdateProduct() {

        Integer id =
            given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "name": "Keyboard",
                    "description": "Mechanical keyboard",
                    "price": 1500,
                    "stock": 3
                }
                """)
            .when()
                .post("/product")
            .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
            .contentType(ContentType.JSON)
            .body("""
            {
                "name": "Keyboard Updated",
                "description": "Updated version",
                "price": 2000,
                "stock": 2
            }
            """)
            .when()
                .put("/product/" + id)
            .then()
                .statusCode(200)
                .body("name", is("Keyboard Updated"));
    }

    @Test
    public void testDeleteProduct() {

        Integer id =
            given()
                .contentType(ContentType.JSON)
                .body("""
                {
                    "name": "Tablet",
                    "description": "Android tablet",
                    "price": 8000,
                    "stock": 4
                }
                """)
            .when()
                .post("/product")
            .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
            .when().delete("/product/" + id)
            .then()
            .statusCode(204);
    }

    @Test
    public void testGetInvalidProduct() {
        given()
            .when().get("/product/999999")
            .then()
            .statusCode(404);
    }
}
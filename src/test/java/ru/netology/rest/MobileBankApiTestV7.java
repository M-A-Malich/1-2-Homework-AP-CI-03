package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class MobileBankApiTestV7 {
    @Test
    void shouldMatchSchemaTest() {
      given()
            .baseUri("http://localhost:9999/api/v1")
      .when()
            .get("/demo/accounts")
      .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
            .body("every{ (it.currency == 'RUB' | it.currency == 'USD') }", is(true)) // Вариант №1
            .body("currency", hasItems("RUB", "USD")) // Вариант №2
            .body("findAll {it.id > 0}.currency", hasItems("RUB", "USD")) // Вариант №3
      ;

    }
}
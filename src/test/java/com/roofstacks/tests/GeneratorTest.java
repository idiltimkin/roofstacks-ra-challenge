package com.roofstacks.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class GeneratorTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://generator.swagger.io/";
    }

    @Test
    void get_clients() {
        when()
                .get("/api/gen/clients/ada")
                .prettyPeek()
                .then().statusCode(200)
                .body("projectName.opt", equalTo("projectName"));
    }

    @Test
    void post_new_client() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/api/gen/clients/android")
                .prettyPeek()
                .then().statusCode(400)
                .body("code", equalTo(1))
                .body("type", equalTo("error"));
    }
}
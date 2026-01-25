package com.joaodev.spacex_api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MissionControllerRA {

    @LocalServerPort
    private int port;

    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {

        RestAssured.port = port;

        existingId = "6973fcae89d8f1ef9014f1c7";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
    public void findAllShouldReturnOkWhenMissionNoArgumentsGiven() {
        given()
                .get("/rockets")
                .then()
                .statusCode(200)
                .body("content.size()", is(notNullValue()));
    }

    @Test
    public void findByIdShouldReturnMissionWhenIdExists() {

        given()
                .get("/missions/{id}", existingId)
                .then()
                .statusCode(200)
                .body("id", is("6973fcae89d8f1ef9014f1c7"))
                .body("missionName", is("Thaicom"))
                .body("wikipedia", is("https://en.wikipedia.org/wiki/Thaicom"))
                .body("description", is(notNullValue()));

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {

        given()
                .get("/missions/{id}", nonExistingId)
                .then()
                .statusCode(404)
                .body("error", equalTo("Recurso não encontrado"))
                .body("status", equalTo(404));

    }
}

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
public class RocketControllerRA {

    @LocalServerPort
    private int port;

    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp(){

        RestAssured.port = port;

        existingId = "5e9d0d95eda69955f709d1eb";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
	public void findAllShouldReturnOkWhenRocketNoArgumentsGiven() {
		given()
			.get("/rockets")
		.then()
			.statusCode(200)
            .body("content.size()", is(notNullValue()));
	}

    @Test
	public void findByIdShouldReturnRocketWhenIdExists() {		
		
		given()
			.get("/rockets/{id}", existingId)
		.then()
			.statusCode(200)
			.body("id", is("5e9d0d95eda69955f709d1eb"))
            .body("name", equalTo("Falcon 1"))
            .body("active", equalTo(false))
            .body("description", notNullValue())
            .body("_links.self.href", notNullValue());
		
	}

    @Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
		
		given()
			.get("/rockets/{id}", nonExistingId)
		.then()
			.statusCode(404)
			.body("error", equalTo("Recurso não encontrado"))
			.body("status", equalTo(404));
	
    }

    @Test
    public void findAllActiveShouldReturnPageRocketWhenActiveIsTrue(){

        given()
			.get("/rockets/active?active=true")
		.then()
			.statusCode(200)
            .body("content.size()", is(notNullValue()));
    }
}
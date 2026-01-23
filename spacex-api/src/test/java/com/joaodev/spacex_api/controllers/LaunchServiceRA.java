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
public class LaunchServiceRA {

    @LocalServerPort
    private int port;

    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {

        RestAssured.port = port;

        existingId = "697263cab5f990e3a82b2f4f";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
	public void findAllShouldReturnOkWhenLaunchNoArgumentsGiven() {
		given()
			.get("/rockets")
		.then()
			.statusCode(200)
            .body("content.size()", is(notNullValue()));
	}

        @Test
	public void findByIdShouldReturnLaunchWhenIdExists() {		
		
		given()
			.get("/launches/{id}", existingId)
		.then()
			.statusCode(200)
			.body("id", is("697263cab5f990e3a82b2f4f"))
            .body("flightNumber", equalTo(1))
            .body("missionName", equalTo("FalconSat"))
            .body("launchDateUtc", equalTo("2006-03-24T22:30:00.000Z"))
            .body("launchSuccess", equalTo(false))
            .body("details", notNullValue())
            .body("rocketId", equalTo("falcon1"));	
	}

      @Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {	
		
		given()
			.get("/launches/{id}", nonExistingId)
		.then()
			.statusCode(404)
			.body("error", equalTo("Recurso não encontrado"))
			.body("status", equalTo(404));
	
    }

    @Test
    public void findAllActiveShouldReturnPageLaunchWhenlaunchSuccessIsTrue(){

        given()
			.get("/launches/success?launchSuccess=true")
		.then()
			.statusCode(200)
            .body("content.size()", is(notNullValue()));
    }
}

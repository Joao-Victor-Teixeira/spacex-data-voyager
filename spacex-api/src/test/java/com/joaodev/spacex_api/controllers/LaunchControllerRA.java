package com.joaodev.spacex_api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.services.LaunchService;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.LaunchFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaunchControllerRA {

    @LocalServerPort
    private int port;

    @MockitoBean
    private LaunchService service;

    private Launch launchMock = LaunchFactory.createLaunch();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        launchMock.setId("5eb87cd9ffd86e000604b32a");
        launchMock.setMissionName("FalconSat");
        launchMock.setLaunchSuccess(true);
        
    }

    @Test
    @DisplayName("Deve retornar página de lançamentos com status 200")
    void shouldReturnPageOfLaunches_WhenFindAll() {
        
        Page<Launch> page = new PageImpl<>(List.of(launchMock));
        
        
        when(service.findAll(any(Pageable.class))).thenReturn(page);

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/launches")
        .then()
            .statusCode(200)
            
            .body("_embedded", notNullValue()) 
            
            .body("_embedded.launchDTOList[0].id", equalTo(launchMock.getId()))
            .body("page.totalElements", equalTo(1));
    }

    @Test
    @DisplayName("Deve retornar um lançamento específico por ID com status 200")
    void shouldReturnLaunch_WhenFindById() {
        
        when(service.findById("5eb87cd9ffd86e000604b32a")).thenReturn(launchMock);

        given()
            .pathParam("id", "5eb87cd9ffd86e000604b32a")
        .when()
            .get("/launches/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(launchMock.getId()))
            .body("missionName", equalTo(launchMock.getMissionName()))
            .body("_links.self.href", containsString("/launches/5eb87cd9ffd86e000604b32a"));
    }

    @Test
    @DisplayName("Deve retornar 404 quando o lançamento não existe")
    void shouldReturnNotFound_WhenLaunchDoesNotExist() {
        
        String nonExistentId = "999";
        
        when(service.findById(nonExistentId))
            .thenThrow(new ResourceNotFoundException("Recurso não encontrado"));

        given()
            .pathParam("id", nonExistentId)
        .when()
            .get("/launches/{id}")
        .then()
            
            .statusCode(404);
    }

    @Test
    @DisplayName("Deve retornar lançamentos filtrados por sucesso")
    void shouldReturnSuccessLaunches_WhenFindByLaunchSuccess() {
        
        Page<Launch> page = new PageImpl<>(List.of(launchMock));
        
        when(service.findByLaunchSuccess(eq(true), any(Pageable.class))).thenReturn(page);

        given()
            .queryParam("launchSuccess", "true")
        .when()
            .get("/launches/success")
        .then()
            .statusCode(200)
            .body("_embedded.launchDTOList", hasSize(1))
            .body("_embedded.launchDTOList[0].launchSuccess", equalTo(true));
    }
}
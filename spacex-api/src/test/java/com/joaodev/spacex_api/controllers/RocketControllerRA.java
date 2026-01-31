package com.joaodev.spacex_api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.services.RocketService;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.RocketFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketControllerRA {

    @LocalServerPort
    private int port;

    @MockitoBean
    private RocketService service;

    private Rocket rocketMock = RocketFactory.createRocket();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        rocketMock.setId("5e9d0d95eda69955f709d1eb");
        rocketMock.setName("Falcon 1");
        rocketMock.setActive(true);

    }

    @Test
    @DisplayName("Deve retornar página de foguetes com status 200")
    void shouldReturnPageOfRockets_WhenFindAll() {

        Page<Rocket> page = new PageImpl<>(List.of(rocketMock));

        when(service.findAll(any(Pageable.class))).thenReturn(page);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/rockets")
                .then()
                .statusCode(200)

                .body("_embedded", notNullValue())
                .body("_embedded.rocketDTOList[0].id", equalTo(rocketMock.getId()))
                .body("_embedded.rocketDTOList[0]._links.self.href", containsString("/rockets/" + rocketMock.getId()))
                .body("_embedded.rocketDTOList[0]._links.all-rockets.href", containsString("/rockets"))
                .body("page.totalElements", equalTo(1));
    }

    @Test
    @DisplayName("Deve retornar um foguete específica por id com status 200")
    public void shouldReturnRocket_WhenFindById() {

        when(service.findById("5e9d0d95eda69955f709d1eb")).thenReturn(rocketMock);

        given()
                .pathParam("id", "5e9d0d95eda69955f709d1eb")
                .when()
                .get("/rockets/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(rocketMock.getId()))
                .body("name", equalTo(rocketMock.getName()))
                .body("active", is(rocketMock.isActive()))
                .body("_links.self.href", containsString("/rockets/5e9d0d95eda69955f709d1eb"))
                .body("_links.all-rockets.href", containsString("/rockets"));

    }

    @Test
    @DisplayName("Deve retornar 404 quando a missão não existe")
    public void shouldReturnNotFound_WhenRocketDoesNotExist() {

        String nonExistingId = "999";

        when(service.findById(nonExistingId)).thenThrow(new ResourceNotFoundException("Recurso não encontrado"));

        given()
                .pathParam("id", nonExistingId)
                .when()
                .get("/rockets/{id}")
                .then()
                .body("error", equalTo("Recurso não encontrado"))
                .body("status", equalTo(404));
                

    }

    @Test
    @DisplayName("Deve retornar todos os foguetes filtrado por ativos")
    public void shoudReturActivesRockets_WhenFindAllActive() {

        Page<Rocket> page = new PageImpl<>(List.of(rocketMock));
        
        when(service.findAllActive(any(Pageable.class), eq(true))).thenReturn(page);
        
        given()
                .queryParam("active", "true")
                .when()
                .get("/rockets/active")
                .then()
                .statusCode(200)
                .body("_embedded.rocketDTOList", hasSize(1))
                .body("_embedded.rocketDTOList[0].active", equalTo(true));
    }
}
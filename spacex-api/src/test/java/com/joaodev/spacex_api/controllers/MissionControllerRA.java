package com.joaodev.spacex_api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
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

import com.joaodev.spacex_api.models.entities.Mission;
import com.joaodev.spacex_api.services.MissionService;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.MissionFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MissionControllerRA {

    @LocalServerPort
    private int port;

    @MockitoBean
    private MissionService service;

    private Mission missionMock = MissionFactory.createMission();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        missionMock.setId("6973fcae89d8f1ef9014f1c7");
        missionMock.setMissionName("Thaicom");
    }

    @Test
    @DisplayName("Deve retornar uma página de missões com status 200")
    public void shouldReturnPageOfMissions_WhenFindAll() {

        Page<Mission> page = new PageImpl<>(List.of(missionMock));

        when(service.findAll(any(Pageable.class))).thenReturn(page);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/missions")
                .then()
                .statusCode(200)
                .body("_embedded", notNullValue())
                .body("_embedded.missionDTOList[0].id", equalTo(missionMock.getId()))
                .body("_embedded.missionDTOList[0]._links.self.href",
                        containsString("/missions/" + missionMock.getId()))
                .body("_embedded.missionDTOList[0]._links.all-missions.href", containsString("/missions"))
                .body("page.totalElements", equalTo(1));
    }

    @Test
    @DisplayName("Deve retornar uma missão específica por id com status 200")
    public void shouldReturnMission_WhenFindById() {

        when(service.findById("6973fcae89d8f1ef9014f1c7")).thenReturn(missionMock);

        given()
                .pathParam("id", "6973fcae89d8f1ef9014f1c7")
                .when()
                .get("/missions/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(missionMock.getId()))
                .body("missionName", equalTo(missionMock.getMissionName()))
                .body("_links.self.href", containsString("/missions/6973fcae89d8f1ef9014f1c7"))
                .body("_links.all-missions.href", containsString("/missions"));

    }

    @Test
    @DisplayName("Deve retornar 404 quando a missão não existe")
    public void shouldReturnNotFound_WhenMissionDoesNotExist() {

        String nonExistentId = "999";

        when(service.findById(nonExistentId))
                .thenThrow(new ResourceNotFoundException("Recurso não encontrado"));
        given()
                .pathParam("id", nonExistentId)
                .when()
                .get("/missions/{id}")
                .then()

                .statusCode(404);

    }
}

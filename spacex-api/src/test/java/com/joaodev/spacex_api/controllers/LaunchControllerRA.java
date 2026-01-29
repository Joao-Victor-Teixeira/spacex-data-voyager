package com.joaodev.spacex_api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.services.LaunchService;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.LaunchFactory;

@WebMvcTest(controllers = LaunchController.class)
class LaunchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LaunchService service;

    private Launch launch;
    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {
        launch = LaunchFactory.createLaunch();
        existingId = "697263cab5f990e3a82b2f4f";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
    void findAllShouldReturnPagedModelWithLinks() throws Exception {

        Page<Launch> page = new PageImpl<>(
                List.of(launch),
                PageRequest.of(0, 10),
                1
        );

        when(service.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/launches")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$._links.self.href").exists())

                .andExpect(jsonPath("$._embedded.launchDTOList[0].id").exists())

                .andExpect(jsonPath("$._embedded.launchDTOList[0]._links.self.href").exists())
                .andExpect(jsonPath("$._embedded.launchDTOList[0]._links.all-launches.href").exists());
    }

    @Test
    void findByIdShouldReturnLaunchWhenIdExists() throws Exception {

        when(service.findById(existingId)).thenReturn(launch);

        mockMvc.perform(get("/launches/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.flightNumber").value(launch.getFlightNumber()))
                .andExpect(jsonPath("$.missionName").value(launch.getMissionName()))
                .andExpect(jsonPath("$.launchSuccess").value(launch.isLaunchSuccess()))
                .andExpect(jsonPath("$.rocketId").value(launch.getRocketId()))
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        when(service.findById(nonExistingId))
                .thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/launches/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void findByLaunchSuccessShouldReturnPagedModel() throws Exception {

        Page<Launch> page = new PageImpl<>(
                List.of(launch),
                PageRequest.of(0, 10),
                1
        );

        when(service.findByLaunchSuccess(eq(true), any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/launches/success")
                .param("launchSuccess", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.launchDTOList").isArray())
                .andExpect(jsonPath("$._links.self.href").exists());
    }
}

package com.joaodev.spacex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.services.MissionService;

@RestController
@RequestMapping(value = "/missions")
public class MissionController {

    @Autowired
    private MissionService service;

    @GetMapping(produces = "application/json")
    public Page<MissionDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
}

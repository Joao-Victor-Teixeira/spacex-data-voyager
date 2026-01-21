package com.joaodev.spacex_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.services.RocketService;

@RestController
@RequestMapping(value = "/rockets")
public class RocketController {

    @Autowired
    private RocketService service;

    @GetMapping
    public Page<RocketDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public RocketDTO findById(@PathVariable String id){
        return service.findById(id);
    }


    @GetMapping("/active")
    public Page<RocketDTO> findAllActive(Pageable pageable,@RequestParam(value = "active", defaultValue = "true") Boolean active){
        return service.findAllActive(pageable, active);
    }
  
}

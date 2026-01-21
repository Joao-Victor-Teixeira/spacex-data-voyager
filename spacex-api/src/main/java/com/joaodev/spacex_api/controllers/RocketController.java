package com.joaodev.spacex_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RocketDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public RocketDTO findById(@PathVariable String id){
        return service.findById(id);
    }


    @GetMapping("/active")
    public ResponseEntity<List<RocketDTO>> findAllActive(@RequestParam(value = "active", defaultValue = "active") Boolean active){
        List<RocketDTO> list = service.findAllActive(active);
        return ResponseEntity.ok().body(list);
    }
  
}

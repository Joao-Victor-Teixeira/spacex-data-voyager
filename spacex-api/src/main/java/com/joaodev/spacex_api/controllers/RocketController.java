package com.joaodev.spacex_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.services.RocketService;

@RestController
@RequestMapping(value = "/rockets")
public class RocketController {

    @Autowired
    private RocketService service;

    @GetMapping
    public ResponseEntity<List<RocketDTO>> findAll(){
        List<RocketDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}

package com.joaodev.spacex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.services.LaunchService;

@RestController
@RequestMapping(value = "/launches")
public class LaunchController {

    @Autowired
    private LaunchService service;

    @GetMapping
    public Page<LaunchDTO> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public LaunchDTO findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping(value = "/success")
    public Page<LaunchDTO> findByLaunchSucess(Pageable pageable, @RequestParam(defaultValue = "true") Boolean launchSucess){
        return service.findByLaunchSucess(launchSucess, pageable);
    }
}

package com.joaodev.spacex_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;

@Service
public class RocketService {

    @Autowired
    private RocketRepository repository;

    public List<RocketDTO> findAll(){
        List<Rocket> list = repository.findAll();
        return list.stream()
           .map(RocketDTO::new) 
           .collect(Collectors.toList());
    }
}

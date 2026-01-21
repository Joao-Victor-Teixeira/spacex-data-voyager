package com.joaodev.spacex_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

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


    public RocketDTO findById(String id){
        Rocket result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        RocketDTO dto = new RocketDTO(result);
        return dto;
    }

    public List<RocketDTO> findAllActive(Boolean active){
        List<Rocket> list = repository.findByActive(active);
        return list.stream()
           .map(RocketDTO::new) 
           .collect(Collectors.toList());
    }
}

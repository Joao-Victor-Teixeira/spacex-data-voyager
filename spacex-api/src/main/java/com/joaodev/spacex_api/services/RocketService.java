package com.joaodev.spacex_api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joaodev.spacex_api.controllers.RocketController;
import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class RocketService {

    @Autowired
    private RocketRepository repository;

    public Page<RocketDTO> findAll(Pageable pageable){
        Page<Rocket> result = repository.findAll(pageable);
        Page<RocketDTO> page = result.map(x -> new RocketDTO(x).add(linkTo(methodOn(RocketController.class).findAll(null)).withRel("Todos os foguetes"))
                                     .add(linkTo(methodOn(RocketController.class).findById(x.getId())).withRel("Clique aqui para ver o foguete " + x.getName().toString()))       
                                     .add(linkTo(methodOn(RocketController.class).findAllActive(null, true)).withRel("Clique aqui para ver os foguetes ativos"))
                                     .add(linkTo(methodOn(RocketController.class).findAllActive(null, false)).withRel("Clique aqui para ver os foguetes inativos")));
        return page;
            
    }


    public RocketDTO findById(String id){
        Rocket result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        RocketDTO dto = new RocketDTO(result).add(linkTo(methodOn(RocketController.class).findById(result.getId())).withSelfRel())
                        .add(linkTo(methodOn(RocketController.class).findAll(null)).withRel("Clique aqui para ver todos os foguetes"))
                        .add(linkTo(methodOn(RocketController.class).findAllActive(null, true)).withRel("Clique aqui para ver os foguetes ativos"))
                        .add(linkTo(methodOn(RocketController.class).findAllActive(null, false)).withRel("Clique aqui para ver os foguetes inativos"));
        
        return dto;
    }

    public Page<RocketDTO> findAllActive(Pageable pageable,Boolean active){
        Page<Rocket> result = repository.findByActive(active, pageable);
        Page<RocketDTO> page = result.map(RocketDTO::new); 
        return page;   
    }
}

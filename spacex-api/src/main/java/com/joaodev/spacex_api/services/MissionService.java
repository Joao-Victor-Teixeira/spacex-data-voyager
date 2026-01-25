package com.joaodev.spacex_api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.controllers.MissionController;
import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.models.entities.Mission;
import com.joaodev.spacex_api.repositories.MissionRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class MissionService {

    @Autowired
    private MissionRepository repository;

    @Transactional(readOnly = true)
    public Page<MissionDTO> findAll(Pageable pageable) {
        Page<Mission> result = repository.findAll(pageable);
        Page<MissionDTO> page = result.map(x -> new MissionDTO(x).add(linkTo(methodOn(MissionController.class).findAll(null)).withSelfRel())
                                      .add(linkTo(methodOn(MissionController.class).findById(x.getId())).withRel("Clique aqui para ver a missão " + x.getMissionName())));  
        return page;
    }

    @Transactional(readOnly = true)
    public MissionDTO findById(String id) {
        Mission result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        MissionDTO dto = new MissionDTO(result).add(linkTo(methodOn(MissionController.class).findById(result.getId())).withSelfRel())
                                               .add(linkTo(methodOn(MissionController.class).findAll(null)).withRel("Clique aqui para ver todas as missões"));     
        return dto;
    }
}

package com.joaodev.spacex_api.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.spacex_api.controllers.MissionController;
import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.models.entities.Mission;

@Component
public class MissionModelAssembler implements RepresentationModelAssembler<Mission, MissionDTO> {

    @Override
    public MissionDTO toModel(Mission entity) {
        
        MissionDTO dto = new MissionDTO(entity);

        dto.add(linkTo(methodOn(MissionController.class).findById(entity.getId())).withSelfRel());

        dto.add(linkTo(methodOn(MissionController.class).findAll(null, null)).withRel("all-missions"));
        
        return dto;
    }

}

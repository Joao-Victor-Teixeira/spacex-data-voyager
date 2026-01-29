package com.joaodev.spacex_api.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.spacex_api.controllers.RocketController;
import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;

@Component
public class RocketModelAssembler implements RepresentationModelAssembler<Rocket, RocketDTO> {

    @Override
    public RocketDTO toModel(Rocket entity) {
        RocketDTO dto = new RocketDTO(entity);
        
        dto.add(linkTo(methodOn(RocketController.class).findById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(RocketController.class).findAll(null, null)).withRel("all-rockets"));
        
        dto.add(linkTo(methodOn(RocketController.class).findAllRocketsActives(null, true, null)).withRel("rockets-active"));
        
        dto.add(linkTo(methodOn(RocketController.class).findAllRocketsActives(null, false, null)).withRel("rockets-inactive"));
        
        return dto; 
    }
}

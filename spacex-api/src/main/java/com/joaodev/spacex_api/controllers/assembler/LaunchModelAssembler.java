package com.joaodev.spacex_api.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.spacex_api.controllers.LaunchController;
import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;

@Component
public class LaunchModelAssembler implements RepresentationModelAssembler<Launch, LaunchDTO> {

    @Override
    public LaunchDTO toModel(Launch entity) {
        
        LaunchDTO dto = new LaunchDTO(entity); 

        dto.add(linkTo(methodOn(LaunchController.class).findAll(null, null)).withSelfRel());

        return dto;
    }


}

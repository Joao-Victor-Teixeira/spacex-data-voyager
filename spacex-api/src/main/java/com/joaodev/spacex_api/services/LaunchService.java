package com.joaodev.spacex_api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.controllers.LaunchController;
import com.joaodev.spacex_api.controllers.RocketController;
import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.repositories.LaunchRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class LaunchService {

    @Autowired
    private LaunchRepository repository;

    @Transactional(readOnly = true)
    public Page<LaunchDTO> findAll(Pageable pageable) {
        Page<Launch> result = repository.findAll(pageable);
        Page<LaunchDTO> page = result.map(x -> new LaunchDTO(x)
                .add(linkTo(methodOn(LaunchController.class).findAll(null)).withSelfRel()
                        .withTitle("Todos os lançamentos"))
                .add(linkTo(methodOn(LaunchController.class).findById(x.getId()))
                        .withRel("Clique aqui para ver o lançamento " + x.getMissionName().toString()))
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, true))
                        .withRel("Clique aqui para ver os lançamentos bem sucedidos"))
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, false))
                        .withRel("Clique aqui para ver os lançamentos que falharam"))
                .add(linkTo(methodOn(RocketController.class).findAll(null))
                        .withRel("all-rockets")));

        return page;
    }

    @Transactional(readOnly = true)
    public LaunchDTO findById(String id) {
        Launch result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        LaunchDTO dto = new LaunchDTO(result)
                .add(linkTo(methodOn(LaunchController.class).findById(result.getId())).withSelfRel())
                .add(linkTo(methodOn(LaunchController.class).findAll(null))
                        .withRel("Clique aqui para ver todos os lançamentos"))
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, true))
                        .withRel("Clique aqui para ver os lançamentos bem sucedidos"))
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, false))
                        .withRel("Clique aqui para ver os lançamentos que falharam"))
                .add(linkTo(methodOn(RocketController.class).findAll(null))
                        .withRel("all-rockets"));
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<LaunchDTO> findByLaunchSuccess(boolean launchSuccess, Pageable pageable) {
        Page<Launch> result = repository.findByLaunchSuccess(launchSuccess, pageable);
        Page<LaunchDTO> page = result.map(x -> new LaunchDTO(x)
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, true)).withSelfRel())
                .add(linkTo(methodOn(LaunchController.class).findAll(null))
                        .withRel("Clique aqui para ver todos os lançamentos"))
                .add(linkTo(methodOn(LaunchController.class).findById(x.getId()))
                        .withRel("Clique aqui para ver o lançamento " + x.getMissionName().toString()))
                .add(linkTo(methodOn(LaunchController.class).findByLaunchSuccess(null, false))
                        .withRel("Clique aqui para ver os lançamentos que falharam"))
                .add(linkTo(methodOn(RocketController.class).findAll(null))
                        .withRel("all-rockets")));

        return page;
    }

}

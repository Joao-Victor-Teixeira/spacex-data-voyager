package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class RocketService {

    @Autowired
    private RocketRepository repository;

    @Transactional(readOnly = true)
    public Page<Rocket> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Rocket findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<Rocket> findAllActive(Pageable pageable, Boolean active) {
        return repository.findByActive(active, pageable);
    }
}

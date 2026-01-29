package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.models.entities.Mission;
import com.joaodev.spacex_api.repositories.MissionRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class MissionService {

    @Autowired
    private MissionRepository repository;

    @Transactional(readOnly = true)
    public Page<Mission> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Mission findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));  
    }
}

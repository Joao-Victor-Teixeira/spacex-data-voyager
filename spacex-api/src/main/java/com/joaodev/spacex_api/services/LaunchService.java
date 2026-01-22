package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.repositories.LaunchRepository;

@Service
public class LaunchService {

    @Autowired
    private LaunchRepository repository;

    public Page<LaunchDTO> findAll(Pageable pageable){
        Page<Launch> result = repository.findAll(pageable);
        Page<LaunchDTO> page = result.map(x -> new LaunchDTO(x));
        return page;
    }
}

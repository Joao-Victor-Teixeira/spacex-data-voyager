package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.repositories.LaunchRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class LaunchService {

    @Autowired
    private LaunchRepository repository;

    @Transactional(readOnly = true)
    public Page<LaunchDTO> findAll(Pageable pageable){
        Page<Launch> result = repository.findAll(pageable);
        Page<LaunchDTO> page = result.map(x -> new LaunchDTO(x));
        return page;
    }

    @Transactional(readOnly = true)
    public LaunchDTO findById(String id){
        Launch result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        LaunchDTO dto = new LaunchDTO(result);
        return dto;
    }


    @Transactional(readOnly = true)
    public Page<LaunchDTO> findByLaunchSucess(boolean launchSuccess,Pageable pageable){
        Page<Launch> result = repository.findByLaunchSuccess(launchSuccess,pageable);
        Page<LaunchDTO> page = result.map(x -> new LaunchDTO(x));
        return page;
    }
}

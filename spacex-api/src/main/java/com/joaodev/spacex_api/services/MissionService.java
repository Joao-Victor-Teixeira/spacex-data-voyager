package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.models.entities.Mission;
import com.joaodev.spacex_api.repositories.MissionRepository;

@Service
public class MissionService {

    @Autowired
    private MissionRepository repository;

    @Transactional(readOnly = true)
    public Page<MissionDTO> findAll(Pageable pageable) {
        Page<Mission> result = repository.findAll(pageable);
        Page<MissionDTO> page = result.map(x -> new MissionDTO(x));
        return page;
    }
}

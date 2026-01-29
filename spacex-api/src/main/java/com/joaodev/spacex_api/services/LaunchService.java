package com.joaodev.spacex_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.repositories.LaunchRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;

@Service
public class LaunchService {

        @Autowired
        private LaunchRepository repository;

        @Transactional(readOnly = true)
        public Page<Launch> findAll(Pageable pageable) {
                return repository.findAll(pageable);
        }

        @Transactional(readOnly = true)
        public Launch findById(String id) {
          return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));             
        }

        @Transactional(readOnly = true)
        public Page<Launch> findByLaunchSuccess(boolean launchSuccess, Pageable pageable) {
                return repository.findByLaunchSuccess(launchSuccess, pageable);     
        }

}

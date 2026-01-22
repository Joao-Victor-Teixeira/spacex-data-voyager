package com.joaodev.spacex_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaodev.spacex_api.models.entities.Launch;


@Repository
public interface LaunchRepository extends MongoRepository<Launch, String> {

    Page<Launch>findByRocketId(String rocketId, Pageable pageable);

}

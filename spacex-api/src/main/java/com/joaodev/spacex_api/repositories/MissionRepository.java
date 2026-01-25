package com.joaodev.spacex_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaodev.spacex_api.models.entities.Mission;

@Repository
public interface MissionRepository extends MongoRepository<Mission, String> {

}

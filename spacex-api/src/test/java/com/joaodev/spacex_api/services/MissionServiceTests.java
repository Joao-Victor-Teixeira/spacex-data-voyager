package com.joaodev.spacex_api.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.joaodev.spacex_api.models.entities.Mission;
import com.joaodev.spacex_api.repositories.MissionRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.MissionFactory;

@ExtendWith(MockitoExtension.class)
public class MissionServiceTests {

    @InjectMocks
    private MissionService service;

    @Mock
    private MissionRepository repository;

    private Mission mission;
    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {
        mission = MissionFactory.createMission();
        existingId = "5e9d0d95eda69955f709d1eb";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    
    }

    @Test
    void findAllShouldReturnPagedMission() {
        Page<Mission> page = new PageImpl<>(List.of(mission));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findAll(pageable)).thenReturn(page);

        Page<Mission> result = service.findAll(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());

        Mission resultMission = result.getContent().get(0);

        Assertions.assertEquals(mission.getId(), resultMission.getId());
        Assertions.assertEquals(mission.getMissionName(), resultMission.getMissionName());
        Assertions.assertEquals(mission.getWikipedia(), resultMission.getWikipedia());
        Assertions.assertEquals(mission.getDescription(), resultMission.getDescription());

        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    void findByIdShouldReturnMissionWhenIdExists() {
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(mission));

        Mission resultMission = service.findById(existingId);

        Assertions.assertEquals(mission.getId(), resultMission.getId());
        Assertions.assertEquals(mission.getMissionName(), resultMission.getMissionName());
        Assertions.assertEquals(mission.getWikipedia(), resultMission.getWikipedia());
        Assertions.assertEquals(mission.getDescription(), resultMission.getDescription());

        Mockito.verify(repository).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> service.findById(nonExistingId));

        Mockito.verify(repository).findById(nonExistingId);
    }

}    

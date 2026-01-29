
package com.joaodev.spacex_api.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.RocketFactory;

@ExtendWith(MockitoExtension.class)
class RocketServiceTest {

    @InjectMocks
    private RocketService service;

    @Mock
    private RocketRepository repository;

    private Rocket rocket;
    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {
        rocket = RocketFactory.createRocket();
        existingId = "5e9d0d95eda69955f709d1eb";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
    void findAllShouldReturnPagedRocketDTO() {
        Page<Rocket> page = new PageImpl<>(List.of(rocket));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findAll(pageable)).thenReturn(page);

        Page<RocketDTO> result = service.findAll(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        RocketDTO dto = result.getContent().get(0);

        Assertions.assertEquals(rocket.getId(), dto.getId());
        Assertions.assertEquals(rocket.getName(), dto.getName());
        Assertions.assertEquals(rocket.getDescription(), dto.getDescription());
        Assertions.assertEquals(rocket.isActive(), dto.isActive());

        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    void findByIdShouldReturnRocketDTOWhenIdExists() {
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(rocket));

        RocketDTO result = service.findById(existingId);

        Assertions.assertEquals(rocket.getId(), result.getId());
        Assertions.assertEquals(rocket.getName(), result.getName());
        Assertions.assertEquals(rocket.getDescription(), result.getDescription());
        Assertions.assertEquals(rocket.isActive(), result.isActive());

        Mockito.verify(repository).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> service.findById(nonExistingId));

        Mockito.verify(repository).findById(nonExistingId);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void findAllActiveShouldReturnPagedRocketDTO(boolean active) {
        rocket.setActive(active);
        Page<Rocket> page = new PageImpl<>(List.of(rocket));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findByActive(active, pageable)).thenReturn(page);

        Page<RocketDTO> result = service.findAllActive(pageable, active);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        RocketDTO dto = result.getContent().get(0);

        Assertions.assertEquals(rocket.getId(), dto.getId());
        Assertions.assertEquals(rocket.getName(), dto.getName());
        Assertions.assertEquals(rocket.getDescription(), dto.getDescription());
        Assertions.assertEquals(active, dto.isActive());

        Mockito.verify(repository).findByActive(active, pageable);
    }
}


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

import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.repositories.LaunchRepository;
import com.joaodev.spacex_api.services.exceptions.ResourceNotFoundException;
import com.joaodev.spacex_api.tests.LaunchFactory;

@ExtendWith(MockitoExtension.class)
public class LaunchServiceTests {

    @InjectMocks
    private LaunchService service;

    @Mock
    private LaunchRepository repository;

    private Launch launch;
    private String existingId;
    private String nonExistingId;

    @BeforeEach
    void setUp() {
        launch = LaunchFactory.createLaunch();
        existingId = "697263cab5f990e3a82b2f4f";
        nonExistingId = "5e9d0d95eda69955f70921ea";
    }

    @Test
    void findAllShouldReturnPagedLaunch() {
        Page<Launch> page = new PageImpl<>(List.of(launch));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findAll(pageable)).thenReturn(page);

        Page<Launch> result = service.findAll(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        Launch resultLaunch = result.getContent().get(0);

        Assertions.assertEquals(launch.getId(), resultLaunch.getId());
        Assertions.assertEquals(launch.getFlightNumber(), resultLaunch.getFlightNumber());
        Assertions.assertEquals(launch.getMissionName(), resultLaunch.getMissionName());
        Assertions.assertEquals(launch.getLaunchDateUtc(), resultLaunch.getLaunchDateUtc());
        Assertions.assertEquals(launch.isLaunchSuccess(), resultLaunch.isLaunchSuccess());
        Assertions.assertEquals(launch.getDetails(), resultLaunch.getDetails());
        Assertions.assertEquals(launch.getRocketId(), resultLaunch.getRocketId());

        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    void findByIdShouldReturnLaunchWhenIdExists() {
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(launch));

        Launch result = service.findById(existingId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(launch.getId(), result.getId());
        Assertions.assertEquals(launch.getFlightNumber(), result.getFlightNumber());
        Assertions.assertEquals(launch.getMissionName(), result.getMissionName());
        Assertions.assertEquals(launch.getLaunchDateUtc(), result.getLaunchDateUtc());
        Assertions.assertEquals(launch.isLaunchSuccess(), result.isLaunchSuccess());
        Assertions.assertEquals(launch.getDetails(), result.getDetails());
        Assertions.assertEquals(launch.getRocketId(), result.getRocketId());

        Mockito.verify(repository).findById(existingId);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> service.findById(nonExistingId));

        Mockito.verify(repository).findById(nonExistingId);
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void findByLaunchSuccessShouldReturnPagedLaunch(boolean launchSuccess) {
        launch.setLaunchSuccess(launchSuccess);

        Page<Launch> page = new PageImpl<>(List.of(launch));
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(repository.findByLaunchSuccess(launchSuccess, pageable))
                .thenReturn(page);

        Page<Launch> result = service.findByLaunchSuccess(launchSuccess, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        Launch resultLaunch = result.getContent().get(0);

        Assertions.assertEquals(launch.getId(), resultLaunch.getId());
        Assertions.assertEquals(launchSuccess, resultLaunch.isLaunchSuccess());

        Mockito.verify(repository).findByLaunchSuccess(launchSuccess, pageable);
    }
}

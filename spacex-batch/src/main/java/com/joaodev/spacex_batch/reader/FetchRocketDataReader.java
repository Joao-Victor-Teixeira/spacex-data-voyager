package com.joaodev.spacex_batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.joaodev.spacex_batch.dto.RocketDTO;

@Component
public class FetchRocketDataReader implements ItemReader<RocketDTO> {

    public static Logger logger = LoggerFactory.getLogger(FetchRocketDataReader.class);
    private final String BASE_URL = "https://api.spacexdata.com/v4/rockets";
    private RestTemplate restTemplate = new RestTemplate();

    private List<RocketDTO> rockets = new ArrayList<>();
    private int rocketIndex = 0;

    @Override
    public RocketDTO read() throws Exception {
        if (rocketIndex < rockets.size()) {
            return rockets.get(rocketIndex++);
        }
        return null; 
    }

    private List<RocketDTO> fetchRocketsFromAPI() {
        try {
            logger.info("[READER] Buscando foguetes na API da SpaceX...");
            ResponseEntity<List<RocketDTO>> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RocketDTO>>() {}
            );
            return response.getBody() != null ? response.getBody() : new ArrayList<>();
        } catch (Exception e) {
            logger.error("Erro ao conectar na SpaceX: " + e.getMessage());
            throw e;
        }
    }

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        if (rockets.isEmpty()) {
            rockets.addAll(fetchRocketsFromAPI());
        }
    }
}
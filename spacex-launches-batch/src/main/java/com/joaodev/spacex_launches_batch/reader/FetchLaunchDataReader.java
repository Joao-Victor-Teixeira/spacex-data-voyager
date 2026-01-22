package com.joaodev.spacex_launches_batch.reader;

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

import com.joaodev.spacex_launches_batch.dto.LaunchDTO;

@Component
public class FetchLaunchDataReader implements ItemReader<LaunchDTO> {

    public static Logger logger = LoggerFactory.getLogger(FetchLaunchDataReader.class);
    private final String BASE_URL = "https://api.spacexdata.com/v3/launches";
    private RestTemplate restTemplate = new RestTemplate();
    
    private List<LaunchDTO> launches = new ArrayList<>();
    private int launchIndex = 0;

    @Override
    public LaunchDTO read() throws Exception {
        if (launchIndex < launches.size()) {
            return launches.get(launchIndex ++);
        }
        return null;
    }

    private List<LaunchDTO> fetchLaunchesFromAPI(){
        try {
            logger.info("[READER] Buscando lançamentos na API da SpaceX...");
            ResponseEntity<List<LaunchDTO>> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LaunchDTO>>() {}
            );
            return response.getBody() != null ? response.getBody() : new ArrayList<>();
        } catch (Exception e) {
            logger.error("Erro ao conectar na SpaceX: " + e.getMessage());
            throw e;
        }
    }

    @BeforeChunk
    public void beforeChunk(ChunkContext context){
        if (launches.isEmpty()) {
            launches.addAll(fetchLaunchesFromAPI());
        }
    }
}

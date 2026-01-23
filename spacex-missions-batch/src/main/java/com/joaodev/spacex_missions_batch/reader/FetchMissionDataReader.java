package com.joaodev.spacex_missions_batch.reader;

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

import com.joaodev.spacex_missions_batch.dto.MissionDTO;

@Component
public class FetchMissionDataReader implements ItemReader<MissionDTO> {

    public static Logger logger = LoggerFactory.getLogger(FetchMissionDataReader.class);
    private final String BASE_URL = "https://api.spacexdata.com/v3/missions";
    private RestTemplate restTemplate = new RestTemplate();

    private List<MissionDTO> missions = new ArrayList<>();
    private int missionIndex = 0;

    @Override
    public MissionDTO read() throws Exception {
        if (missionIndex < missions.size()) {
            return missions.get(missionIndex ++);
        }
        return null;
    }

    private List<MissionDTO> fetchMissionsFromAPI(){
        try {
            logger.info("[READER] Buscando missões na API da SpaceX...");
            ResponseEntity<List<MissionDTO>> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MissionDTO>>() {}
            );
            return response.getBody() != null ? response.getBody() : new ArrayList<>();
        } catch (Exception e) {
            logger.error("Erro ao conectar na SapceX: " + e.getMessage());
            throw e;
        }
    }

    @BeforeChunk
    public void beforeChunk(ChunkContext contexr) {
        if (missions.isEmpty()) {
            missions.addAll(fetchMissionsFromAPI());
        }
    }
}

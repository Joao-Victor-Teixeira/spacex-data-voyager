package com.joaodev.spacex_missions_batch.job.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.joaodev.spacex_missions_batch.domain.Mission;
import com.joaodev.spacex_missions_batch.dto.MissionDTO;

@Configuration
public class FetchMissionDataAndStoreInDatabaseConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("${chunkSize:10}")
    private int chunkSize;

    @Bean
    public ItemProcessor<MissionDTO, Mission> missonItemProcessor(){
        return dto ->{
            Mission mission = new Mission();
            mission.setId(dto.getId());
            mission.setMissionName(dto.getMissionName());
            mission.setWikipedia(dto.getWikipedia());
            mission.setDescription(dto.getDescription());
            return mission;
        };
    }

    @Bean
    public MongoItemWriter<Mission> missionWriter(MongoTemplate mongoTemplate){
        return new MongoItemWriterBuilder<Mission>()
                .template(mongoTemplate)
                .collection("missions")
                .build();
    }

    @Bean
    public Step fetchMissionDataAndStoreInDatabase(
            ItemReader<MissionDTO> fetchMissionDataReader,
            ItemProcessor<MissionDTO, Mission> missionProcessor,
            ItemWriter<Mission> missionWriter,
            JobRepository jobRepository) {

        return new StepBuilder("fetchMissionDataStep", jobRepository)
        .<MissionDTO, Mission>chunk(chunkSize, transactionManager)
        .reader(fetchMissionDataReader)
        .processor(missionProcessor)
        .writer(missionWriter)
        .build();        
    }
}

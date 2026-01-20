package com.joaodev.spacex_batch.step;

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

import com.joaodev.spacex_batch.domain.Rocket;
import com.joaodev.spacex_batch.dto.RocketDTO;

@Configuration
public class FetchRocketDataAndStoreInDatabaseConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("${chunckSize}")
    private int chunckSize;

    @Bean
    public ItemProcessor<RocketDTO, Rocket> rocketProcessor() {
        return dto -> {
            Rocket rocket = new Rocket();
            rocket.setId(dto.getId());
            rocket.setName(dto.getRocketName());
            rocket.setActive(dto.isActive());
            rocket.setDescription(dto.getDescription());
            return rocket;
        };
    }

    @Bean
    public MongoItemWriter<Rocket> rocketWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Rocket>()
                .template(mongoTemplate)
                .collection("rockets") 
                .build();
    }    

    @Bean
    public Step fetchRocketDataAndStoreInDatabase(
            ItemReader<RocketDTO> fetchRocketDataReader, 
            ItemProcessor<RocketDTO, Rocket> rocketProcessor,
            ItemWriter<Rocket> rocketWriter, 
            JobRepository jobRepository) {
        
        return new StepBuilder("fetchRocketDataStep", jobRepository)
                .<RocketDTO, Rocket>chunk(chunckSize, transactionManager) 
                .reader(fetchRocketDataReader)
                .processor(rocketProcessor) 
                .writer(rocketWriter)
                .build();
    }

    
}
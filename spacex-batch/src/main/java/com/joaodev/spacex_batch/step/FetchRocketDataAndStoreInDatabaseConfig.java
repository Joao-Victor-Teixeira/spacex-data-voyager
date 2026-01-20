package com.joaodev.spacex_batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.joaodev.spacex_batch.dto.RocketDTO;

@Configuration
public class FetchRocketDataAndStoreInDatabaseConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("${chunckSize}")
    private int chunckSize;

    @Bean
    public Step fetchRocketDataAndStoreInDatabase(
        ItemReader<RocketDTO> fetchRocketDataReader, 
        ItemWriter<RocketDTO> rocketWriter, 
        JobRepository jobRepository){
    
        return new StepBuilder("fetchRocketDataStep", jobRepository) 
            .<RocketDTO, RocketDTO>chunk(chunckSize, transactionManager)
            .reader(fetchRocketDataReader)
            .writer(rocketWriter) 
            .build();
}

    @Bean
    public ItemWriter<RocketDTO> rocketWriter() {
        return items -> items.forEach(System.out::println);
    }
}

package com.joaodev.spacex_launches_batch.step;

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

import com.joaodev.spacex_launches_batch.domain.Launch;
import com.joaodev.spacex_launches_batch.dto.LaunchDTO;

@Configuration
public class FetchLaunchDataAndStoreInDatabaseConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("${chunkSize}")
    private int chunkSize;

    @Bean
    public ItemProcessor<LaunchDTO, Launch> launItemProcessor() {
        return dto ->{
            Launch launch = new Launch();
            launch.setId(dto.getId());
            launch.setFlightNumber(dto.getFlightNumber());
            launch.setMissionName(dto.getMissionName());
            launch.setLaunchDateUtc(dto.getLaunchDateUtc());
            launch.setLaunchSuccess(dto.isLaunchSuccess());
            launch.setDetails(dto.getDetails());
            launch.setRocketId(dto.getRocket().getRocketId());
            return launch;
        };
    }

    @Bean
    public MongoItemWriter<Launch> launchWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Launch>()
                .template(mongoTemplate)
                .collection("launches")
                .build();
    }

    @Bean
    public Step fetchLaunchDataAndStoreInDatabase(
            ItemReader<LaunchDTO> fetchLaunchDataReader,
            ItemProcessor<LaunchDTO, Launch> launchProcessor,
            ItemWriter<Launch> launchWriter,
            JobRepository jobRepository) {

        return new StepBuilder("fetchLaunchDataStep", jobRepository)
                .<LaunchDTO, Launch>chunk(chunkSize, transactionManager)
                .reader(fetchLaunchDataReader)
                .processor(launchProcessor)
                .writer(launchWriter)
                .build();

    }
}

package com.joaodev.spacex_launches_batch.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FetchLaunchDataAndStoreInDatabaseConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Value("${chunckSize}")
    private int chunckSize;

    public Step fetchLaunchDataAndStoreInDatabase(ItemReader<LaunchDTO> fetchLaunchDataReader, JobRepository jobRepository){
        
    }
}

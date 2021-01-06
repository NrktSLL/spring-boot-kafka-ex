package com.nrkt.springbootkafkaex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync
public class TaskConfig {

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}

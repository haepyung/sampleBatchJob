package com.example.bacthjob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AgentThreadPoolConfig {

    public static final int CORE_TASK_POOL_SIZE = 24;
    public static final int MAX_TASK_POOL_SIZE = 128;

    @Bean()
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_TASK_POOL_SIZE);
        executor.setMaxPoolSize(MAX_TASK_POOL_SIZE);
        return executor;
    }
}

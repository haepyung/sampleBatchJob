package com.example.bacthjob.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("sampleJob").start(simleStep1()).build();
    }

    private Step simleStep1() {
        return stepBuilderFactory.get("sampleStpe1").tasklet((contribution, chunkContext) -> {
            log.info(">>>>>>>>>>>> STEP ONE~!");
            return RepeatStatus.FINISHED;
        }).build();
    }
}

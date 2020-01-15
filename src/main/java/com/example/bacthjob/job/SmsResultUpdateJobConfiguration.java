package com.example.bacthjob.job;

import com.example.bacthjob.job.listener.SmsResultUpdateJobListener;
import com.example.bacthjob.job.listener.SmsTransferJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SmsResultUpdateJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SmsResultUpdateJobListener smsResultUpdateJobListener;

    @Bean(name = "job_sms_result_update")
    public Job job() {
        return jobBuilderFactory.get("job_sms_result_update")
                .listener(smsResultUpdateJobListener)
                .start(simpleStep1()).build();
    }

    @JobScope
    private Step simpleStep1() {
        return stepBuilderFactory.get("resultUpdate1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> RESULT UPDATE STEP ONE~! ");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}

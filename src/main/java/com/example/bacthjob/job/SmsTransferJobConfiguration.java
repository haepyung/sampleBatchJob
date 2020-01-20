package com.example.bacthjob.job;

import com.example.bacthjob.config.AgentThreadPoolConfig;
import com.example.bacthjob.job.listener.SmsTransferJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SmsTransferJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SmsTransferJobListener smsTransferJobListener;
    private final AgentThreadPoolConfig agentThreadPoolConfig;

    @Bean(name = "job_sms_transfer")
    public Job job() {
        return jobBuilderFactory.get("job_sms_transfer")
                .listener(smsTransferJobListener)
                .start(simpleStep1()).build();
    }

    @JobScope
    private Step simpleStep2() {
        return stepBuilderFactory.get("sampleStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> SMS TRANSFER UPDATE STEP TWO~START ");
                    Thread.sleep(20000);
                    log.info(">>>>>>>>>>>> SMS TRANSFER UPDATE STEP TWO~END ");
                    return RepeatStatus.FINISHED;
                }).build();
                //.taskExecutor(agentThreadPoolConfig.executor()).throttleLimit(4).build();
    }

    @JobScope
    private Step simpleStep1() {
        return stepBuilderFactory.get("sampleStep")
                //.tasklet(resultUpdateTasklet).build();
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> SMS TRANSFER UPDATE STEP ONE~! ");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}

package com.example.bacthjob.job;

import com.example.bacthjob.job.decider.SmsResultUpdateDecider;
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
    private final SmsResultUpdateDecider smsResultUpdateDecider;

    @Bean(name = "job_sms_result_update")
    public Job job() {
        return jobBuilderFactory.get("job_sms_result_update")
                .listener(smsResultUpdateJobListener)
                .start(simpleStep1())
                .next(smsResultUpdateDecider)
                .from(smsResultUpdateDecider)
                    .on("SUCCESS")
                    .to(simpleStep2())
                .from(smsResultUpdateDecider)
                    .on("FAIL")
                    .to(simpleStep3())
                .end()
                .build();
    }

    @JobScope
    private Step simpleStep3() {
        return stepBuilderFactory.get("resultUpdate3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> RESULT UPDATE STEP FAIL! ");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @JobScope
    private Step simpleStep2() {
        return stepBuilderFactory.get("resultUpdate2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> RESULT UPDATE STEP SUCCESS! ");
                    return RepeatStatus.FINISHED;
                }).build();
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

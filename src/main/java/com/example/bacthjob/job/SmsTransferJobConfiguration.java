package com.example.bacthjob.job;

import com.example.bacthjob.config.AgentThreadPoolConfig;
import com.example.bacthjob.job.listener.SmsTransferJobListener;
import com.example.bacthjob.job.partitioner.SmsTransferPartitioner;
import com.example.bacthjob.job.processor.SmsTransferProcessor;
import com.example.bacthjob.job.reader.SmsTransferReader;
import com.example.bacthjob.job.writer.SmsTransferWriter;
import com.example.bacthjob.model.Users;
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

    private final SmsTransferReader smsTransferReader;
    private final SmsTransferProcessor smsTransferProcessor;
    private final SmsTransferWriter smsTransferWriter;
    private final SmsTransferPartitioner smsTransferPartitioner;

    private static final int chunkSize = 1000;

    @Bean(name = "job_sms_transfer")
    public Job job() {
        return jobBuilderFactory.get("job_sms_transfer")
                .listener(smsTransferJobListener)
                .start(simpleStep1()).build();
    }

    @JobScope
    private Step simpleStep4() {
        return stepBuilderFactory.get("sampleStep4")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> SMS TRANSFER UPDATE STEP FOUR~! ");
                    return RepeatStatus.FINISHED;
                })
                .taskExecutor(agentThreadPoolConfig.executor()).throttleLimit(4).build();
    }

    @JobScope
    private Step simpleStep3() {
        return stepBuilderFactory.get("sampleStep3")
                .partitioner("slaveStep", smsTransferPartitioner)
                .build();
    }

    @JobScope
    private Step simpleStep2() {
        return stepBuilderFactory.get("sampleStep")
                .<Users, Users>chunk(chunkSize)
                .reader(smsTransferReader)
                .processor(smsTransferProcessor)
                .writer(smsTransferWriter)
                .build();

    }

    @JobScope
    private Step simpleStep1() {
        return stepBuilderFactory.get("sampleStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>>>>>>>> SMS TRANSFER UPDATE STEP ONE~! ");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}

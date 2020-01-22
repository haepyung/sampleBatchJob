package com.example.bacthjob.job.decider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class SmsResultUpdateDecider implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

        int randomNum = new Random().nextInt() + 1;
        log.info("num:: " + randomNum);


        return randomNum % 2 == 0 ? new FlowExecutionStatus("SUCCESS") : new FlowExecutionStatus("FAIL");
    }
}

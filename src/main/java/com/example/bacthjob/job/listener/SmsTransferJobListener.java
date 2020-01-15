package com.example.bacthjob.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsTransferJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info(">>>>>> SmsTransferJobListener - start");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info(">>>>>> SmsTransferJobListener - end");
	}
}

package com.example.bacthjob.job.schedule;

import com.example.bacthjob.job.SmsTransferJobConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SmsTransferSchedule implements BaseScheduler {

    @Qualifier("job_sms_transfer") @Autowired private Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "*/10 * * * * ?")
    @Override
    public void process() {

        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("input.file.name", "TEST")
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}

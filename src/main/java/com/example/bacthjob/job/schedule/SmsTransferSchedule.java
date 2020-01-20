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

    @Scheduled(cron = "*/10 * * * * ?") // TODO 해당 부분은 설정 파일 에서가져오도록 변경 처리
    @Override
    public void process() {

        //TODO 여기서 TRANSFER 사용 여부 체크
        log.info("시작은 하는데 JOB은 안되니?");

        //TODO 20, 70 테이블 select
        try {

            for (int i = 0; i < 3; i++)
            {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addString("input.file.name", "TEST")
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();

                log.info("시작 번호:: " + i);
                jobLauncher.run(job, jobParameters);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}

package com.example.bacthjob.job.processor;

import com.example.bacthjob.model.Users;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SmsTransferProcessor implements ItemProcessor<Users, Users> {
    @Override
    public Users process(Users item) throws Exception {
        return null;
    }
}

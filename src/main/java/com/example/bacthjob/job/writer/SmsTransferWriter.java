package com.example.bacthjob.job.writer;

import com.example.bacthjob.model.Users;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsTransferWriter implements ItemWriter<Users> {

    @Override
    public void write(List items) throws Exception {

    }
}

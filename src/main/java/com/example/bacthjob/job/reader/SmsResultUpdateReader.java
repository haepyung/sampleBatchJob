package com.example.bacthjob.job.reader;


import com.example.bacthjob.model.Users;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class SmsResultUpdateReader implements ItemReader<Users> {

    @Override
    public Users read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}

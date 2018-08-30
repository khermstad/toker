package com.khermstad.toker.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@Component
public class RepositoryUtil {

    public Timestamp getCurrentTimestamp(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return Timestamp.valueOf(dateFormat.format(calendar.getTime()));
    }

}

package com.pg.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DateTimeUtils {

    private static String zoneId;

    @Autowired
    public void setZoneId(@Value("${date.time.utils.zoneid}") String zoneId){
        DateTimeUtils.zoneId=zoneId;
    }

    public static Long currentMillis(){
        return Instant.now().toEpochMilli();
    }
}

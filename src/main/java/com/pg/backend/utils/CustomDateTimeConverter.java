package com.pg.backend.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import static com.pg.backend.constants.ApplicationConstants.DATE_TIME_FORMATTER;

@Component
@Converter
public class CustomDateTimeConverter implements AttributeConverter<Long, String> {

    private static String zoneId;

    @Autowired
    public void setZoneId(@Value("${custom.date.converter.zoneid}") String zoneId) {
        CustomDateTimeConverter.zoneId = zoneId;
    }

    @Override
    public String convertToDatabaseColumn(Long timeInMillis) {
        if (!Objects.isNull(timeInMillis)) {
            LocalDateTime localDateTime = Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.of(zoneId)).toLocalDateTime();
            return DATE_TIME_FORMATTER.format(localDateTime);
        }
        return null;
    }

    @Override
    public Long convertToEntityAttribute(String dbDateTime) {
        if (Objects.isNull(dbDateTime) || dbDateTime.equals("0000-00-00 00:00:00") || dbDateTime.isEmpty()) {
            return null;
        }
        String validDateString = dbDateTime.length() > 26 ? dbDateTime.substring(0, 26) : dbDateTime;
        LocalDateTime localDateTime = LocalDateTime.parse(validDateString,DATE_TIME_FORMATTER);
        Instant instant = localDateTime.atZone(ZoneId.of(zoneId)).toInstant();
        return instant.toEpochMilli();
    }
}

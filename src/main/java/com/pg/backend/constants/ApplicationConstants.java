package com.pg.backend.constants;

import java.time.format.DateTimeFormatter;

public class ApplicationConstants {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String SUCCESS_MESSAGE = "success";
    public static final Integer SUCCESS_STATUS_CODE = 200;

}

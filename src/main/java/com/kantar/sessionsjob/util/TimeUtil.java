package com.kantar.sessionsjob.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class TimeUtil {
    private TimeUtil() {
    }

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public static DateTime parseStringToDateTime(String textDate) {
        return formatter.parseDateTime(textDate);
    }

    public static String dateTimeToString(DateTime dateTime) {
        return formatter.print(dateTime);
    }
}

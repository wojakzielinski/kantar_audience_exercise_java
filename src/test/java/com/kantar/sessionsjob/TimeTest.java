package com.kantar.sessionsjob;

import com.kantar.sessionsjob.util.TimeUtil;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTest {

    @Test
    void testTextToDateParsing() {
        String actualTextDate = "20210703133017";
        DateTime expectedTime = new DateTime(2021, 7, 3, 13, 30, 17);
        DateTime actualTime = TimeUtil.parseStringToDateTime(actualTextDate);
        assertEquals(expectedTime.getMillis(), actualTime.getMillis());
        assertEquals(TimeUtil.dateTimeToString(expectedTime), actualTextDate);
    }
}

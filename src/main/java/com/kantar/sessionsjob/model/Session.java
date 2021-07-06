package com.kantar.sessionsjob.model;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

@Setter
@Getter
public class Session {
    private int homeNo;
    private int channel;
    private DateTime startTime;
    private Activity activity;
    private DateTime endTime;
    private Seconds duration;
}

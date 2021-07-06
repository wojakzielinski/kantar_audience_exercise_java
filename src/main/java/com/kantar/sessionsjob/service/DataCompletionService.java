package com.kantar.sessionsjob.service;

import com.kantar.sessionsjob.model.Session;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.Seconds;

import java.util.List;

public class DataCompletionService {
    private final DateTimeComparator dateOnlyComparator = DateTimeComparator.getDateOnlyInstance();

    public void completeSessionData(List<Session> sessions) {
        Session previousSession = null;
        for (Session session : sessions) {
            session.setEndTime(obtainSessionEndTime(previousSession, session));
            session.setDuration(calculateDuration(session));
            previousSession = session;
        }
    }

    private DateTime obtainSessionEndTime(Session previousSession, Session currentSession) {
        if (isTheSameHomeNoOrDay(previousSession, currentSession)) {
            return previousSession.getStartTime().minusSeconds(1);
        } else {
            return currentSession.getStartTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        }
    }

    private boolean isTheSameHomeNoOrDay(Session previousSession, Session currentSession) {
        return previousSession != null //skip first loop
                && previousSession.getHomeNo() == currentSession.getHomeNo()
                && dateOnlyComparator.compare(previousSession.getStartTime(), currentSession.getStartTime()) == 0;
    }

    private Seconds calculateDuration(Session session) {
        Seconds duration = Seconds.secondsBetween(session.getStartTime(), session.getEndTime());
        //add the last full second in session
        return duration.plus(1);
    }
}

package com.kantar.sessionsjob;

import com.kantar.sessionsjob.model.Activity;
import com.kantar.sessionsjob.model.Session;
import com.kantar.sessionsjob.service.DataCompletionService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletingDataTest {

    @Test
    void testCompletingData() {
        //get test data and complete end time and duration
        DataCompletionService dataCompletionService = new DataCompletionService();
        List<Session> testStatements = getTestStatements();
        dataCompletionService.completeSessionData(testStatements);
        //check later session
        Session laterSession = testStatements.get(0);
        assertEquals(laterSession.getEndTime().getMillis(), laterSessionEndTime().getMillis());
        assertEquals(19800, laterSession.getDuration().getSeconds());
        //check earlier session
        Session earlierSession = testStatements.get(1);
        assertEquals(earlierSession.getEndTime().getMillis(), earlierSessionEndTime().getMillis());
        assertEquals(1800, earlierSession.getDuration().getSeconds());
    }

    private List<Session> getTestStatements() {
        List<Session> sessions = new ArrayList<>(2);
        sessions.add(getExpectedLaterStatement());
        sessions.add(getExpectedEarlierStatement());
        return sessions;
    }

    private Session getExpectedLaterStatement() {
        Session session = new Session();
        session.setHomeNo(1234);
        session.setChannel(102);
        session.setStartTime(new DateTime(2020, 1, 1, 18, 30, 0));
        session.setActivity(Activity.LIVE);
        return session;
    }

    private DateTime laterSessionEndTime() {
        return new DateTime(2020, 1, 1, 23, 59, 59);
    }

    private Session getExpectedEarlierStatement() {
        Session session = new Session();
        session.setHomeNo(1234);
        session.setChannel(101);
        session.setStartTime(new DateTime(2020, 1, 1, 18, 0, 0));
        session.setActivity(Activity.LIVE);
        return session;
    }

    private DateTime earlierSessionEndTime() {
        return new DateTime(2020, 1, 1, 18, 29, 59);
    }
}

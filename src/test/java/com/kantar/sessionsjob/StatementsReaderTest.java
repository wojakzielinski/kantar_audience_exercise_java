package com.kantar.sessionsjob;

import com.kantar.sessionsjob.model.Activity;
import com.kantar.sessionsjob.model.Session;
import com.kantar.sessionsjob.service.PsvFileReader;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatementsReaderTest {
    @Test
    void testStatementsReader() throws IOException {
        PsvFileReader psvFileReader = new PsvFileReader();
        List<Session> expected = getExpectedStatements();
        List<Session> actual = psvFileReader.readStatements("src/test/resources/unit-test-file.psv");
        assertEquals(expected.size(), actual.size());
        assertTrue(EqualsBuilder.reflectionEquals(expected.get(0), actual.get(0)));
        assertTrue(EqualsBuilder.reflectionEquals(expected.get(1), actual.get(1)));
    }

    private List<Session> getExpectedStatements() {
        List<Session> sessions = new ArrayList<>(2);
        sessions.add(getExpectedFirstStatement());
        sessions.add(getExpectedSecondStatement());
        return sessions;
    }

    private Session getExpectedFirstStatement() {
        Session session = new Session();
        session.setHomeNo(1234);
        session.setChannel(102);
        session.setStartTime(new DateTime(2020, 1, 1, 18, 30, 0));
        session.setActivity(Activity.LIVE);
        return session;
    }


    private Session getExpectedSecondStatement() {
        Session session = new Session();
        session.setHomeNo(1234);
        session.setChannel(101);
        session.setStartTime(new DateTime(2020, 1, 1, 18, 0, 0));
        session.setActivity(Activity.LIVE);
        return session;
    }
}

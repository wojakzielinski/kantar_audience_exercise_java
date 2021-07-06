package com.kantar.sessionsjob.service;

import com.kantar.sessionsjob.model.Activity;
import com.kantar.sessionsjob.model.Session;
import com.kantar.sessionsjob.util.TimeUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PsvFileReader {
    public List<Session> readStatements(String pathToFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToFile));
        //remove file header with the names of columns
        lines.remove(0);
        return stringsToStatements(lines);
    }

    private List<Session> stringsToStatements(List<String> textList) {
        return textList
                .stream()
                .map(this::getSessionFromText)
                .sorted(getSessionComparator())
                .collect(Collectors.toList());
    }

    private Session getSessionFromText(String text) {
        String columnSeparatorRegex = "\\|";
        String[] cells = text.split(columnSeparatorRegex);
        Session session = new Session();
        session.setHomeNo(Integer.parseInt(cells[0]));
        session.setChannel(Integer.parseInt(cells[1]));
        session.setStartTime(TimeUtil.parseStringToDateTime(cells[2]));
        session.setActivity(Activity.getActivityByText(cells[3]));
        return session;
    }

    private Comparator<Session> getSessionComparator() {
        return Comparator.comparing(Session::getHomeNo).thenComparing(Session::getStartTime, Comparator.reverseOrder());
    }
}

package com.kantar.sessionsjob.service;

import com.kantar.sessionsjob.model.Session;
import com.kantar.sessionsjob.util.TimeUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PsvFileWriter {
    private static final String OUTPUT_PSV_HEADER = "HomeNo|Channel|Starttime|Activity|EndTime|Duration";

    public void writeSessionsToFile(List<Session> sessions, String outputFilePath) throws IOException {
        List<String> linesToWrite = sessions
                .stream()
                .map(this::sessionToOutputLine)
                .collect(Collectors.toList());
        linesToWrite.add(0, OUTPUT_PSV_HEADER);
        Files.write(Paths.get(outputFilePath), linesToWrite);
    }


    private String sessionToOutputLine(Session session) {
        return String.format("%d|%d|%s|%s|%s|%d",
                session.getHomeNo(),
                session.getChannel(),
                TimeUtil.dateTimeToString(session.getStartTime()),
                session.getActivity().getActivityText(),
                TimeUtil.dateTimeToString(session.getEndTime()),
                session.getDuration().getSeconds());
    }
}

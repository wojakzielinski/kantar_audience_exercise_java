package com.kantar.sessionsjob;

import com.kantar.sessionsjob.model.Session;
import com.kantar.sessionsjob.service.DataCompletionService;
import com.kantar.sessionsjob.service.PsvFileReader;
import com.kantar.sessionsjob.service.PsvFileWriter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class Main {
    private static PsvFileReader fileReader = new PsvFileReader();
    private static DataCompletionService dataCompletionService = new DataCompletionService();
    private static PsvFileWriter fileWriter = new PsvFileWriter();
    // See README.txt for usage example

    public static void main(String[] args) {

        if (args.length < 2) {
            log.error("Missing arguments: <input-statements-file> <output-sessions-file>");
            System.exit(1);
        }
        try {
            String inputFilePath = args[0];
            String outputFilePath = args[1];
            log.info("Reading statements from file: " + inputFilePath);
            List<Session> sessions = fileReader.readStatements(inputFilePath);
            log.info("Completing sessions data");
            dataCompletionService.completeSessionData(sessions);
            log.info("Session data completed, writing sessions to file: " + outputFilePath);
            fileWriter.writeSessionsToFile(sessions, outputFilePath);
            log.info("Program successfully executed.");
            System.exit(0);
        } catch (IOException e) {
            log.error("Exception occurred during session processing: ", e);
            System.exit(1);
        }
    }
}

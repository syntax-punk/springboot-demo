package com.example.springboot101.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RunJsonDataLoadr implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoadr.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoadr(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            try (var inputStream = getClass().getResourceAsStream("/data/runs.json")) {
                var allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON file and saving them in DB", allRuns.runs().size());

                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                log.error("Failed to load runs", e);
            }
        } else {
            log.info("Not loading runs, data already exists");
        }
    }
}

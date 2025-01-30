package com.example.springboot101.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    void create(Run run) {
        runs.add(run);
    }

    void update(Integer id, Run run) {
        var existingRun = findById(id);

        if (existingRun.isEmpty())
            throw new RunNotFoundException();

        runs.set(runs.indexOf(existingRun.get()), run);
    }

    void delete (Integer id) {
        runs.removeIf(r -> r.id() == id);
    }

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
            .filter(run -> run.id() == id)
            .findFirst();
    }

    @PostConstruct
    private void init() {
        runs.add(
            new Run(1,
                "Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(45, ChronoUnit.MINUTES),
                5,
                Location.OUTDOOR));

        runs.add(
            new Run(2,
                "Evening Run",
                LocalDateTime.now().minus(1, ChronoUnit.DAYS),
                LocalDateTime.now().minus(1, ChronoUnit.DAYS).plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));
    }
}

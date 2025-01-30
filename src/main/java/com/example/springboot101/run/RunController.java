package com.example.springboot101.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping
    public List<Run> getRuns() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        var run = runRepository.findById(id);

        if (run.isEmpty())
            throw new RunNotFoundException();

        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(id, run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }
}

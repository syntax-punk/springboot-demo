package com.example.springboot101.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        runRepository.deleteById(id);
    }

    @GetMapping("/location/{location}")
    public List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location.toUpperCase());
    }
}

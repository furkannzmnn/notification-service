package com.example.notificationservice.controller;

import com.example.notificationservice.model.Log;
import com.example.notificationservice.repository.LogRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/all/{size}")
    public List<Log> getAllLogs(@PathVariable int size) {
        Iterable<Log> all = logRepository.findAll(Pageable.ofSize(size));
        List<Log> logs = new ArrayList<>();
        all.forEach(logs::add);
        return logs;
    }

    @GetMapping("/clear")
    public void clearLogs() {
        logRepository.deleteAll();
    }

    @GetMapping("/count")
    public long countLogs() {
        return logRepository.count();
    }

    @GetMapping("/search")
    public List<Log> searchMessages(String message) {
        return logRepository.findByMessage(message);
    }
}

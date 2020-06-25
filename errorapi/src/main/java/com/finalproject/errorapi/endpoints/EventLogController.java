package com.finalproject.errorapi.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.finalproject.errorapi.entity.EventLog;
import com.finalproject.errorapi.entity.EventLogDTO;
import com.finalproject.errorapi.entity.Level;
import com.finalproject.errorapi.service.EventLogService;

@RestController
public class EventLogController {

    @Autowired
    private EventLogService eventLogService;

    @GetMapping(value = "/eventlog")
    public ResponseEntity<List<EventLogDTO>> findAll(@RequestParam(required = false) Optional<Level> level,
                                                    @RequestParam(required = false) Optional<String> description,
                                                    @RequestParam(required = false) Optional<String> source,
                                                    @RequestParam(required = false) Optional<Integer> quantity,
                                                    @RequestParam(defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy) {
        List<EventLog> results = eventLogService.findAll(pageNumber, pageSize, sortBy);
        if (level.isPresent())
            results = results.stream()
                            .filter(eventLog -> eventLog.getLevel().equals(level.get()))
                            .collect(Collectors.toList());
        if (description.isPresent())
            results = results.stream()
                            .filter(eventLog -> eventLog.getDescription().equals(description.get()))
                            .collect(Collectors.toList());
        if (source.isPresent())
            results = results.stream()
                            .filter(eventLog -> eventLog.getSource().equals(source.get()))
                            .collect(Collectors.toList());
        if (quantity.isPresent())
            results = results.stream()
                            .filter(eventLog -> eventLog.getQuantity().equals(quantity.get()))
                            .collect(Collectors.toList());
        List<EventLogDTO> dtoResults = results.stream()
                                                .map(eventLog -> new EventLogDTO().convertEventLogToDTO(eventLog))
                                                .collect(Collectors.toList());
            return new ResponseEntity<List<EventLogDTO>>(dtoResults, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/eventlog/{id}")
    public ResponseEntity<EventLog> findById(@PathVariable(value = "id") Long id) {
        Optional<EventLog> eventLog = eventLogService.findById(id);
        if (eventLog.isPresent())
            return new ResponseEntity<EventLog>(eventLog.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/eventlog")
    public EventLog createEventLog(@RequestBody @Valid EventLog eventLog) {
        return eventLogService.save(eventLog);
    }
}
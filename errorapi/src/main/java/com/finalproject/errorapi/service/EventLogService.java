package com.finalproject.errorapi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.finalproject.errorapi.entity.EventLog;
import com.finalproject.errorapi.entity.Level;
import com.finalproject.errorapi.repository.EventLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventLogService implements EventLogServiceInterface {

    @Autowired
    private EventLogRepository eventLogRepository;

    @Override
    public Optional<EventLog> findById(Long id) {
        return eventLogRepository.findById(id);
    }

    @Override
    public List<EventLog> findAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<EventLog> pagedResult = eventLogRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<EventLog>();
        }
    }

    @Override
    public List<EventLog> findByDecription(String description) {
        return eventLogRepository.findByDescription(description);
    }

    @Override
    public List<EventLog> findBySource(String source) {
        return eventLogRepository.findBySource(source);
    }

    @Override
    public List<EventLog> findByDate(Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EventLog> findByQuantity(Integer quantity) {
        return eventLogRepository.findByQuantity(quantity);
    }

    @Override
    public List<EventLog> findByLevel(Level level) {
        return eventLogRepository.findByLevel(level);
    }

    public EventLog save(@Valid EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }
}
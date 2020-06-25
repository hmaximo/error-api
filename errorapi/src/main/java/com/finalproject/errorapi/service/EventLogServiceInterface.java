package com.finalproject.errorapi.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.finalproject.errorapi.entity.EventLog;
import com.finalproject.errorapi.entity.Level;

public interface EventLogServiceInterface {
    
    Optional<EventLog> findById(Long id);

    List<EventLog> findAll(Integer pageNumber, Integer pageSize, String sortBy);
    
    List<EventLog> findByLevel(Level level);

    List<EventLog> findByDecription(String description);

    List<EventLog> findBySource(String source);

    List<EventLog>  findByDate(Date date);

    List<EventLog> findByQuantity(Integer quantity);
}
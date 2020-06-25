package com.finalproject.errorapi.repository;

import java.util.List;

import com.finalproject.errorapi.entity.EventLog;
import com.finalproject.errorapi.entity.Level;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends PagingAndSortingRepository<EventLog, Long> {

    List<EventLog> findByLevel(Level level);

    List<EventLog> findByDescription(String description);

    List<EventLog> findBySource(String source);

    List<EventLog> findByQuantity(Integer quantity);
}
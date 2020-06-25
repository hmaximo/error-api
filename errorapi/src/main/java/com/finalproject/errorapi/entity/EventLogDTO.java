package com.finalproject.errorapi.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventLogDTO {

	private Long id;

    private Level level;

    private String description;

    private String source;

    private LocalDateTime createdAt;

    private Integer quantity;

    public EventLogDTO convertEventLogToDTO(EventLog eventLog) {
        return new EventLogDTO(eventLog.getId(), eventLog.getLevel(), eventLog.getDescription(), 
                                eventLog.getSource(), eventLog.getCreatedAt(), eventLog.getQuantity());
    }
}
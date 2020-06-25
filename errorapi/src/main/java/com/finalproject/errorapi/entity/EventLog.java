package com.finalproject.errorapi.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O level não pode ser nulo.")
    private Level level;

    @NotBlank(message = "A descrição não pode ser nula.")
    private String description;

    @NotBlank(message = "A mensagem de log não pode ser nula.")
    private String log;

    @NotBlank(message = "A origem não pode ser nula.")
    private String source;

    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull(message = "A quantidade não pode ser nula.")
    private Integer quantity;
}
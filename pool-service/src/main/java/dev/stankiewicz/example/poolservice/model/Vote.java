package dev.stankiewicz.example.poolservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Option option;

    private String userId;

    private LocalDateTime voteDate;

    @PrePersist
    public void prePersist() {
        voteDate = LocalDateTime.now();
    }
}

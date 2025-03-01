package com.example.HackathonServer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;
    @NonNull
    private long duration;
    @NonNull
    private Type gameType;
    @NonNull
    private int score;
    @NonNull
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @PrePersist
    public void initializeTime() {
        startTime = LocalDateTime.now();

    }

    @PreUpdate
    public void calculateDuration()
    {
        if (startTime != null && endTime != null) {
            // Calculate the duration in seconds or minutes
            this.duration = Duration.between(startTime, endTime).getSeconds(); // or getMinutes()
        }
    }

    public enum Type
    {
        PHYSICAL("PHYSICAL"),
        INTELLECTUAL("INTELLECTUAL");

        private String value;
        Type(String value)
        {
            this.value = value;
        }
    }

}

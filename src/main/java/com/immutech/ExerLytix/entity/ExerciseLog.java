package com.immutech.ExerLytix.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercise_log",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "date"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member user;

    private LocalDate date;
    private int pushUp = 0;
    private int pullUp = 0;
    private int squat = 0;
    private int walk = 0;
    private int sitUp = 0;
    private int bicepCurl = 0;
    private int shoulderPress = 0;

    private float duration = 0.0f;
    private float calories = 0.0f;
    private int totalExercisesCompleted = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public ExerciseLog(Member user) {
        this.user = user;
        this.date = LocalDate.now();
    }
}

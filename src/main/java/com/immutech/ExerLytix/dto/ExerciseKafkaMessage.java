package com.immutech.ExerLytix.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseKafkaMessage {
    private Long user_id;
    private String exercise_type;
    private int count;
    private int elapsed_time;
    private long start_time;
    private long timestamp;
}

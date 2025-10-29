package com.immutech.ExerLytix.dto;

import lombok.Data;

@Data
public class ExerciseRequest {
    private int userId;
    private String exercise;
    private int count;
    private float duration;
}

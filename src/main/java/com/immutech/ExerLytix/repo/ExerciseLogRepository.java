package com.immutech.ExerLytix.repo;

import com.immutech.ExerLytix.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    Optional<ExerciseLog> findByUserAndDate(Member user, LocalDate date);
    boolean existsByUserAndDate(Member user, LocalDate date);
}

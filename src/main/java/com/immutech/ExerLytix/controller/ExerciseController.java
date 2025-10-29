package com.immutech.ExerLytix.controller;

import com.immutech.ExerLytix.dto.ExerciseRequest;
import com.immutech.ExerLytix.entity.*;
import com.immutech.ExerLytix.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExerciseController {

    @Autowired
    private ExerciseLogRepository logRepo;

    @Autowired
    private MemberRepository userRepo;

    // 🧩 API endpoint to update exercise
    @PostMapping("/exercise/update")
    public ResponseEntity<?> updateExercise(@RequestBody ExerciseRequest req) {

        try {
            int userId=req.getUserId();
            Member user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            LocalDate today = LocalDate.now();


            ExerciseLog log = logRepo.findByUserAndDate(user, today)
                    .orElseGet(() -> {
                        ExerciseLog newLog = new ExerciseLog(user);
                        return logRepo.save(newLog);
                    });

            int count = req.getCount();
            String exercise = req.getExercise().toLowerCase();



            // Update count
            switch (exercise) {
                case "push-up": log.setPushUp(log.getPushUp() + count); break;
                case "pull-up": log.setPullUp(log.getPullUp() + count); break;
                case "squat": log.setSquat(log.getSquat() + count); break;
                case "walk": log.setWalk(log.getWalk() + count); break;
                case "sit-up": log.setSitUp(log.getSitUp() + count); break;
                case "bicep": log.setBicepCurl(log.getBicepCurl() + count); break;
                case "shoulder-press": log.setShoulderPress(log.getShoulderPress() + count); break;
                case "shoulder-raise": log.setShoulderPress(log.getShoulderPress() + count); break;
                default:
                    throw new RuntimeException("Invalid exercise type: " + exercise);
            }

            // Duration & Calories calculation
            float duration = req.getDuration();

            log.setDuration(log.getDuration() + duration);

            float calories = calculateCalories(exercise, count);
            log.setCalories(log.getCalories() + calories);

            // Total exercises completed
            log.setTotalExercisesCompleted(calculateCompletedExercises(log));

            ExerciseLog updatedLog = logRepo.save(log);


            //printing all data on console
            System.out.println("---------------------------------");
            System.out.println("    Data of Current Exercise");
            System.out.println("---------------------------------");
            System.out.println("user_Id: "+userId);
            System.out.println("exercise: "+exercise);
            System.out.println("total_reps: "+count);
            System.out.println("duration: "+duration);
            System.out.println("calories_burned: "+calories);

            return ResponseEntity.ok(updatedLog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/exercise/latest/{userId}")
    public ResponseEntity<?> getLatestExercise(@PathVariable Integer userId) {
        try {
            Member user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            LocalDate today = LocalDate.now();

            ExerciseLog log = logRepo.findByUserAndDate(user, today)
                    .orElseThrow(() -> new RuntimeException("No log found for today"));

            return ResponseEntity.ok(log);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private float calculateCalories(String exercise, int count) {
        switch (exercise) {
            case "push-up": return count * 0.5f;
            case "pull-up": return count * 1.0f;
            case "squat": return count * 0.4f;
            case "walk": return count * 0.03f;
            case "sit-up": return count * 0.45f;
            case "bicep": return count * 0.6f;
            case "shoulder-press": return count * 0.7f;
            case "shoulder-raise": return count * 0.7f;
            default: return 0;
        }
    }

    private int calculateCompletedExercises(ExerciseLog log) {
        int total = 0;
        if (log.getPushUp() > 0) total++;
        if (log.getPullUp() > 0) total++;
        if (log.getSquat() > 0) total++;
        if (log.getWalk() > 0) total++;
        if (log.getSitUp() > 0) total++;
        if (log.getBicepCurl() > 0) total++;
        if (log.getShoulderPress() > 0) total++;
        return total;
    }
}

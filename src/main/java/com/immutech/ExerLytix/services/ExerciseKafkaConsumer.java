//package com.immutech.ExerLytix.services;
//
//import com.immutech.ExerLytix.dto.ExerciseKafkaMessage;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ExerciseKafkaConsumer {
//
//    @KafkaListener(topics = "exercise", groupId = "exercise_group", containerFactory = "kafkaListenerFactory")
//    public void consume(ExerciseKafkaMessage message) {
//        System.out.println("📩 Received Kafka Message: " + message);
//    }
//}
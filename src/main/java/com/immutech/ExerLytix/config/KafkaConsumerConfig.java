//package com.immutech.ExerLytix.config;
//
//import com.immutech.ExerLytix.dto.ExerciseKafkaMessage;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    public ConsumerFactory<String, ExerciseKafkaMessage> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//
//        // Kafka broker connection
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
//        // Consumer group ID
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "exercise_group");
//
//        // Key & Value deserializers
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        // Trust the package containing your DTO
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.immutech.ExerLytix.dto");
//
//        return new DefaultKafkaConsumerFactory<>(
//                props,
//                new StringDeserializer(),
//                new JsonDeserializer<>(ExerciseKafkaMessage.class, false)
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, ExerciseKafkaMessage> kafkaListenerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, ExerciseKafkaMessage> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//}

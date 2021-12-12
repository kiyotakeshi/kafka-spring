package com.kiyotakeshi.kafka.libraryeventsconsumer.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
public class LibraryEventsConsumerConfig {

//    // copied by org/springframework/boot/autoconfigure/kafka/KafkaAnnotationDrivenConfiguration.java#kafkaListenerContainerFactory
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
//            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
//            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        configurer.configure(factory, kafkaConsumerFactory);
//        // @see https://docs.spring.io/spring-kafka/reference/html/#committing-offsets
//        // @see https://spring.pleiades.io/spring-kafka/docs/current/reference/html/#ooo-commits
//        // factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//
//        // factory.setConcurrency(3);
//        // 2021-12-13 08:43:05.096  INFO 35878 --- [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : library-events-group: partitions assigned: [library-events-0]
//        // 2021-12-13 08:43:05.096  INFO 35878 --- [ntainer#0-1-C-1] o.s.k.l.KafkaMessageListenerContainer    : library-events-group: partitions assigned: [library-events-1]
//        // 2021-12-13 08:43:05.096  INFO 35878 --- [ntainer#0-2-C-1] o.s.k.l.KafkaMessageListenerContainer    : library-events-group: partitions assigned: [library-events-2]
//        return factory;
//    }
}

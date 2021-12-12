package com.kiyotakeshi.kafka.libraryeventsconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumer {

    // use ConcurrentKafkaListenerContainerFactory default
    // @see
    // org/springframework/boot/autoconfigure/kafka/KafkaAutoConfiguration.java -> KafkaAnnotationDrivenConfiguration
    // org/springframework/boot/autoconfigure/kafka/KafkaAnnotationDrivenConfiguration.java#kafkaListenerContainerFactory
    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        log.info("ConsumerRecord: {} ", consumerRecord);
    }
}

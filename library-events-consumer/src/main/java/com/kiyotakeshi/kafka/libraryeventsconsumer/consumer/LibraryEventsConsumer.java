package com.kiyotakeshi.kafka.libraryeventsconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiyotakeshi.kafka.libraryeventsconsumer.service.LibraryEventsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumer {

    private final LibraryEventsService libraryEventsService;

    public LibraryEventsConsumer(LibraryEventsService libraryEventsService) {
        this.libraryEventsService = libraryEventsService;
    }

    // use ConcurrentKafkaListenerContainerFactory default
    // @see
    // org/springframework/boot/autoconfigure/kafka/KafkaAutoConfiguration.java -> KafkaAnnotationDrivenConfiguration
    // org/springframework/boot/autoconfigure/kafka/KafkaAnnotationDrivenConfiguration.java#kafkaListenerContainerFactory
    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord: {} ", consumerRecord);
        libraryEventsService.processLibraryEvent(consumerRecord);
    }
}

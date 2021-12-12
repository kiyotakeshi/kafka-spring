package com.kiyotakeshi.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiyotakeshi.kafka.domain.Book;
import com.kiyotakeshi.kafka.domain.LibraryEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryEventProducerTest {

    @Mock
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    LibraryEventProducer eventProducer;

    @Test
    void sendLibraryEvent_Approach2_failure() throws JsonProcessingException {
        // given
        var book = Book.builder()
                .bookId(12)
                .bookAuthor("Taro")
                .bookName("Kafka with Spring Boot")
                .build();

        var libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        var future = new SettableListenableFuture();
        future.setException(new RuntimeException("Exception Calling Kafka"));
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        // expected
        assertThrows(Exception.class, () -> eventProducer.sendLibraryEvent_Approach2(libraryEvent).get());
    }
}

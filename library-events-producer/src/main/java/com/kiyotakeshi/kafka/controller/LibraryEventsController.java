package com.kiyotakeshi.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiyotakeshi.kafka.domain.LibraryEvent;
import com.kiyotakeshi.kafka.domain.LibraryEventType;
import com.kiyotakeshi.kafka.producer.LibraryEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/v1")
@Slf4j
public class LibraryEventsController {

    private LibraryEventProducer libraryEventProducer;

    public LibraryEventsController(LibraryEventProducer libraryEventProducer) {
        this.libraryEventProducer = libraryEventProducer;
    }

    @PostMapping("/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        // invoke kafka producer
        log.info("before send libraryevent");
        libraryEvent.setLibraryEventType(LibraryEventType.NEW);

        // libraryEventProducer.sendLibraryEvent(libraryEvent);
        libraryEventProducer.sendLibraryEvent_Approach2(libraryEvent);

        // SendResult<Integer, String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
        // log.info("send result is {}", sendResult.toString());

        // display this message before LibraryEventProducer.java#handleSuccess
        // kafkaTemplate.send exec asynchronously
        log.info("after send libraryevent");

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    @PutMapping("/libraryevent")
    public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        if (libraryEvent.getLibraryEventId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the LibraryEventId");
        }

        libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
        // if you want to send a message to same partition, make sure you pass the same key
        // Message send successfully for the key : 123 and the value is {"libraryEventId":123,"libraryEventType":"UPDATE","book":{"bookId":456,"bookName":"Kafka Using Spring Boot","bookAuthor":"Taro"}}, partition is 1
        libraryEventProducer.sendLibraryEvent_Approach2(libraryEvent);
        return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
    }
}

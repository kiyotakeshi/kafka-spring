package com.kiyotakeshi.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiyotakeshi.kafka.domain.LibraryEvent;
import com.kiyotakeshi.kafka.domain.LibraryEventType;
import com.kiyotakeshi.kafka.producer.LibraryEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        // invoke kafka producer

        log.info("before send libraryevent");
        libraryEvent.setLibraryEventType(LibraryEventType.NEW);
        // libraryEventProducer.sendLibraryEvent(libraryEvent);
        libraryEventProducer.sendLibraryEvent_Approach2(libraryEvent);
        // SendResult<Integer, String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
        // log.info("send result is {}", sendResult.toString());
        log.info("after send libraryevent");

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}

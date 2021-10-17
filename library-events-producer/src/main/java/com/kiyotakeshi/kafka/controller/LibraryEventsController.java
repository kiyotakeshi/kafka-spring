package com.kiyotakeshi.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kiyotakeshi.kafka.domain.LibraryEvent;
import com.kiyotakeshi.kafka.producer.LibraryEventProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LibraryEventsController {


    @PostMapping("/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {

        // invoke kafka producer

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}

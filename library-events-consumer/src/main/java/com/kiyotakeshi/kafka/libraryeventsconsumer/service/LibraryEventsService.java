package com.kiyotakeshi.kafka.libraryeventsconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiyotakeshi.kafka.libraryeventsconsumer.domain.LibraryEvent;
import com.kiyotakeshi.kafka.libraryeventsconsumer.repository.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventsService {

    private final ObjectMapper objectMapper;

    private final LibraryEventsRepository libraryEventsRepository;

    public LibraryEventsService(ObjectMapper objectMapper, LibraryEventsRepository libraryEventsRepository) {
        this.objectMapper = objectMapper;
        this.libraryEventsRepository = libraryEventsRepository;
    }

    public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        var libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("libraryEvent: {}", libraryEvent);

        switch (libraryEvent.getLibraryEventType()) {
            case NEW:
                // save
                save(libraryEvent);
                break;
            case UPDATE:
                // update
                validate(libraryEvent);
                save(libraryEvent);
                break;
            default:
                log.info("Invalid Library Event Type");
        }
    }

    private void validate(LibraryEvent libraryEvent) {
        if (libraryEvent.getLibraryEventId() == null) {
            throw new IllegalArgumentException("Library Event Id is missing");
        }

        Optional<LibraryEvent> libraryEventOptional = libraryEventsRepository.findById(libraryEvent.getLibraryEventId());
        if (!libraryEventOptional.isPresent()) {
            throw new IllegalArgumentException("Not a valid library Event");
        }
        log.info("Validation is successful for the Library Event: {}", libraryEventOptional.get());
    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventsRepository.save(libraryEvent);
        log.info("Successfully Persisted the Library Event {}", libraryEvent);
    }
}

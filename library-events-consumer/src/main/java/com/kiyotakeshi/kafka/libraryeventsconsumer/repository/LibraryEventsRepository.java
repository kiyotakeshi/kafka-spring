package com.kiyotakeshi.kafka.libraryeventsconsumer.repository;

import com.kiyotakeshi.kafka.libraryeventsconsumer.domain.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent, Integer> {
}

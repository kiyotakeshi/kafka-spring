package com.kiyotakeshi.kafka.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryEvent {
    private Integer libraryEventId;
    private Book book;
}

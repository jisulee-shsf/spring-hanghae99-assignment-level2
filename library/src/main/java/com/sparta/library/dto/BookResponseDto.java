package com.sparta.library.dto;

import com.sparta.library.entity.Book;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {

    private Long bookId;
    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime registeredDate;

    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.registeredDate = book.getRegisteredDate();
    }

}

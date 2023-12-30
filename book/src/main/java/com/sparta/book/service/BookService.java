package com.sparta.book.service;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.dto.SuccessResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(requestDto);
        Book saveBook = bookRepository.save(book);
        BookResponseDto bookResponseDto = new BookResponseDto(saveBook);
        return bookResponseDto;
    }

    public BookResponseDto getBook(Long id) {
        return bookRepository.findById(id).map(BookResponseDto::new).orElseThrow(() ->
                new IllegalArgumentException("해당 도서 정보를 찾을 수 없습니다."));
    }

    public List<BookResponseDto> getBooks() {
        return bookRepository.findAllByOrderByRegisteredDate().stream().map(BookResponseDto::new).toList();
    }

    @Transactional
    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        Book book = findBook(id);
        book.update(requestDto);
        return new BookResponseDto(book);
    }

    public SuccessResponseDto deleteBook(Long id) {
        Book book = findBook(id);
        bookRepository.delete(book);
        return new SuccessResponseDto(true);
    }

    private Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 도서 정보를 찾을 수 없습니다."));
    }
}
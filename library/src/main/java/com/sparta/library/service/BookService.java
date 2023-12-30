package com.sparta.library.service;

import com.sparta.library.dto.BookRequestDto;
import com.sparta.library.dto.BookResponseDto;
import com.sparta.library.entity.Book;
import com.sparta.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 도서 등록 (요청데이터-> 엔티티 -> 반환데이터)
    @Transactional
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(requestDto);
        Book saveBook = bookRepository.save(book);
        return new BookResponseDto(saveBook);

    }

    // 선택 도서 호출
    public BookResponseDto findBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new EntityNotFoundException("도서를 찾을 수 없습니다."));
        return new BookResponseDto(book);
    }

    // 전체 도서목록 호출
    public List<BookResponseDto> findAllBook() {
        return bookRepository.findAllByOrderByRegisteredDateDesc().stream().map(BookResponseDto::new).toList();
    }
}

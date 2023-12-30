package com.sparta.library.controller;

import com.sparta.library.dto.*;
import com.sparta.library.service.BookService;
import com.sparta.library.service.LoanService;
import com.sparta.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * BookService, MemberService, LoanService
 * -> RestController
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {
    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;


    /**
     * 도서 등록 (post)
     * @param requestDto 입력받은 도서정보
     * @return 입력받아 등록한 도서정보
     */
    @PostMapping("/books")
    public BookResponseDto createBook (@RequestBody BookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    /**
     * 선택한 도서 정보 조회 (get)
     * @param bookId book pk
     * @return book id와 일치하는 도서정보
     */
    @GetMapping("/books/{bookId}")
    public BookResponseDto getBookInfo (@PathVariable Long bookId) {
        return bookService.findBook(bookId);
    }

    /**
     * 전체 도서 목록 조회 (get)
     * @return 등록되어있는 도서정보 목록 내림차순 정렬
     */
    @GetMapping("/books")
    public List<BookResponseDto> getBooksList() {
        return bookService.findAllBook();
    }

    /**
     * 회원 등록 (post)
     * @param requestDto 입력받은 회원정보
     * @return 입력받아 등록된 회원정보
     */
    @PostMapping("/members")
    public MemberResponseDto createMember(@RequestBody MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    /**
     * 선택한 도서 대출 (post) :
     * 대출 정보 저장
     * 대출 시 "반납 전"으로 반납상태(returnStatus) 변경
     * : book을 빌릴때 member와 book의 정보를 loan 내역에 함께 저장
     * @param bookId book pk
     * @param memberId member pk
     * @return 해당 도서 대출 정보
     */
    @PostMapping("books/loan/bookId/{bookId}/memberId/{memberId}")
    public LoanResponseDto loanBook (@PathVariable Long bookId, @PathVariable Long memberId) {
        return loanService.loanBook(bookId, memberId);
    }

    /**
     * 선택한 도서 반납 (put) :
     * loanId -> LoanRepository 에 있는지 확인
     * 도서 대출상태 "반납전"일때
     * -> "반납완료"상태로 repository에 저장
     * @param loanId loan pk
     * @return 대출 정보 (반납전->반납후)
     */
    @PutMapping("/books/loan/{loanId}")
    public LoanResponseDto returnBook (@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }

    /**
     * 회원의 대출 내역 조회 (get)
     * memberId로 해당 회원이 대출한 내역 loanRepository에서 조회
     * 등록할때 저장된 Book, Member 객체 이용 -> 필요한 회원 정보, 책 정보 get
     * -> loanInfoDto로 response
     * @param memberId member pk
     * @return 해당 회원의 대출한 내역 (+ 회원 이름, 전화번호, 책의 제목, 저자)
     */
    @GetMapping("/books/loan/memberId/{memberId}")
    public List<LoanInfoDto> getLoanList (@PathVariable Long memberId) {
        return loanService.findLoans(memberId);
    }
}

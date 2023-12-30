package com.sparta.library.entity;

import com.sparta.library.dto.LoanResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Loan extends LoanTimestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    private Member member;
    @ManyToOne
    private Book book;

    private Long memberId;
    private Long bookId;

    private String returnStatus;

    // 도서 대출할 때
    public Loan(Book book, Member member) {
        super();
        this.member = member;
        this.book = book;
        this.bookId = book.getBookId();
        this.memberId = member.getMemberId();
        this.returnStatus = "반납 전"; // 대출 중으로 전환
    }

    // 도서 반납할 때
    public void returnBook() {
        this.returnStatus = "반납 완료";
    }
}

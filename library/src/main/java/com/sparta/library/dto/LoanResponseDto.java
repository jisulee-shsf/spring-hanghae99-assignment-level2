package com.sparta.library.dto;

import com.sparta.library.entity.Loan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LoanResponseDto {
    private Long loanId;
    private Long bookId;
    private Long memberId;
    private String returnStatus;

    private LocalDateTime loanDate;
    private LocalDateTime returnDate;

    public LoanResponseDto(Loan loan) {
        this.loanId = loan.getLoanId();
        this.bookId = loan.getBookId();
        this.memberId = loan.getMemberId();
        this.returnStatus = loan.getReturnStatus();

        this.loanDate = loan.getLoanDate();
        this.returnDate = loan.getReturnDate();
    }
}

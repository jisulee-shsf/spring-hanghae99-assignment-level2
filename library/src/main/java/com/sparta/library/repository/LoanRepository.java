package com.sparta.library.repository;

import com.sparta.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBookIdAndReturnStatus(Long bookId, String returnStatus);
    List<Loan> findAllByMemberIdOrderByReturnDateAsc(Long memberId);


}

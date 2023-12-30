package com.sparta.library.service;

import com.sparta.library.dto.LoanInfoDto;
import com.sparta.library.dto.LoanResponseDto;
import com.sparta.library.entity.Book;
import com.sparta.library.entity.Loan;
import com.sparta.library.entity.Member;
import com.sparta.library.repository.BookRepository;
import com.sparta.library.repository.LoanRepository;
import com.sparta.library.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    // 도서 대출 기능
    @Transactional
    public LoanResponseDto loanBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new EntityNotFoundException("도서를 찾을 수 없습니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(()
                -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        if (loanRepository.existsByBookIdAndReturnStatus(bookId, "반납 전")) {
            throw new IllegalArgumentException("대출 실패 : 이미 도서가 대출중입니다.");
        }

        Loan loan = new Loan(book, member);

        Loan saveloan = loanRepository.save(loan); // db에 대출기록 저장
        return new LoanResponseDto(saveloan);
    }

    // 도서 반납 기능(update)
    @Transactional
    public LoanResponseDto returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() ->
                new IllegalArgumentException("선택한 대출기록은 존재하지 않습니다."));
        loan.returnBook();
        loanRepository.save(loan); // 반납 완료상태로 저장 -> 이 부분 delete에서 save로 수정해서 아직 테스트안함

        return new LoanResponseDto(loan);
    }

    // 회원의 대출 내역 조회
    public List<LoanInfoDto> findLoans(Long memberId) {
        List<Loan> MemberLoanList =  loanRepository.findAllByMemberIdOrderByReturnDateAsc(memberId).
                stream().toList();
        List<LoanInfoDto> loanInfoDtoList = new ArrayList<>();
        for (Loan loan : MemberLoanList) {
            LoanInfoDto loanInfoDto = new LoanInfoDto();

            // 대출한 도서 정보
            Book book = loan.getBook();
            loanInfoDto.setTitle(book.getTitle());
            loanInfoDto.setAuthor(book.getAuthor());

            // 대출한 회원 정보 가져오기
            Member member = loan.getMember();
            loanInfoDto.setName(member.getName());
            loanInfoDto.setPhoneNumber(member.getPhoneNumber());
            loanInfoDto.setLoanDate(loan.getLoanDate());

            loanInfoDtoList.add(loanInfoDto);
        }
        return loanInfoDtoList;
    }
}

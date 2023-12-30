package com.sparta.library.service;

import com.sparta.library.dto.MemberRequestDto;
import com.sparta.library.dto.MemberResponseDto;
import com.sparta.library.entity.Member;
import com.sparta.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 등록
    @Transactional
    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        Member saveMember = memberRepository.save(member);
        return new MemberResponseDto(saveMember);
    }
}

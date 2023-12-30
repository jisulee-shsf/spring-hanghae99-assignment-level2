package com.sparta.library.dto;

import com.sparta.library.entity.Member;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {

    private Long memberId;
    private String name;
    private String gender;
//    private String idCardNumber; 주민번호는 반환x
    private String phoneNumber;
    private String address;

    public MemberResponseDto(Member Member) {
        this.memberId = Member.getMemberId();
        this.name = Member.getName();
        this.gender = Member.getGender();
        this.phoneNumber = Member.getPhoneNumber();
        this.address = Member.getAddress();
    }
}

package com.sparta.library.entity;

import com.sparta.library.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private String gender;

    @Column(unique = true)
    private String idCardNumber;

    @Column(unique = true)
    private String phoneNumber;

    private String address;

    public Member(MemberRequestDto requestDto) {
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
        this.idCardNumber = requestDto.getIdCardNumber();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
    }
}

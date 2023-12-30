package com.sparta.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoanInfoDto {
    private String name;
    private String phoneNumber;
    private String title;
    private String author;
    private LocalDateTime loanDate;
}

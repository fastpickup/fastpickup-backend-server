package com.project.fastpickup.admin.stats.dto;

import java.time.LocalDate;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntryDataDTO {
    private String registMonth;
    private Long signUpCount;
    private String registDate;
}

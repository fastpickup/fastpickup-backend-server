package com.project.fastpickup.admin.stats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @NotBlank(message = "RegistMonth Can Not Be Blank")
    private String registMonth;

    @NotNull(message = "SingUpCount Can Not Be Null")
    private Long signUpCount;

    @NotBlank(message = "ReigstDate Cant Not Be Blank")
    private String registDate;
}

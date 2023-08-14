package com.project.fastpickup.admin.like.dto;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Like DTO Class
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    // tbl_like
    @NotBlank(message = "Email Can Not Be Blank")
    private String email;

    @NotNull(message = "Pno Can Not Be Null")
    private Long pno;

    @NotNull(message = "CreateDate Can Not Be Null")
    private LocalDate createDate;
}

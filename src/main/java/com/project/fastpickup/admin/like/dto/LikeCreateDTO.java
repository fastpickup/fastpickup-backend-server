package com.project.fastpickup.admin.like.dto;

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
import lombok.ToString;

// Like Create DTO Class
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeCreateDTO {
    // tbl_like
    @NotBlank(message = "Email Can Not Be Null")
    private String email;
    @NotNull(message = "Pno Can Not Be Null")
    private Long pno;
}

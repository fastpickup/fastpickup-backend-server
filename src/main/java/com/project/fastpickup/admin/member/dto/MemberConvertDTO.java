package com.project.fastpickup.admin.member.dto;

import java.time.LocalDateTime;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberConvertDTO {
    // tbl_member
    private String email;
    private String memberPw;
    private String memberName;
    private String memberPhone;
    private String store;
    private LocalDateTime joinDate;
}

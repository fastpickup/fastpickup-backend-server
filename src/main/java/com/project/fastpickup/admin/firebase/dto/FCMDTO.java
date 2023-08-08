package com.project.fastpickup.admin.firebase.dto;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FCMDTO {
    private String email;
    private String fcmToken;
}

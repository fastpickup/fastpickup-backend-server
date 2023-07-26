package com.project.fastpickup.admin.member.dto;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO extends User implements OAuth2User {

    private String email;
    private String memberName;
    private String memberPw;

    public MemberDTO(String email, String memberPw, String memberName, List<String> roleNames) {

        // super(email,mpw, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        super(email, memberPw,
                roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
        this.memberName = memberName;
        this.email = email;
        this.memberPw = memberPw;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return this.email;
    }
}

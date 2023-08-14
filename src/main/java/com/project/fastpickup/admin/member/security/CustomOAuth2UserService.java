package com.project.fastpickup.admin.member.security;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.dto.MemberReadDTO;
import com.project.fastpickup.admin.member.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// Kakao Login User Details Class 
@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // 의존성 주입 
    private final MemberMapper memberMapper;

    // Oauth2User LoadByUserRequest 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("Is Running Load By User Request");
        log.info(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }
        log.info("Email: " + email);

        // DB에 해당 사용자가있으면
        MemberReadDTO memberReadDTO = memberMapper.selectOne(email);
        if (memberReadDTO != null) {

            return new MemberDTO(email,
                    memberReadDTO.getMemberPw(),
                    memberReadDTO.getMemberName(),
                    memberReadDTO.getRolenames());}

        // PW를 사용하지않는 자원 이미 카카오에서 인증이되었기 때문에 비워둔다.
        MemberDTO memberDTO = new MemberDTO(email, "", "카카오사용자", List.of("USER"));
        return memberDTO;
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {
        log.info("KAKAO Login Is Running");
        Object value = paramMap.get("kakao_account");
        log.info("value: "+value);
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");
        log.info("email: " + email);
        return email;
    }
}
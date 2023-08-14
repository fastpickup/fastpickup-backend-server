package com.project.fastpickup.admin.member.security.handler;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.project.fastpickup.admin.member.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

// Kakao Login Success Handler Class
@Log4j2
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {

    // Kakao Login Success Hanlder 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("Is Running CustomOAuthSuccessHandler");
        log.info("authentication.getPrincipal" + authentication.getPrincipal());

        MemberDTO dto = (MemberDTO) authentication.getPrincipal();
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("email", dto.getEmail());

        // Encode the email for safe use in URL
        String encodedEmail = URLEncoder.encode(dto.getEmail(), StandardCharsets.UTF_8.toString());

        // MemberDTO를 사용 소셜 로그인에 성공했을 시(mpw가 "" 일때) 수정페이지로 가기
        if (dto.getMemberPw() == null || dto.getMemberPw().equals("")) {

            // DB에 소셜로그인 email이 없을때 Redirect Member Update Page
            response.sendRedirect("/admin/member/update/" + dto.getEmail());
            return;
        }
        // DB에 소셜로그인 email이 있으면 Redirect Member Read Page
        response.sendRedirect("/admin/member/read/" + dto.getEmail());
    }
}
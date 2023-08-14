package com.project.fastpickup.admin.member.security.handler;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

// Spring Security Access Denied Handler
@Log4j2 
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // 디나이드헨들러의 구현 메소드 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
            log.info("Is Running AccessDenied Handler");
            log.info(accessDeniedException);
    }  
}
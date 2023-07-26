package com.project.fastpickup.admin.member.config;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */


import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.project.fastpickup.admin.member.security.handler.CustomAccessDeniedHandler;
import com.project.fastpickup.admin.member.security.handler.CustomOAuthSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;

    // TokenRepository에 토큰 값 저장 함수
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public CustomOAuthSuccessHandler customOAuthSuccessHandler() {
        return new CustomOAuthSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Seucirty Filter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Spring Seucirty Filter Chain Is Running");

        // 커스텀 로그인 페이지 경로 지정
        http.formLogin(config -> {
            config.loginPage("/admin/member/signin");
            config.successHandler(customOAuthSuccessHandler());
        });

        // 권한이 없는 페이지를 접속했을 시 처리
        http.exceptionHandling(config -> {
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        http.rememberMe(config -> {
            // 발행한 토큰 값 repository에 저장
            config.tokenRepository(persistentTokenRepository());
            config.tokenValiditySeconds(60 * 60 * 24 * 7); // 7 Days
        });

        // form 안에있는 Hidden으로 포함된 csrf input tag를 없애겠다.
        http.csrf(config -> {
            config.disable();
        });

        // social 로그인 signin페이지에 설정 (카카오)
        http.oauth2Login(config -> {
            config.loginPage("/admin/member/signin");
            config.successHandler(customOAuthSuccessHandler());

        });

        // logout
        http.logout(config -> {
            config.logoutUrl("/admin/member/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/admin/member/signin");
        });

        return http.build();
    }

}
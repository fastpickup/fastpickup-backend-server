package com.project.fastpickup.like.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.fastpickup.admin.like.dto.LikeCreateDTO;
import com.project.fastpickup.admin.like.dto.LikeDTO;
import com.project.fastpickup.admin.like.mappers.LikeMapper;

import lombok.extern.log4j.Log4j2;

// Like Mapper Test Class
@Log4j2
@SpringBootTest
public class LikeMapperTests {
    
    // 의존성 주입 
    @Autowired(required = false)
    private LikeMapper likeMapper;

    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_PROUDCT_NUMBER = 22L; 

    // BeforeEach 사용을 위한 DTO 정의 
    private LikeDTO likeDTO;
    private LikeCreateDTO likeCreateDTO;

    // BeforeEach
    @BeforeEach
    public void setUp() {
        likeCreateDTO = LikeCreateDTO.builder()
        .email(TEST_EMAIL)
        .pno(TEST_PROUDCT_NUMBER)
        .build();

        likeDTO = LikeDTO.builder()
        .email(TEST_EMAIL)
        .pno(TEST_PROUDCT_NUMBER)
        .build();
    }
}

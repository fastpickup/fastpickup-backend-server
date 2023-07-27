package com.project.fastpickup.qna.service;

import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.mappers.QnaMapper;
import com.project.fastpickup.admin.qna.service.QnaService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class QnaServiceTests {

    // 의존성 주입
    @Autowired
    private QnaService qnaService;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다.
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_QNA_TITLE = "문의합니다.";
    private static final String TEST_QNA_TITLE2 = "문의합니다.(수정본)";
    private static final String TEST_QNA_CONTENT = "문의내용은 ~~~~~~~입니다.";
    private static final String TEST_QNA_CONTENT2 = "문의내용은 ~~~~~~~입니다.(수정본)";

    // DTO 정의
    private QnaRegistDTO qnaRegistDTO;
    private QnaUpdateDTO qnaUpdateDTO;

    // BeforeEach 사용을 위한 setUp
    @BeforeEach
    public void setUp() {

        // 문의 등록
        qnaRegistDTO = QnaRegistDTO.builder()
                .email(TEST_EMAIL)
                .qnaTitle(TEST_QNA_TITLE)
                .qnaContent(TEST_QNA_CONTENT)
                .build();

        // 문의 수정
        qnaUpdateDTO = QnaUpdateDTO.builder()
                .qno(4L)
                .qnaTitle(TEST_QNA_TITLE2)
                .qnaContent(TEST_QNA_CONTENT2)
                .build();
    }

    /*  TEST  */
    // listQna test
    @Test
    public void listQnaTestService() {


    }
}

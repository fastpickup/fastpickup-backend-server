package com.project.fastpickup.review.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.review.dto.ReviewDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewModifyDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReviewServiceTests {

    @Autowired
    private ReviewService reviewService;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final Long TEST_RNO = 19L;
    private static final Long TEST_SNO = 1L;
    private static final Long TEST_ONO = 4L;
    private static final Long TEST_PNO = 22L;
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_REVIEW_TITLE = "맛있어용";
    private static final String TEST_REVIEW_CONTENT = "It's So disgust";
    private static final int TEST_GNO = 19;
    private static final boolean TEST_ISDELETED = false;

    private static final String TEST_FILE_NAME = "Junit.jpg"; // 파일명

    // DTO 정의
    private ReviewDTO reviewDTO;
    private ReviewRegistDTO reviewRegistDTO;
    private ReviewListDTO reviewListDTO;
    private PageRequestDTO pageRequestDTO;
    private ReviewModifyDTO reviewModifyDTO;

    @BeforeEach
    public void init() {
        // 리뷰 등록
        reviewRegistDTO = ReviewRegistDTO.builder()
                .sno(TEST_SNO)
                .ono(TEST_ONO)
                .gno(TEST_GNO)
                .email(TEST_EMAIL)
                .reviewTitle(TEST_REVIEW_TITLE)
                .reviewContent(TEST_REVIEW_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + "_" + TEST_FILE_NAME, UUID.randomUUID() + "_" + TEST_FILE_NAME))
                .build();

        reviewModifyDTO = ReviewModifyDTO.builder()
                .rno(TEST_RNO)
                .sno(TEST_SNO)
                .gno(TEST_GNO)
                .email(TEST_EMAIL)
                .reviewTitle(TEST_EMAIL)
                .reviewContent(TEST_EMAIL)
                .fileNames(List.of(UUID.randomUUID() + "_" + TEST_FILE_NAME, UUID.randomUUID() + "_" + TEST_FILE_NAME))
                .build();

        // 리뷰 리스트 페이징
        pageRequestDTO = PageRequestDTO.builder().build();

    }

    @Test
    @Transactional
    @DisplayName("리뷰 등록 서비스 테스트")
    public void testRegistReview() {

        // GIVEN
        log.info("=== Start Regist Review Test Service ===");
        // WHEN
        long registCnt = reviewService.registReview(reviewRegistDTO);

        log.info("==============");
        log.info("==============");
        log.info(registCnt);
        log.info("==============");
        // THEN
        Assertions.assertTrue(registCnt > 0, "Review Register Test Fail");
        log.info("=== End Regist Review Test Service ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 리스트 서비스 테스트")
    public void testListReview() {

        // GIVEN
        log.info("=== Start List Review Test Service ===");

        // WHEN
        PageResponseDTO<ReviewListDTO> list = reviewService.getList(pageRequestDTO);

        // THEN
        log.info(list);
        Assertions.assertNotNull(list, "Review List Store is Null");
        log.info("=== End List Review Test Service ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 조회 서비스 테스트")
    public void testReadReview() {
        // GIVEN
        log.info("=== Start Read Review Test Service ===");
        // WHEN
        ReviewReadDTO dto = reviewService.reviewSelectOne(TEST_RNO);
        // THENs
        log.info(dto);
        Assertions.assertNotNull(dto, "Review Read is Null");
        log.info("=== End Read Review Test Service ===");
    }

    @Test
    @Transactional
    @DisplayName("리뷰 가맹점 서비스 리스트")
    public void testRestList() {

        PageResponseDTO<ReviewListDTO> list = reviewService.getStoreList(TEST_SNO, pageRequestDTO);

        log.info(list.getList());

    }

    @Test
    @Transactional
    @DisplayName("리뷰 수정 서비스 테스트")
    public void testUpdateReview(){

        reviewService.updateReview(reviewModifyDTO);
        

    }

}

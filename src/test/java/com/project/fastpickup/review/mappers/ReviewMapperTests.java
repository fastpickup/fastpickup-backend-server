package com.project.fastpickup.review.mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.mappers.ReviewFileMapper;
import com.project.fastpickup.admin.review.mappers.ReviewMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@RequiredArgsConstructor
public class ReviewMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private ReviewMapper reviewMapper;

    @Autowired(required = false)
    private ReviewFileMapper reviewFileMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final Long TEST_RNO = 19L;
    private static final Long TEST_SNO = 1L;
    private static final Long TEST_ONO = 4L;
    private static final Long TEST_PNO = 22L;
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_REVIEW_TITLE = "맛있어요";
    private static final String TEST_REVIEW_CONTENT = "It's So disgust";
    private static final int TEST_GNO = 0;
    private static final boolean TEST_ISDELETED = false;

    private static final String TEST_FILE_NAME = "Junit.jpg"; // 파일명

    // DTO 정의
    private ReviewDTO reviewDTO;
    private ReviewRegistDTO reviewRegistDTO;
    private PageRequestDTO pageRequestDTO;

    @BeforeEach
    public void init() {
        // 리뷰 등록
        reviewRegistDTO = ReviewRegistDTO.builder()
                .sno(TEST_SNO)
                .ono(TEST_ONO)
                .email(TEST_EMAIL)
                .reviewTitle(TEST_REVIEW_TITLE)
                .reviewContent(TEST_REVIEW_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + "_" + TEST_FILE_NAME, UUID.randomUUID() + "_" + TEST_FILE_NAME))
                .build();

                

        // 리뷰 리스트 페이징
        pageRequestDTO = PageRequestDTO.builder().build();

    }

    @Test
    @Transactional
    @DisplayName("리뷰 등록 매퍼 테스트")
    public void testRegistReview() {

        // GIVEN
        log.info("=== Start Regist Review Test Mapper ===");

        // WHEN
        int registCnt = reviewMapper.registReview(reviewRegistDTO);

        // select Key로 등록 한 리뷰 rno값을 출력
        long rno = reviewRegistDTO.getRno();
        log.info("등록한 리뷰번호: " + rno);

        // THEN
        Assertions.assertEquals(1, registCnt, "Product Register Test Fail");
        log.info("=== End Regist Review Test Mapper ===");
    }

    @Test
    @Transactional
    @DisplayName("리뷰 이미지 등록 매퍼 테스트")
    public void testRegistReviewImg() {

        // GIVEN
        log.info("=== Start Regist Review Img Test Mapper ===");

        Long rno = 21L;
        List<String> fileNames = reviewRegistDTO.getFileNames();

        AtomicInteger index = new AtomicInteger();
        // 등록된 파일 fileNames에서 추출
        List<Map<String, String>> list = fileNames.stream().map(str -> {

            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);

            return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());
        log.info(list);

        // WHEN
        // 파일 명 DB에 저장
        int reviewFileCnt = reviewFileMapper.registReviewImg(list);

        // log.info("================");
        // log.info(reviewFileCnt);
        // log.info("================");
        // log.info(fileNames.size());

        // THEN
        // fileName의 개수 = fileNames.size()와 reviewFileCnt가 같을 시
        Assertions.assertEquals(fileNames.size(), reviewFileCnt, "Product Register Img Test Fail");

        log.info("=== End Regist Review Img Test Mapper ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 리스트 매퍼 테스트")
    public void testReviewList() {

        // GIVEN
        log.info("=== Start List Review Test Mapper ===");

        // WHEN
        List<ReviewListDTO> list = reviewMapper.getReviewList(pageRequestDTO);

        // THEN
        log.info(list);
        Assertions.assertNotNull(list, "Product List is Null");
        log.info("=== End List Product Test Mapper ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 리스트 토탈 매퍼 테스트")
    public void testListCountReviwe() {
        // GIVEN
        log.info("=== Start List Count Review Test Mapper ===");
        // WHEN
        long listTotal = reviewMapper.reviewListCount(pageRequestDTO);
        // THEN
        log.info(listTotal);
        Assertions.assertNotNull(listTotal, "Review List is Null");
        log.info("=== End List Count Review Test Mapper ===");
    }

    @Test
    @Transactional
    @DisplayName("리뷰 상세조회 테스트")
    public void testReadReview() {
        // GIVEN
        log.info("=== Start Read Review Test Mapper ===");
        // WHEN
        ReviewReadDTO read = reviewMapper.reviewSelectOne(TEST_RNO);
        log.info(read);

        // THEN

    }

    
    @Test
    @Transactional
    @DisplayName("리뷰 삭제 테스트")
    public void testDeleteReview(){

        reviewMapper.deleteReview(TEST_RNO);

    }

}

package com.project.fastpickup.admin.review.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewModifyDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.mappers.ReviewFileMapper;
import com.project.fastpickup.admin.review.mappers.ReviewMapper;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    // 의존성 주입
    private final ReviewMapper reviewMapper;
    private final ReviewFileMapper reviewFileMapper;

    // Regist Review Service
    @Override
    public Long registReview(ReviewRegistDTO reviewRegistDTO) {

        log.info("============ Review Create Service ============");

        long gno = reviewRegistDTO.getGno();
        // review 개수 보여 주려고 설계
        long sno = reviewRegistDTO.getSno();

        /* 리뷰 */
        if (gno == 0) {

            // 리뷰 등록
            long reviewCnt = reviewMapper.registReview(reviewRegistDTO);

            // selectKey로 가져온 rno값
            long rno = reviewRegistDTO.getRno();

            // 리뷰 등록 후 gno = rno 처리 하여 리뷰, 답글 구분
            reviewMapper.updateGno(rno);

            List<String> fileNames = reviewRegistDTO.getFileNames();

            if(reviewCnt > 0 && reviewRegistDTO.getFileNames() != null && !reviewRegistDTO.getFileNames().isEmpty()) {

                AtomicInteger index = new AtomicInteger();
                // 등록된 파일 fileNames에서 추출
                List<Map<String, String>> list = fileNames.stream().map(str -> {

                    String uuid = str.substring(0, 36);
                    String fileName = str.substring(37);

                    return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord",
                            "" + index.getAndIncrement());
                }).collect(Collectors.toList());
                log.info(list);

                reviewFileMapper.registReviewImg(list);
            }

            return rno;

        }
        /* 리뷰 */

        /* 답글 */
        long reviewCnt = reviewMapper.registChildReview(reviewRegistDTO);
        long rno = reviewRegistDTO.getRno();

        List<String> fileNames = reviewRegistDTO.getFileNames();

        if(reviewCnt > 0 && reviewRegistDTO.getFileNames() != null && !reviewRegistDTO.getFileNames().isEmpty()) {

            AtomicInteger index = new AtomicInteger();
            // 등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(list);

            reviewFileMapper.registReviewImg(list);
        }

        return rno;

    }
    /* 답글 */

    /* 리스트 */
    @Override
    public PageResponseDTO<ReviewListDTO> getList(PageRequestDTO pageRequestDTO) {

        log.info("============ Review List Service ============");
        // 리스트 선언
        List<ReviewListDTO> list = reviewMapper.getReviewList(pageRequestDTO);
        // Total 선언
        long total = reviewMapper.reviewListCount(pageRequestDTO);

        log.info("============ //Review List Service ============");

        // PageResponseDTO 타입으로 반환
        return PageResponseDTO.<ReviewListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }
    /* 리스트 */

    @Override
    public ReviewReadDTO reviewSelectOne(Long rno) {

        return reviewMapper.reviewSelectOne(rno);

    }

    @Override
    public PageResponseDTO<ReviewListDTO> getStoreList(Long sno, PageRequestDTO pageRequestDTO) {

        List<ReviewListDTO> list = reviewMapper.getReviewListStore(sno, pageRequestDTO);
        long total = reviewMapper.reviewListStoreCount(pageRequestDTO);

        return PageResponseDTO.<ReviewListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public Long updateReview(ReviewModifyDTO reviewModifyDTO) {

        int updateCnt = reviewMapper.updateReview(reviewModifyDTO);

        Long rno = reviewModifyDTO.getRno();

        reviewFileMapper.deleteReviewImg(rno);

        List<String> fileNames = reviewModifyDTO.getFileNames();

        if(updateCnt > 0 && reviewModifyDTO.getFileNames() != null && !reviewModifyDTO.getFileNames().isEmpty()) {

            AtomicInteger index = new AtomicInteger();
            // 등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(list);

            reviewFileMapper.registReviewImg(list);
        }

        return rno;

    }

    @Override
    public int deleteReview(Long rno) {

        return reviewMapper.deleteReview(rno);

    }

    // 가맹점 리뷰 답글
    @Override
    public ReviewReadDTO storeReview(Long rno) {

        return reviewMapper.storeReview(rno);

    }

    @Override
    public int countStoreReivew(int gno) {

        return reviewMapper.countStoreReivew(gno);

    }

    @Override
    public long getReviewGno(Long rno) {

        return reviewMapper.getReviewGno(rno);

    }

}

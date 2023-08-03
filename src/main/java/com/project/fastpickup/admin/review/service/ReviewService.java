package com.project.fastpickup.admin.review.service;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewModifyDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

@Transactional
public interface ReviewService {
    
    // Regist Review
    Long registReview(ReviewRegistDTO reviewRegistDTO);

    // 전체 리뷰 리스트
    PageResponseDTO<ReviewListDTO> getList( PageRequestDTO pageRequestDTO);

    // 가맹점 별 리뷰 리스트 => Rest 방식
    PageResponseDTO<ReviewListDTO> getStoreList(Long sno, PageRequestDTO pageRequestDTO);

    // Read Review
    ReviewReadDTO reviewSelectOne(Long rno);

    // Update Review
    Long updateReview(ReviewModifyDTO reviewModifyDTO);

    // Delete Review
    int deleteReview(Long rno);

    // 가맹점 리뷰 답글
    ReviewReadDTO storeReview(Long rno);

    // 리뷰 답글 카운트
    int countStoreReivew(int gno);

    // GNO를 가져오는 코드
    long getReviewGno(Long rno);

}

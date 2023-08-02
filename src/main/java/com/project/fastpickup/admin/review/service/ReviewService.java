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

    // List Review
    PageResponseDTO<ReviewListDTO> getList( PageRequestDTO pageRequestDTO);

    // Rest 방식
    PageResponseDTO<ReviewListDTO> getStoreList(Long sno, PageRequestDTO pageRequestDTO);

    // Read Review
    ReviewReadDTO reviewSelectOne(Long rno);

    // Update Review
    Long updateReview(ReviewModifyDTO reviewModifyDTO);

    // Delete Review
    int deleteReview(Long rno);

}

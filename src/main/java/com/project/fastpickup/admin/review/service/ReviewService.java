package com.project.fastpickup.admin.review.service;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

@Transactional
public interface ReviewService {
    
    // Regist Review
    Long registReview(ReviewRegistDTO reviewRegistDTO);

    // List Review
    PageResponseDTO<ReviewListDTO> getList(long sno,PageRequestDTO pageRequestDTO);

}

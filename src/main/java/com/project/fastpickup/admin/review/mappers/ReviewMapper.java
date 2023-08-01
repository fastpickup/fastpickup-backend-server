package com.project.fastpickup.admin.review.mappers;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

public interface ReviewMapper {

    // 리뷰 등록
    int registReview(ReviewRegistDTO reviewRegistDTO);
    int updateGno(Long rno);

    // 리뷰 답글 등록
    int registChildReview(ReviewRegistDTO reviewRegistDTO);

    // 리뷰 리스트
    List<ReviewListDTO> getReviewList(PageRequestDTO pageRequestDTO);
    long reviewListCount(PageRequestDTO pageRequestDTO);

    ReviewReadDTO reviewSelectOne(Long rno);

}

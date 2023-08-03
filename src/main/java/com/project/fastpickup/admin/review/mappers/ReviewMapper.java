package com.project.fastpickup.admin.review.mappers;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewModifyDTO;
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

    // Rest
    List<ReviewListDTO> getReviewListStore(@Param("sno")Long sno, @Param("pr")PageRequestDTO pageRequestDTO);
    long reviewListStoreCount(PageRequestDTO pageRequestDTO);


    // 리뷰 상세 페이지
    ReviewReadDTO reviewSelectOne(Long rno);

    // 리뷰 삭제
    int deleteReview(Long rno);

    // 리뷰 수정
    int updateReview(ReviewModifyDTO modifyDTO);

    // 리뷰 답글
    ReviewReadDTO storeReview(Long rno);

    // 답글 카운트
    int countStoreReivew(int gno);

    // gno 값을 추출
    long getReviewGno(Long rno);

}

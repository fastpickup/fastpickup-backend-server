package com.project.fastpickup.admin.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
 @ToString
public class ReviewListDTO {

    private Long rno;                           // PK
    private Long sno;                           // 가맹점 번호
    private String email;                       // 리뷰 작성자
    private String reviewContent;               // 리뷰 내용
    private LocalDateTime reviewDate;           // 상품 등록일
    private LocalDateTime reviewUpdateDate;     // 리뷰 수정일
    private int gno;                            // 댓글 답글 여부 확인
    private boolean isDeleted;                  // 리뷰삭제 여부

    private List<String> fileNames;             // 파일명


}

package com.project.fastpickup.admin.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
 @ToString
public class ReviewReadDTO {
    
    private Long rno;                           // PK
    private Long sno;                           // 가맹점 번호
    private Long ono;                           // 주문 번호
    private String email;                       // 리뷰 작성자
    private String reviewTitle;                 // 리뷰 제목
    private String reviewContent;               // 리뷰 내용
    private LocalDateTime reviewDate;           // 리뷰 등록일
    private int gno;                            // 리뷰 답글 여부 확인
    private boolean isDeleted;                  // 리뷰삭제 여부

    private String storeName;                   // 가맹점명
    private String productName;                 // 상품명
    
    private List<String> fileNames;             // 파일명

}

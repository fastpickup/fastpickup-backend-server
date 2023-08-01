package com.project.fastpickup.admin.review.dto;

import java.time.LocalDateTime;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

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
public class ReviewDTO {

    private Long rno;                       // PK
    private String reviewTitle;             // 리뷰 제목
    private String reviewContent;           // 리뷰 내용
    private LocalDateTime reviewDate;       // 리뷰 등록일
    private LocalDateTime reviewUpdateDate; // 리뷰 수정일
    private int gno;                        // 답글 여부
    private boolean isDeleted;              // 리뷰삭제 여부
    private String email;                   // 리뷰 작성자
    private long ono;                       // 주문 번호
    private long sno;                       // 가맹점 번호

}

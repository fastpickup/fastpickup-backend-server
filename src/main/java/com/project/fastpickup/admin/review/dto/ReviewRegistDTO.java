package com.project.fastpickup.admin.review.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Date   : 2023.07.27
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewRegistDTO {

    private Long rno;                                    // PK
    private long sno;                                    // 가맹점 번호
    private long ono;                                    // 주분 번호
    private long pno;                                    // 상품 번호
    
    @Builder.Default
    private long gno = 0L;                               // 답글 여부   
    
    private String email;                                // 작성자 이메일
    private String reviewContent;                        // 리뷰 내용
    private String reviewTitle;                          // 리뷰 제목
    
    @Builder.Default
    private List<String> fileNames = new ArrayList<>();  // 파일명
}

package com.project.fastpickup.admin.review.dto;

import java.util.ArrayList;
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
public class ReviewModifyDTO {

    private Long rno;                                    // PK
    private long sno;                                    // 가맹점 번호
    private long ono;                                    // 주분 번호
    private long pno;                                    // 상품 번호
    
    private long gno;                               // 답글 여부   
    
    private String email;                                // 작성자 이메일
    private String reviewContent;                        // 리뷰 내용
    private String reviewTitle;                          // 리뷰 제목
    
    @Builder.Default
    private List<String> fileNames = new ArrayList<>();  // 파일명

    
}

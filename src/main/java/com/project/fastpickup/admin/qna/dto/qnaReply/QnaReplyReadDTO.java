package com.project.fastpickup.admin.qna.dto.qnaReply;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QnaReplyReadDTO {

    // 변수
    private Long qno; // 문의글 번호
    private String email; // 이메일
    private String reply; // 답글 내용
    private String name; // 작성자(member에서 join)
    private LocalDateTime replyDate; // 답글 등록일자
}

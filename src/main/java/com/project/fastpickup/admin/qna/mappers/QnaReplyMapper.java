package com.project.fastpickup.admin.qna.mappers;

import com.project.fastpickup.admin.qna.dto.reply.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaReplyMapper {

    // Create
    int createQnaReply(QnaReplyRegistDTO qnaReplyRegistDTO);

    // read
    QnaReplyReadDTO readQnaReply(Long rno);

    // Update
    int updateQnaReply(QnaReplyUpdateDTO qnaReplyUpdateDTO);

    // Delete
    int deleteQnaReply(Long rno);
}

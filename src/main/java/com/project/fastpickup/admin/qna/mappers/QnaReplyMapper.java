package com.project.fastpickup.admin.qna.mappers;

import com.project.fastpickup.admin.qna.dto.qnaReply.QnaReplyDTO;
import com.project.fastpickup.admin.qna.dto.qnaReply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.dto.qnaReply.QnaReplyRegistDTO;
import com.project.fastpickup.admin.qna.dto.qnaReply.QnaReplyUpdateDTO;
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

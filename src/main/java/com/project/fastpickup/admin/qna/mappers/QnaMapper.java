package com.project.fastpickup.admin.qna.mappers;

/*
 * Date   : 2023.07.31
 * Author : 송수정
 * Author : 이범수
 * E-mail : sujung033131@gmail.com
 * E-mail : beomsu_1221@naver.com
 */

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    // list
    List<QnaListDTO> listQna (PageRequestDTO pageRequestDTO);

    // Create
    int createQna (QnaRegistDTO qnaRegistDTO);

    // read
    QnaDTO readQna (Long qno);

    // update
    int updateQna(QnaUpdateDTO qnaUpdateDTO);

    // delete
    int deleteQna (Long qno);

    // total
    long listCount (PageRequestDTO pageRequestDTO);


    
}

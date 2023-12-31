package com.project.fastpickup.admin.qna.controller;

/*
 * Date   : 2023.07.31
 * Author : 송수정
 * Author : 이범수
 * E-mail : sujung033131@gmail.com
 * E-mail : beomsu_1221@naver.com
 */

import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyRegistDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyUpdateDTO;
import com.project.fastpickup.admin.qna.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("admin/qna/replies/")
@RequiredArgsConstructor
public class QnaReplyController {

    private final QnaReplyService qnaReplyService;

    // create
    // GET
    @GetMapping("{qno}/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getCreate (@PathVariable("qno") Long qno) {
        log.info("GET | create");

        return "admin/qna/replies/create";
    }

    // POST
    @PostMapping("{qno}/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postCreate (@PathVariable("qno") Long qno, QnaReplyRegistDTO qnaReplyRegistDTO) {
        log.info("POST | create");

        qnaReplyService.createQnaReply(qnaReplyRegistDTO);

        return "redirect:/admin/qna/read/{qno}";
    }

    // read
    @GetMapping("read/{rno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String GetRead (@PathVariable("rno") Long rno , Model model){

        log.info("GET | read");

        QnaReplyReadDTO read = qnaReplyService.readQnaReply(rno);

        model.addAttribute("readReply" , read);

        return "admin/qna/replies/read";
    }

    // update
    // GET
    @GetMapping("update/{rno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getUpdate(@PathVariable("rno") Long rno , Model model){

        log.info("GET | update");

        QnaReplyReadDTO read = qnaReplyService.readQnaReplyRno(rno);

        model.addAttribute("readReply", read);

        return "admin/qna/replies/update";

    }

    // post
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postUpdate(QnaReplyUpdateDTO qnaReplyUpdateDTO){

        log.info("POST | update");

        qnaReplyService.updateQnaReply(qnaReplyUpdateDTO);

        return "redirect:/admin/qna/read/" + qnaReplyUpdateDTO.getQno();

    }

    // delete
    @PostMapping("delete/{rno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postDelete(@PathVariable("rno") Long rno) {

        log.info("POST | delete");

        QnaReplyReadDTO dto = qnaReplyService.readQnaReply(rno);

        qnaReplyService.deleteQnaReply(rno);

        return "redirect:/admin/qna/list";

    }
}

package com.project.fastpickup.admin.qna.controller;

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
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.service.QnaReplyService;
import com.project.fastpickup.admin.qna.service.QnaService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("admin/qna/")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    private final QnaReplyService qnaReplyService;

    //페이지 체크
    @ModelAttribute("pageName")
    public String pageName(){
        return "qna";
    }

    // list
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | Admin Qna List");
        PageResponseDTO<QnaListDTO> listQna = qnaService.listQna(pageRequestDTO);
        model.addAttribute("listQna", listQna);

    }

    // create
    // get
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void getCreate(Model model) {



        log.info("GET | Admin Qna Create");
    }

    // post
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postCreate(QnaRegistDTO qnaRegistDTO) {

        log.info("POST | Admin Qna Create");
        qnaService.createQna(qnaRegistDTO);

        return "redirect:/admin/qna/list";
    }

    // read
    @GetMapping("read/{qno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getRead(@PathVariable("qno") Long qno, Model model) {

        log.info("GET | Admin Qna Read");

        QnaDTO listQna = qnaService.readQna(qno);
        model.addAttribute("listQna", listQna);

        QnaReplyReadDTO replyRead = qnaReplyService.readQnaReply(qno);
        model.addAttribute("replyRead", replyRead);

        int count = qnaReplyService.replyCount(qno);
        model.addAttribute("count" , count);

        return "admin/qna/read";
    }

    // update
    // get
    @GetMapping("update/{qno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getUpdate(@PathVariable("qno") Long qno, Model model) {

        log.info("GET | Admin Qna Update");

        QnaDTO listQna = qnaService.readQna(qno);
        model.addAttribute("listQna", listQna);

        return "admin/qna/update";
    }

    // post
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postUpdate(QnaUpdateDTO qnaUpdateDTO) {

        log.info("POST | Admin Qna Update");

        qnaService.updateQna(qnaUpdateDTO);

        return "redirect:/admin/qna/read/" + qnaUpdateDTO.getQno();
    }

    // delete
    @PostMapping("delete/{qno}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postDelete(@PathVariable("qno") Long qno) {

        log.info("POST | Admin Qna Delete");

        qnaService.deleteQna(qno);

        return "redirect:/admin/qna/list";

    }




}

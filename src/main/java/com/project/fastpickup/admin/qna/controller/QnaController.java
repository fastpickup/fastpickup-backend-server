package com.project.fastpickup.admin.qna.controller;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
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
@RequestMapping("/admin/qna/")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    // list
    @GetMapping("list")
//    @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("permitAll")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET | Admin Qna List");
        PageResponseDTO<QnaListDTO> listQna = qnaService.listQna(pageRequestDTO);
        model.addAttribute("listQna", listQna);
    }

    // create
    // get
    @GetMapping("create")
    @PreAuthorize("permitAll")
    public void getCreate() {

        log.info("GET | Admin Qna Create");
    }

    // post
    @PostMapping("create")
    @PreAuthorize("permitAll")
    public String postCreate(@RequestBody QnaRegistDTO qnaRegistDTO) {

        log.info("POST | Admin Qna Create");
        qnaService.createQna(qnaRegistDTO);

        return "redirect:/admin/qna/list";
    }

    // read
    @GetMapping("read/{qno}")
    @PreAuthorize("permitAll")
    public String getRead(@PathVariable("qno") Long qno, Model model) {

        log.info("GET | Admin Qna Read");

        QnaDTO read = qnaService.readQna(qno);
        model.addAttribute("readQna", read);

        return "/admin/qna/read";
    }

    // update
    // get
    @GetMapping("update/{qno}")
    @PreAuthorize("permitAll")
    public String getUpdate(@PathVariable("qno") Long qno, Model model) {

        log.info("GET | Admin Qna Update");

        QnaDTO update = qnaService.readQna(qno);
        model.addAttribute("updateQna", update);

        return "/admin/qna/update";
    }

    // post
    @PostMapping("update")
    @PreAuthorize("permitAll")
    public String postUpdate(QnaUpdateDTO qnaUpdateDTO) {

        log.info("POST | Admin Qna Update");

        qnaService.updateQna(qnaUpdateDTO);

        return "redirect:/admin/qna/read/" + qnaUpdateDTO.getQno();
    }

    // delete
    @PostMapping("delete/{qno}")
    @PreAuthorize("permitAll")
    public String postDelete(@PathVariable("qno") Long qno) {

        log.info("POST | Admin Qna Delete");

        qnaService.deleteQna(qno);

        return "redirect:/admin/qna/list";

    }




}

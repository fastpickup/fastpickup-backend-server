package com.project.fastpickup.admin.review.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/review")
public class ReviewRestController {

    // private final ReviewService reviewService;

    // // 페이지 체크
    // @ModelAttribute("pageName")
    // public String pageName() {
    //     return "review";
    // }

    // // @PreAuthorize("hasAnyRole('ADMIN','STORE')")
    // @GetMapping("/list/{sno}")
    // public PageResponseDTO getReviewList(
    //         @PathVariable("sno") Long sno,
    //         PageRequestDTO pageRequestDTO) {

    //     return reviewService.getStoreList(sno, pageRequestDTO);
    // }

}

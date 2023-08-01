package com.project.fastpickup.admin.review.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("admin/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 페이지 체크
    @ModelAttribute("pageName")
    public String pageName() {
        return "review";
    }

    @PreAuthorize("hasAnyRole('ADMIN','STORE')")
    @GetMapping("/list")
    public void reviewList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<ReviewListDTO> list = reviewService.getList(pageRequestDTO);

        log.info(list);

        model.addAttribute("listReview", list);

    }

    @PreAuthorize("hasAnyRole('ADMIN','STORE')")
    @GetMapping("/read/{rno}")
    public String getReview(@PathVariable("rno") Long rno, Model model) {

        ReviewReadDTO reviewRead = reviewService.reviewSelectOne(rno);

        model.addAttribute("reviewRead", reviewRead);

        return "admin/review/read";

    }

    @PreAuthorize("hasAnyRole('ADMIN','STORE')")
    @GetMapping("/create/{sno}")
    public String createReview(@PathVariable("sno") Long sno, Model model) {

        model.addAttribute("sno", sno);

        log.info("GET | Admin Review Create");

        return "admin/review/create";

    }

    // Post
    @PreAuthorize("hasAnyRole('ADMIN','STORE')")
    @PostMapping("/create")
    public String postCreate(
            ReviewRegistDTO reviewRegistDTO, RedirectAttributes rttr) {
        log.info("POST | Product Create =================");
        reviewService.registReview(reviewRegistDTO);

        // 상품 등록 후 1회성 메세지 전달
        rttr.addFlashAttribute("message", "상품 등록이 완료 되었습니다.");

        return "redirect:/admin/review/list";
    }

    @GetMapping("{sno}/list")
    @PreAuthorize("permitAll")
    @ResponseBody
    public PageResponseDTO<ReviewListDTO> getStoreList(
            @PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO) {

        return reviewService.getStoreList(sno, pageRequestDTO);
    }

    // 리뷰 수정
    @GetMapping("/update/{rno}")
    @PreAuthorize("hasAnyRole('permitAll')")
    public String getUpdate(
            PageRequestDTO pageRequestDTO, @PathVariable("rno") Long rno, Model model) {
        log.info("GET | Product Update =================");

        ReviewReadDTO reviewReadDTO = reviewService.reviewSelectOne(rno);

        model.addAttribute("reviewReadDTO", reviewReadDTO);

        return "admin/product/update";
    }

}

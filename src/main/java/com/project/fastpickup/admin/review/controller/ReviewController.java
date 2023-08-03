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
import com.project.fastpickup.admin.review.dto.ReviewModifyDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/list")
    public void reviewList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<ReviewListDTO> list = reviewService.getList(pageRequestDTO);

        log.info(list);

        model.addAttribute("listReview", list);

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @GetMapping("/read/{rno}")
    public String getReview(@PathVariable("rno") Long rno, Model model) {

        ReviewReadDTO reviewRead = reviewService.reviewSelectOne(rno);

        model.addAttribute("reviewRead", reviewRead);

        ReviewReadDTO storeReview = reviewService.storeReview(rno);

        model.addAttribute("storeReview", storeReview);

        int count = reviewService.countStoreReivew(reviewRead.getGno());

        model.addAttribute("count", count);

        long sno = reviewRead.getSno();

        model.addAttribute("sno", sno);

        return "admin/review/read";

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @GetMapping("/create/{sno}")
    public String createReview(@PathVariable("sno") Long sno, Model model) {

        model.addAttribute("sno", sno);

        log.info("GET | Admin Review Create");

        return "admin/review/create";

    }

    // 리뷰 답글 작성 시 리뷰 페이지로 redirect
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @PostMapping("/create/{rno}")
    public String postCreate(
            @PathVariable("rno")Long rno,ReviewRegistDTO reviewRegistDTO, RedirectAttributes rttr) {
        log.info("POST | Product Create =================");
        reviewService.registReview(reviewRegistDTO);

        // 상품 등록 후 1회성 메세지 전달
        rttr.addFlashAttribute("message", " 답변 등록이 완료 되었습니다.");

        return "redirect:/admin/review/read/"+rno;
    }

    // POST FOR REVIEW 
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @PostMapping("/create")
    public String postCreateForReview(ReviewRegistDTO reviewRegistDTO, RedirectAttributes rttr) {
        reviewService.registReview(reviewRegistDTO);

         // 상품 등록 후 1회성 메세지 전달
        rttr.addFlashAttribute("message", " 리뷰 등록이 완료 되었습니다.");

        return "redirect:/admin/review/list";
    }

    // 가맹점 별 리뷰 리스트 => Rest방식
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @GetMapping("/{sno}/list")
    @ResponseBody
    public PageResponseDTO<ReviewListDTO> getStoreList(
            @PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO) {

        return reviewService.getStoreList(sno, pageRequestDTO);
    }

    // 리뷰 수정 페이지 GET
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @GetMapping("/update/{rno}")
    public String getUpdate(
            PageRequestDTO pageRequestDTO, @PathVariable("rno") Long rno, Model model) {
        log.info("GET | Product Update =================");

        ReviewReadDTO reviewRead = reviewService.reviewSelectOne(rno);

        log.info("==================");
        log.info(reviewRead);
        log.info("==================");

        model.addAttribute("reviewRead", reviewRead);

        return "admin/review/update";
    }

    // 리뷰 수정 POST
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @PostMapping("update/{rno}")
    public String postUpdate(
            ReviewModifyDTO reviewModifyDTO, RedirectAttributes rttr) {
        log.info("POST | Product Update =================");

        reviewService.updateReview(reviewModifyDTO);

        rttr.addFlashAttribute("message", reviewModifyDTO.getRno() + "번 리뷰가 수정 되었습니다.");

        return "redirect:/admin/review/read/" + reviewModifyDTO.getRno();
    }

    // 리뷰 삭제
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @PostMapping("delete/{rno}")
    public String postDelete(
            @PathVariable("rno") Long rno, RedirectAttributes rttr) {
        log.info("POST | Product Delete =================");

        reviewService.deleteReview(rno);

        // 상품 삭제 후 1회성 메세지 전달
        rttr.addFlashAttribute("message", rno + "번 리뷰가 삭제 되었습니다.");

        return "redirect:/admin/review/list";
    }

    // 리뷰에 대한 답변 수정 페이지
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @GetMapping("/store/update/{rno}")
    public String getStoreReivewUpdate(
            PageRequestDTO pageRequestDTO, @PathVariable("rno") Long rno, Model model) {
        log.info("GET | Product Update =================");

        ReviewReadDTO reviewRead = reviewService.reviewSelectOne(rno);

        log.info("==================");
        log.info(reviewRead);
        log.info("==================");

        model.addAttribute("reviewRead", reviewRead);

        return "admin/review/store/update";
    }


    // 리뷰에대한 답변 수정
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')") 
    @PostMapping("/store/update/{rno}")
    public String PostStoreReivewUpdate(
            ReviewModifyDTO reviewModifyDTO, RedirectAttributes rttr) {
        log.info("POST | Product Update =================");

        reviewService.updateReview(reviewModifyDTO);

        rttr.addFlashAttribute("message", reviewModifyDTO.getRno() + "번 답변이 수정 되었습니다.");

        return "redirect:/admin/review/read/" + reviewModifyDTO.getGno();
    }

    //리뷰에 대한 답변 삭제
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    @PostMapping("/store/delete/{rno}")
    public String postStoreReviewDelete(
            @PathVariable("rno") Long rno, RedirectAttributes rttr) {
        log.info("POST | Product Delete =================");

        reviewService.deleteReview(rno);
        
        long gno = reviewService.getReviewGno(rno);

        log.info("===============================");
        log.info(gno);
        log.info("===============================");


        // 상품 삭제 후 1회성 메세지 전달
        rttr.addFlashAttribute("message", rno + "번 답변이 삭제 되었습니다.");

        return "redirect:/admin/review/read/" + gno;
    }

    @GetMapping("/list/{sno}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getListForStore(@PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<ReviewListDTO> list = reviewService.getListForStore(sno, pageRequestDTO);

        model.addAttribute("listReview", list);
        model.addAttribute("sno", sno);
        return "admin/review/list";
    }


}

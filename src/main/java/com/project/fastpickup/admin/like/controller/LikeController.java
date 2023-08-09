package com.project.fastpickup.admin.like.controller;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.like.dto.LikeDTO;
import com.project.fastpickup.admin.like.service.LikeService;

import lombok.extern.log4j.Log4j2;

// Like Controller Class 
@Log4j2
@RestController
@RequestMapping("/like/")
public class LikeController {

    // 의존성 주입
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        log.info("Constructor Called, Like Service Injected.");
        this.likeService = likeService;
    }

    // Toggle Like
    @PostMapping("pno/toggle/{pno}")
    @PreAuthorize("permitAll")
    public ResponseEntity<Map<String, Integer>> toggleLikePno(@PathVariable("pno") Long pno,
            Authentication authentication) {
        log.info("RestController | Admin Toggle Like");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        likeService.checkEmailNotLogin(email); // 회원 로그인 여부 Check
//        likeService.checkPnoNotFound(pno); // 상품 존재 여부 Check
        int result = likeService.toggleLike(pno, email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Count Like
    @GetMapping("pno/{pno}/count")
    @PreAuthorize("permitAll")
    public ResponseEntity<Map<String, Long>> countLike(@PathVariable("pno") Long pno) {
        log.info("RestController | Admin Count Like");
//        likeService.checkPnoNotFound(pno); // 상품 존재 여부 Check
        Long result = likeService.countLike(pno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Check Like
    @GetMapping("pno/{pno}/check")
    @PreAuthorize("permitAll")
    public ResponseEntity<Map<String, Boolean>> checkLike(@PathVariable("pno") Long pno,
            Authentication authentication) {
        log.info("RestController | Admin Check Like");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        likeService.checkEmailNotLogin(email); // 회원 로그인 여부 Check
//        likeService.checkPnoNotFound(pno); // 상품 존재 여부 Check
        LikeDTO result = likeService.checkLikeByMemberAndPost(pno, email);
        return new ResponseEntity<>(Map.of("liked", result != null), HttpStatus.OK);
    }
}

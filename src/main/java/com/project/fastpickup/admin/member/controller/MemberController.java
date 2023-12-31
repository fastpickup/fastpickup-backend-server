package com.project.fastpickup.admin.member.controller;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.service.MemberService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

// Member Controller Class
@Log4j2
@Controller
@RequestMapping("admin/member/")
public class MemberController {

    // 의존성 주입
    private final MemberService memberService;

    // Autowired 명시적 표시
    @Autowired
    public MemberController(MemberService memberService) {
        log.info("Constructor Called, Service Injected.");
        this.memberService = memberService;
    }

    // 페이지 체크
    @ModelAttribute("pageName")
    public String pageName() {
        return "member";
    }

    // GET : Member List
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getListMember(PageRequestDTO pageRequestDTO, Authentication authentication, Model model) {
        log.info("GET | Admin Member List");
        PageResponseDTO<MemberConvertDTO> listMember = memberService.listMember(pageRequestDTO);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        model.addAttribute("listMember", listMember);
        model.addAttribute("email", email);
        return "admin/member/list";
    }

    // GET : Read Member
    @GetMapping("read/{email}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'STORE')")
    public String getReadMember(@PathVariable("email") String email, Model model) {
        log.info("GET | Admin Member Read");
        memberService.searchUser(email); // 회원 존재 여부 Check
        MemberConvertDTO listMember = memberService.readMember(email);
        Long sno = listMember.getSno();
        model.addAttribute("listMember", listMember);
        model.addAttribute("sno", sno);
        return "admin/member/read";
    }

    // GET : Update Member
    @GetMapping("update/{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getUpdateMember(@PathVariable("email") String email, Model model) {
        log.info("GET | Admin Member Update");
        memberService.searchUser(email); // 회원 존재 여부 Check
        MemberConvertDTO listMember = memberService.readMember(email);
        model.addAttribute("listMember", listMember);
        return "admin/member/update";
    }

    // GET : Join Member
    @GetMapping("create")
    @PreAuthorize("permitAll")
    public String getCreateMember() {
        log.info("GET | Admin Member Create");
        return "admin/member/create";
    }

    // GET : Create Store
    @GetMapping("createstore")
    @PreAuthorize("permitAll")
    public String getCreateStoreMember() {
        log.info("GET | Admin Store Member Create");
        return "admin/member/createstore";
    }

    // POST : Update Member
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String postUpdateMember(@Valid MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Member Update");
        int updateMember = memberService.updateMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 업데이트 완료");
        return "redirect:/admin/member/read/" + memberConvertDTO.getEmail();
    }

    // POST : Join Member
    @PostMapping("create")
    @PreAuthorize("permitAll")
    public String postCreateMember(@Valid MemberConvertDTO memberConvertDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Member Join");
        memberService.checkEmailAlreadyExists(memberConvertDTO.getEmail()); // 이메일 존재 여부 Check
        int joinMember = memberService.joinMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
        return "redirect:/admin/member/list";
    }

    // POST : Join Store Member
    @PostMapping("createstore")
    @PreAuthorize("permitAll")
    public String postCreateStoreMember(@Valid MemberConvertDTO memberConvertDTO,
            RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Store Member Join");
        memberService.checkEmailAlreadyExists(memberConvertDTO.getEmail()); // 이메일 존재 여부 Check
        int joinStoreMember = memberService.joinStoreMember(memberConvertDTO);
        redirectAttributes.addFlashAttribute("message", "가맹점 회원 가입 완료");
        return "redirect:/admin/member/list";
    }

    // POST : Delete Member
    @PostMapping("delete/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String postDeleteMember(@PathVariable("email") String email, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Member Delete");
        memberService.searchUser(email); // 회원 존재 여부 Check
        int deletedMember = memberService.deleteMember(email);
        redirectAttributes.addFlashAttribute("message", "회원 탈퇴 완료");
        return "redirect:/admin/member/list";
    }
}

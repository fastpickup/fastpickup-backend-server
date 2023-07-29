package com.project.fastpickup.admin.store.controller;

/*
 * Date   : 2023.07.27
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

import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

// Store Controller Class
@Log4j2
@Controller
@RequestMapping("admin/store/")
public class StoreController {

    // 의존성 주입
    private final StoreService storeService;

    // Autowired 명시적 표시
    @Autowired
    public StoreController(StoreService storeService) {
        log.info("Constructor Called, Service Injected.");
        this.storeService = storeService;
    }

    //페이지 체크
    @ModelAttribute("pageName")
    public String pageName(){
        return "store";
    }

    // GET : List Store
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('USER')")
    public String getListStore(PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | Admin Store List");
        PageResponseDTO<StoreListDTO> listStore = storeService.listStore(pageRequestDTO);
        model.addAttribute("listStore", listStore);
        return "admin/store/list";
    }

    // GET : Create Store
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public String getCreateStore() {
        log.info("GET | Admin Store Create");
        return "admin/store/create";
    }

    // GET : Read Store
    @GetMapping("read/{sno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getReadStore(@PathVariable("sno") Long sno, Model model) {
        log.info("GET | Admin Store Read");
        StoreDTO listStore = storeService.readStore(sno);
        model.addAttribute("listStore", listStore);
        return "admin/store/read";
    }

    // GET : Update Store
    @GetMapping("update/{sno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateStore(@PathVariable("sno") Long sno, Model model) {
        log.info("GET | Admin Store Update");
        StoreDTO listStore = storeService.readStore(sno);
        model.addAttribute("listStore", listStore);
        return "admin/store/update";
    }

    // POST : Update Store
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('USER')")
    public String postUpdateStore(@Valid StoreUpdateDTO storeUpdateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Store Update");
        int updateStore = storeService.updateStore(storeUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "가맹점 업데이트 완료");
        return "redirect:/admin/store/read/" + storeUpdateDTO.getSno();
    }

    // POST : Create Store
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public String postCreateStore(@Valid StoreCreateDTO storeCreateDTO, Authentication authentication, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Store Create");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        storeCreateDTO.setEmail(email);
        int createStore = storeService.createStore(storeCreateDTO);
        redirectAttributes.addFlashAttribute("message", "가맹점 등록 완료");
        return "redirect:/admin/store/list";
    }

    // POST : Delete Store
    @PostMapping("delete/{sno}")
    @PreAuthorize("hasAnyRole('USER')")
    public String postDeleteStore(@PathVariable("sno") Long sno, RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Store Delete");
        int deleteStore = storeService.deleteStore(sno);
        redirectAttributes.addFlashAttribute("message", "가맹점 퇴출 완료");
        return "redirect:/admin/store/list";
    }
}

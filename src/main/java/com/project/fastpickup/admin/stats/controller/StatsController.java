package com.project.fastpickup.admin.stats.controller;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;
import com.project.fastpickup.admin.stats.service.StatsService;
import lombok.extern.log4j.Log4j2;

// Stats Controller Class
@Log4j2
@Controller
@RequestMapping("admin/stats/")
public class StatsController {

    private final StatsService statsService;

    // Autowired 명시적 표시
    @Autowired
    public StatsController(StatsService statsService) {
        log.info("Constructor Called, Service Injected.");
        this.statsService = statsService;
    }

    // 페이지 체크
    @ModelAttribute("pageName")
    public String pageName() {
        return "stats";
    }

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getStatsEntryData(Model model) {
        log.info("GET | Admin Store Entry Data");
        List<StoreEntryDataDTO> listStoreEntry = statsService.storeMonthEntryData();
        List<StoreEntryDataDTO> listStoreEntryDay = statsService.storeDayEntryData();
        List<MemberEntryDataDTO> listMemberEntry = statsService.memberMonthEntryData();
        List<MemberEntryDataDTO> listMemberEntryDay = statsService.memberDayEntryData();
        model.addAttribute("listStoreEntry", listStoreEntry);
        model.addAttribute("listStoreEntryDay", listStoreEntryDay);
        model.addAttribute("listMemberEntry", listMemberEntry);
        model.addAttribute("listMemberEntryDay", listMemberEntryDay);
        return "admin/stats/list";
    }
}

package com.project.fastpickup.stats.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;
import com.project.fastpickup.admin.stats.service.StatsService;

import lombok.extern.log4j.Log4j2;

// Stats Service
@Log4j2
@SpringBootTest
public class StatsServiceTests {

    // 의존성 주입
    @Autowired
    private StatsService statsService;

    @Test
    @Transactional
    @DisplayName("Service: 월별 가맹점 입점 통계")
    public void storeMonthEntryDataService() {
        log.info("=== Start Store Month Entry Data Service ===");
        List<StoreEntryDataDTO> list = statsService.storeMonthEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Store Month Entry Data Service ===");
    }

    @Test
    @Transactional
    @DisplayName("Service: 일별 가맹점 입점 통계")
    public void storeMonthEntryDayService() {
        log.info("=== Start Store Month Entry Data Service ===");
        List<StoreEntryDataDTO> list = statsService.storeDayEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Store Month Entry Data Service ===");
    }

    @Test
    @Transactional
    @DisplayName("Service: 월별 회원가입 통계")
    public void memberMonthEntryDataService() {
        log.info("=== Start Member Month Entry Data Service ===");
        List<MemberEntryDataDTO> list = statsService.memberMonthEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Member Month Entry Data Service ===");
    }

    @Test
    @Transactional
    @DisplayName("Service: 일별 회원가입 통계")
    public void memberMonthEntryDayService() {
        log.info("=== Start Member Day Entry Data Service ===");
        List<MemberEntryDataDTO> list = statsService.memberDayEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Member Day Entry Data Service ===");
    }
}

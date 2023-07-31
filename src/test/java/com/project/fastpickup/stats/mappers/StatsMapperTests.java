package com.project.fastpickup.stats.mappers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;
import com.project.fastpickup.admin.stats.mappers.StatsMapper;

import lombok.extern.log4j.Log4j2;

// Stats Mapper Test Class
@Log4j2
@SpringBootTest
public class StatsMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private StatsMapper statsMapper;

    @Test
    @Transactional
    @DisplayName("Mapper: 월별 가맹점 입점 통계")
    public void storeMonthEntryDataMapper() {
        log.info("=== Start Sotre Month Entry Data ===");
        List<StoreEntryDataDTO> list = statsMapper.storeMonthEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be not Null");
        log.info("=== Start Sotre Month Entry Data ===");
    }

    @Test
    @Transactional
    @DisplayName("Mapper: 월별 가맹점 입점 통계")
    public void storeMonthEntryDayMapper() {
        log.info("=== Start Sotre Day Entry Data ===");
        List<StoreEntryDataDTO> list = statsMapper.storeDayEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be not Null");
        log.info("=== Start Sotre Day Entry Data ===");
    }

    @Test
    @Transactional
    @DisplayName("Mapper: 일별 회원가입 통계")
    public void memberDayEntryData() {
        log.info("=== Start Member Day Entry Data ===");
        List<MemberEntryDataDTO> list = statsMapper.memberDayEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Member Day Entry Data ===");
    }

    @Test
    @Transactional
    @DisplayName("Mapper: 월별 회원가입 통계")
    public void memberMonthEntryData() {
        log.info("=== Start Member Month Entry Data ===");
        List<MemberEntryDataDTO> list = statsMapper.memberMonthEntryData();
        log.info(list);
        Assertions.assertNotNull(list, "list Should Be Not Null");
        log.info("=== End Member Month Entry Data ===");
    }
}

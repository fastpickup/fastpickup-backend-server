package com.project.fastpickup.store.service;

import java.util.List;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreSalesDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class StoreServiceTests {

    // 의존성 주입
    @Autowired
    private StoreService storeService;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final String TEST_STORE_NAME = "교촌치킨";
    private static final String TEST_STORE_NUMBER = "12342-23423-2342";
    private static final String TEST_STORE_ADDRESS = "경기도 성남시";
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_SNO = 1L;
    private static final String TEST_STORE_PHONE = "2342-323-423";

    // DTO 정의
    private StoreDTO storeDTO;
    private StoreUpdateDTO storeUpdateDTO;
    private StoreCreateDTO storeCreateDTO;

    // @BeforeEach 사용을 위한 셋팅
    @BeforeEach
    public void setUp() {
        storeCreateDTO = StoreCreateDTO.builder()
                .storeName(TEST_STORE_NAME)
                .storeNumber(TEST_STORE_NUMBER)
                .storeAddress(TEST_STORE_ADDRESS)
                .storePhone(TEST_STORE_PHONE)
                .email(TEST_EMAIL)
                .build();

        storeUpdateDTO = StoreUpdateDTO.builder()
                .sno(TEST_SNO)
                .storeName(TEST_STORE_NAME)
                .storeNumber(TEST_STORE_NUMBER)
                .storeAddress(TEST_STORE_ADDRESS)
                .storePhone(TEST_STORE_PHONE)
                .email(TEST_EMAIL)
                .build();
    }

    // Create Store Service
    @Test
    @Transactional
    @DisplayName("가맹점 정보 생성 테스트 서비스")
    public void createStoreServiceTest() {
        // GIVEN
        log.info("=== Start Create Store Service Test ===");
        // WHEN
        storeService.createStore(storeCreateDTO);
        // THEN
        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_STORE_NAME, "교촌치킨");
        Assertions.assertEquals(TEST_STORE_ADDRESS, "경기도 성남시");
        log.info("=== End Create Store Service Test ===");
    }

    // Read Store Service
    @Test
    @Transactional
    @DisplayName("가맹점 상세 정보 테스트 서비스")
    public void readStoreServiceTest() {
        // GIVEN
        log.info("=== Start Read Store Service Test ===");
        // WHEN
        StoreDTO readStore = storeService.readStore(TEST_SNO);
        // THEN
        Assertions.assertNotNull(readStore, "readStore Should Be Not Null");
        log.info("=== End Read Store Service Test ===");
    }

    // Delete Store Service
    @Test
    @Transactional
    @DisplayName("가맹점 삭제 테스트 서비스")
    public void deleteStoreServiceTest() {
        // GIVEN
        log.info("=== Start Delete Store Service Test ===");
        // WHEN
        storeService.deleteStore(TEST_SNO);
        // THEN
        StoreDTO deletedStore = storeService.readStore(TEST_SNO);
        Assertions.assertNull(deletedStore, "deletedStore Should Be Null");
        log.info("=== End Delete Store Service Test ===");
    }

    // List Store Service
    @Test
    @Transactional
    @DisplayName("가맹점 리스트 테스트 서비스")
    public void listStoreServiceTest() {
        // GIVEN
        log.info("=== Start List Store Service Test ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<StoreListDTO> listStore = storeService.listStore(pageRequestDTO);
        // THEN
        log.info(listStore.getList());
        Assertions.assertNotNull(listStore, "listStore Should Be Not Null");
        log.info("=== End List Store Service Test ===");
    }

    // Sales Month Service
    @Test
    @Transactional
    @DisplayName("Service: 가맹점 월별 매출 통계")
    public void salesMonthStoreService() {
        // GIVEN
        log.info("=== Start Sales Month Service ===");
        // WHEN
        List<StoreSalesDTO> storeSalesDTO = storeService.salesMonth(TEST_SNO);
        log.info(storeSalesDTO);
        Assertions.assertNotNull(storeSalesDTO, "storeSalesDTO Should Be Not Null");
        log.info("=== End Sales Month Service ===");
    }

    // Sales Day Service
    @Test
    @Transactional
    @DisplayName("Service: 가맹점 일별 매출 통계")
    public void salesDayStoreService() {
        // GIVEN
        log.info("=== Start Sales Day Service ===");
        // WHEN
        List<StoreSalesDTO> storeSalesDTO = storeService.salesDate(TEST_SNO);
        log.info(storeSalesDTO);
        Assertions.assertNotNull(storeSalesDTO, "storeSalesDTO Should Be Not Null");
        log.info("=== End Sales Day Service ===");
    }
}

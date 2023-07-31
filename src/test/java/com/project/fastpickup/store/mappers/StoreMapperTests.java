package com.project.fastpickup.store.mappers;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

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
import com.project.fastpickup.admin.store.mappers.StoreMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

// Store Mapper Test Class
@Log4j2
@SpringBootTest
public class StoreMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private StoreMapper storeMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final String TEST_STORE_NAME = "교촌치킨";
    private static final String TEST_STORE_NUMBER = "12342-23423-2342";
    private static final String TEST_STORE_ADDRESS = "경기도 성남시";
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_STORE_PHONE = "12321-23423-523532";
    private static final Long TEST_SNO = 1L;

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
                .email(TEST_EMAIL)
                .storePhone(TEST_STORE_PHONE)
                .build();

        storeUpdateDTO = StoreUpdateDTO.builder()
                .sno(TEST_SNO)
                .storeName(TEST_STORE_NAME)
                .storeNumber(TEST_STORE_NUMBER)
                .storeAddress(TEST_STORE_ADDRESS)
                .email(TEST_EMAIL)
                .storePhone(TEST_STORE_PHONE)
                .build();
    }

    // Create Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹정 정보 생성 테스트")
    public void createStoreMapperTest() {
        // GIVEN
        log.info("=== Start Create Store Mapper Test ===");
        // WHEN
        storeMapper.createStore(storeCreateDTO);
        // THEN
        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_STORE_NAME, "교촌치킨");
        Assertions.assertEquals(TEST_STORE_NUMBER, "12342-23423-2342");
        log.info("=== End Create Store Mapper Test");
    }

    // Read Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 상세 조회 테스트")
    public void readStoreMapperTest() {
        // GIVEN
        log.info("=== Start Read Store Mapper Test ===");
        // WHEN
        StoreDTO readStore = storeMapper.readStore(TEST_SNO);
        log.info("가맹점: " + readStore);
        Assertions.assertNotNull(readStore, "readStore Should Be Not Null");
        log.info("=== End Read Store Mapper Test ===");
    }

    // Delete Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 삭제 테스트")
    public void deleteStoreMapperTest() {
        // GIVEN
        log.info("=== Start Delete Store Mapper Test ===");
        // WHEN
        storeMapper.deleteStore(TEST_SNO);
        // THEN
        StoreDTO readStore = storeMapper.readStore(TEST_SNO);
        Assertions.assertNull(readStore, "readStore Should Be Null");
        log.info("=== End Delete Store Mapper Test ===");
    }

    // Update Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 업데이트 테스트")
    public void updateStoreMapperTest() {
        // GIVEN
        log.info("=== Start Update Store Mapper Test ===");
        // WHEN
        storeMapper.updateStore(storeUpdateDTO);
        // THEN
        StoreDTO updatedStore = storeMapper.readStore(TEST_SNO);
        Assertions.assertNotNull(updatedStore, "updatedStore Should Be Not Null");
        Assertions.assertEquals(storeUpdateDTO.getEmail(), "thistrik@naver.com");
        Assertions.assertEquals(storeUpdateDTO.getStoreAddress(), "경기도 성남시");
        Assertions.assertEquals(storeUpdateDTO.getStoreNumber(), "12342-23423-2342");
        log.info("=== End Update Store Mapper Test ===");
    }

    // List Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 리스트 테스트")
    public void listStoreMapperTest() {
        // GIVEN
        log.info("=== Start List Store Mapper Test ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<StoreListDTO> listStore = storeMapper.listStore(pageRequestDTO);
        log.info(listStore);
        Assertions.assertNotNull(listStore, "listStore Should Be Not Null");
        log.info("=== End List Store Mapper Test ===");
    }

    // Total Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 토탈 테스트")
    public void totalStoreMapperTest() {
        // GIVEN
        log.info("=== Start Total Store Mapper Test ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        int totalCount = storeMapper.total(pageRequestDTO);
        // THEN
        log.info(totalCount);
        Assertions.assertNotNull(totalCount, "totalCount Should Be Not Null");
        log.info("=== End Total Store Mapper Test ===");
    }

    // Sales Month
    @Test
    @Transactional
    @DisplayName("가맹점 월별 매출")
    public void salesMonthStoreMapperTest() {
        log.info("=== Start Sales Month Mapper Test ===");
        List<StoreSalesDTO> storeSalesDTO = storeMapper.salesMonth(TEST_SNO);
        log.info(storeSalesDTO);
        Assertions.assertNotNull(storeSalesDTO);
        Assertions.assertNotNull(storeSalesDTO, "storeSalesDTO Should Be Not Null");
        log.info("=== End Sales Month Mapper Test ===");
    }

    // Sales Day
    @Test
    @Transactional
    @DisplayName("가맹점 일별 매출")
    public void salesDayStoreMapperTest() {
        log.info("=== Start Sales Day Mapper Test ===");
        List<StoreSalesDTO> storeSalesDTO = storeMapper.salesDate(TEST_SNO);
        log.info(storeSalesDTO);
        Assertions.assertNotNull(storeSalesDTO, "storeSalesDTO Should Be Not Null");
        log.info("=== End Sales Day Mapper Test ===");
    }
}
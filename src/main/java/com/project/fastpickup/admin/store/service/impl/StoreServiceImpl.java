package com.project.fastpickup.admin.store.service.impl;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreSalesDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.store.exception.StoreNotFoundException;
import com.project.fastpickup.admin.store.mappers.StoreMapper;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

// Store ServiceImpl Class
@Log4j2
@Service
public class StoreServiceImpl implements StoreService {

    // 의존성 주입
    private final StoreMapper storeMapper;

    // Autowired 명시적 표시
    @Autowired
    public StoreServiceImpl(StoreMapper storeMapper) {
        log.info("Constructor Called, Mapper Injected.");
        this.storeMapper = storeMapper;
    }

    // Create Store ServiceImpl
    @Override
    @Transactional
    public int createStore(StoreCreateDTO storeCreateDTO) {
        log.info("Is Running Create Store ServiceImpl");
        return storeMapper.createStore(storeCreateDTO);
    }

    // Read Store ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public StoreDTO readStore(Long sno) {
        log.info("Is Running Read Store ServiceImpl");
        return storeMapper.readStore(sno);
    }

    // Update Store ServiceImpl
    @Override
    @Transactional
    public int updateStore(StoreUpdateDTO storeUpdateDTO) {
        log.info("Is Running Update Store ServieImpl");
        return storeMapper.updateStore(storeUpdateDTO);
    }

    // Delete Store ServiceImpl
    @Override
    @Transactional
    public int deleteStore(Long sno) {
        log.info("Is Running Delete Store ServiceImpl");
        return storeMapper.deleteStore(sno);
    }

    // List Store Serviceimpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<StoreListDTO> listStore(PageRequestDTO pageRequestDTO) {
        log.info("Is Running List Store ServiceImpl");
        List<StoreListDTO> list = storeMapper.listStore(pageRequestDTO);
        int total = storeMapper.total(pageRequestDTO);
        return PageResponseDTO.<StoreListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // Sales Date ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public List<StoreSalesDTO> salesDate(Long sno) {
        log.info("Is Running Sales Date ServiceImpl");
        return storeMapper.salesDate(sno);
    }

    // Sales Month ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public List<StoreSalesDTO> salesMonth(Long sno) {
        log.info("Is Running Sales Month ServiceImpl");
        return storeMapper.salesMonth(sno);
    }

    // Check Store Number ServiceImpl
    @Override
    @Transactional
    public void checkStoreNumber(Long sno) {
        log.info("Is Runnint Check Store Number");
        if (storeMapper.duplicateSno(sno) == 0) {
            throw new StoreNotFoundException("찾으시는 가맹점이 없습니다.");
        }
    }

    // List For Store ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<StoreListDTO> listForStore(PageRequestDTO pageRequestDTO, Long sno) {
        log.info("Is Running List For Store ServiceImpl");
        List<StoreListDTO> list = storeMapper.listForStore(pageRequestDTO, sno);
        int total = storeMapper.totalForStore(pageRequestDTO, sno);
        return PageResponseDTO.<StoreListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}

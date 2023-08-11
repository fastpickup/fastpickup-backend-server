package com.project.fastpickup.admin.store.service.impl;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreSalesDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.store.exception.StoreNotFoundException;
import com.project.fastpickup.admin.store.mappers.StoreFileMapper;
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
    private final StoreFileMapper storeFileMapper;

    // Autowired 명시적 표시
    @Autowired
    public StoreServiceImpl(StoreMapper storeMapper, StoreFileMapper storeFileMapper) {
        log.info("Constructor Called, Mapper Injected.");
        this.storeMapper = storeMapper;
        this.storeFileMapper = storeFileMapper;
    }

    // Create Store ServiceImpl
    @Override
    @Transactional
    public Long createStore(StoreCreateDTO storeCreateDTO) {
        log.info("Is Running Create Store ServiceImpl");
        Long count = storeMapper.createStore(storeCreateDTO);
        List<String> fileNames = storeCreateDTO.getFileName();
        Long sno = storeCreateDTO.getSno();

        if (storeCreateDTO.getFileName() != null && !storeCreateDTO.getFileName().isEmpty()) {
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                AtomicInteger index = new AtomicInteger(0);
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);
                
                return Map.of("uuid", uuid, "fileName", fileName, "sno", "" + sno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            storeFileMapper.createStoreFile(list);
        }
        return storeCreateDTO.getSno();
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
    public Long updateStore(StoreUpdateDTO storeUpdateDTO) {
        log.info("Is Running Update Store ServieImpl");
        Long count = storeMapper.updateStore(storeUpdateDTO);
        storeFileMapper.deleteStoreFile(storeUpdateDTO.getSno());

        if (storeUpdateDTO.getFileName() != null & !storeUpdateDTO.getFileName().isEmpty()) {
            AtomicInteger index = new AtomicInteger(0);
            List<String> fileNames = storeUpdateDTO.getFileName();
            Long sno = storeUpdateDTO.getSno();
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "sno", "" + sno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            storeFileMapper.createStoreFile(list);
        }
        return storeUpdateDTO.getSno();
    }

    // Delete Store ServiceImpl
    @Override
    @Transactional
    public int deleteStore(Long sno) {
        log.info("Is Running Delete Store ServiceImpl");
        storeFileMapper.deleteStoreFile(sno);
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

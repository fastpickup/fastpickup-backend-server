package com.project.fastpickup.admin.store.service;

import java.util.List;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreSalesDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

// Store Service Interface 
public interface StoreService {

    // Create Store
    int createStore(StoreCreateDTO storeCreateDTO);

    // Read Store
    StoreDTO readStore(Long sno);

    // Update Store
    int updateStore(StoreUpdateDTO storeUpdateDTO);

    // Delete Store
    int deleteStore(Long sno);

    // List Store
    PageResponseDTO<StoreListDTO> listStore(PageRequestDTO pageRequestDTO);

    // Sales Date
    List<StoreSalesDTO> salesDate(Long sno);

    // Sales Month
    List<StoreSalesDTO> salesMonth(Long sno);

    // Check Store Number
    void checkStoreNumber(Long sno);

    // List For Store
    PageResponseDTO<StoreListDTO> listForStore(PageRequestDTO pageRequestDTO, Long sno);
}

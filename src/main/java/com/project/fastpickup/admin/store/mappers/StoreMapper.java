package com.project.fastpickup.admin.store.mappers;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.store.dto.StoreCreateDTO;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreListDTO;
import com.project.fastpickup.admin.store.dto.StoreSalesDTO;
import com.project.fastpickup.admin.store.dto.StoreUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

// Store Mapper Interface
@Mapper
public interface StoreMapper {

    // Create Store
    int createStore(StoreCreateDTO storeCreateDTO);

    // Read Store
    StoreDTO readStore(Long sno);

    // Update Store
    int updateStore(StoreUpdateDTO storeUpdateDTO);

    // Delete store
    int deleteStore(Long sno);

    // List Store
    List<StoreListDTO> listStore(PageRequestDTO pageRequestDTO);

    // Total Store
    int total(PageRequestDTO pageRequestDTO);

    // Sales Date
    List<StoreSalesDTO> salesDate(Long sno);

    // Sales Month
    List<StoreSalesDTO> salesMonth(Long sno);

    // duplicate Sno
    int duplicateSno(Long sno);

    // List For Store
    List<StoreListDTO> listForStore(@Param("pr") PageRequestDTO pageRequestDTO, @Param("sno") Long sno);

    // List For Store 
    int totalForStore(@Param("pr") PageRequestDTO pageRequestDTO, @Param("sno") Long sno);
}

package com.project.fastpickup.admin.order.mappers;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.apache.ibatis.annotations.Mapper;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryCreateDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryUpdateDTO;

@Mapper
public interface OrderHistoryMapper {
    
    // Create History 
    Long createHistory(OrderHistoryCreateDTO orderHistoryCreateDTO);

    // Update History 
    Long updateHistory(OrderHistoryUpdateDTO orderHistoryUpdateDTO);

    // Read History
    OrderHistoryDTO readHistory(Long ono);
}

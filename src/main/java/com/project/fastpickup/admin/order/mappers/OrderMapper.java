package com.project.fastpickup.admin.order.mappers;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.order.OrderUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

// Order Mapper Interface 
@Mapper
public interface OrderMapper {

    /// Create Order
    Long createOrder(OrderCreateDTO orderCreateDTO);

    // Read Order
    OrderDTO readOrder(Long ono);

    // Update Order
    Long updateOrder(OrderUpdateDTO orderUpdateDTO);

    // Delete Order
    int deleteOrder(Long ono);

    // List Order
    List<OrderAndHistoryListDTO> listOrderAndHistory(PageRequestDTO pageRequestDTO);

    // List For Store Order
    List<OrderAndHistoryListDTO> listOrderForStoreAndHistory(@Param("pr") PageRequestDTO pageRequestDTO,
            @Param("sno") Long sno);

    // Total For Store
    int totalForStore(@Param("pr") PageRequestDTO pageRequestDTO, @Param("sno") Long sno);
    
    // Total
    int total(PageRequestDTO pageRequestDTO);

    // Duplicate Ono
    int duplicateOno(Long ono);
}

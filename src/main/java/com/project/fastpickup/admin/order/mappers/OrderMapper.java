package com.project.fastpickup.admin.order.mappers;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

    // total
    int total(PageRequestDTO pageRequestDTO);
    
}

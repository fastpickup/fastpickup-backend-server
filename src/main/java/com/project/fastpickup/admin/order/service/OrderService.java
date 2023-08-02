package com.project.fastpickup.admin.order.service;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.order.OrderUpdateDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

// Order Service Interface
public interface OrderService {

    // Create Order
    Long createOrder(OrderCreateDTO orderCreateDTO);

    // Read Order 
    OrderDTO readOrder(Long ono);

    // Update Order
    Long updateOrder(OrderUpdateDTO orderUpdateDTO);

    // Delete Order
    int deleteOrder(Long ono);

    // List Order
    PageResponseDTO<OrderAndHistoryListDTO> listOrderAndHistory(PageRequestDTO pageRequestDTO);

    // List For Store Order 
    PageResponseDTO<OrderAndHistoryListDTO> listForStoreOrderAndHistory(PageRequestDTO pageRequestDTO, Long sno);

    // Update Order History
    Long updateHistory(OrderHistoryUpdateDTO orderHistoryUpdateDTO);

    // Read Order History
    OrderHistoryDTO readHistory(Long ono);
    
    // Check Order Number
    void checkOrderNumber(Long ono);
}

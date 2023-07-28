package com.project.fastpickup.admin.order.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.project.fastpickup.admin.order.dto.order.OrderDTO;

// Order Mapper Interface 
@Mapper
public interface OrderMapper {
    
    /// Create Order 
    int createOrder();

    // Read Order 
    OrderDTO readOrder();

    // Update Order 
    int updateOrder();

    // Delete Order 
    int deleteOrder();

    // List Order
    
}

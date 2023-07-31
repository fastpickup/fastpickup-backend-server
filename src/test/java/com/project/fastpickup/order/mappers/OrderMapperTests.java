package com.project.fastpickup.order.mappers;

/*
 * Date   : 2023.07.28
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

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.order.OrderUpdateDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryCreateDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryUpdateDTO;
import com.project.fastpickup.admin.order.mappers.OrderHistoryMapper;
import com.project.fastpickup.admin.order.mappers.OrderMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

// Order Mapper Test Class
@Log4j2
@SpringBootTest
public class OrderMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private OrderHistoryMapper orderHistoryMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    // tbl_product
    private static final Long TEST_PNO = 22L;

    // tbl_member
    private static final String TEST_EMAIL = "thistrik@naver.com";

    private static final String TEST_EMAIL_VERSION_2 = "whtkdgml3627@naver.com";

    // tbl_store
    private static final Long TEST_SNO = 1L;

    // tbl_order
    private static final Long TEST_ONO = 4L;
    private static final int TEST_ORDER_COUNT = 5;
    private static final int TEST_ORDER_COUNT_VERSION_2 = 7;
    private static final String TEST_ORDER_STATUS_REJECT = "반려";
    private static final String TEST_ORDER_STATUS_COMPLETE = "완료";
    private static final String TEST_ORDER_STATUS_CONFIRM = "주문확인";

    // tbl_order_history
    private static final Long TEST_ORDER_HISTORY = 4L;

    // DTO 정의
    private OrderCreateDTO orderCreateDTO;

    private OrderCreateDTO orderMemberCreateOrderDTO;

    private OrderUpdateDTO orderUpdateDTO;
    private OrderHistoryCreateDTO orderHistoryCreateDTO;
    private OrderHistoryUpdateDTO orderHistoryUpdateDTO;

    @BeforeEach
    public void setUp() {

        orderMemberCreateOrderDTO = OrderCreateDTO.builder()
                .orderCount(TEST_ORDER_COUNT)
                .email(TEST_EMAIL_VERSION_2)
                .sno(TEST_SNO)
                .pno(TEST_PNO)
                .build();

        orderCreateDTO = OrderCreateDTO.builder()
                .orderCount(TEST_ORDER_COUNT)
                .email(TEST_EMAIL)
                .sno(TEST_SNO)
                .pno(TEST_PNO)
                .build();

        orderUpdateDTO = OrderUpdateDTO.builder()
                .ono(TEST_ONO)
                .orderCount(TEST_ORDER_COUNT_VERSION_2)
                .build();

        orderHistoryCreateDTO = OrderHistoryCreateDTO.builder()
                .orderStatus(TEST_ORDER_STATUS_REJECT)
                .build();

        orderHistoryUpdateDTO = OrderHistoryUpdateDTO.builder()
                .orderHistory(TEST_ORDER_HISTORY)
                .orderStatus(TEST_ORDER_STATUS_COMPLETE)
                .build();
    }

    // Update Order Count
    @Test
    @Transactional
    @DisplayName("주문의 주문개수 업데이트")
    public void updateOrderCount() {
        // GIVEN
        log.info("=== Start Update Order OrderCount Mapper ===");
        // WHEN
        orderMapper.updateOrder(orderUpdateDTO);
        // THEN
        OrderDTO readUpdatedOrder = orderMapper.readOrder(TEST_ONO);
        Assertions.assertNotNull(readUpdatedOrder, "readUpatedOrder Should Be Not Null");
        Assertions.assertEquals(orderUpdateDTO.getOrderCount(), 7, "OrderCount Should Be 7");
        log.info("=== End Update Order OrderCount Mapper ===");
    }

    // Update OrderHistory Status
    @Test
    @Transactional
    @DisplayName("주문 이력의 상태 업데이트")
    public void updateHistoryStatus() {
        // GVIEN
        log.info("=== Start Update History Status Mapper===");
        // WHEN
        orderHistoryMapper.updateHistory(orderHistoryUpdateDTO);
        // THEN
        OrderHistoryDTO updatedOrderStatus = orderHistoryMapper.readHistory(TEST_ONO);
        Assertions.assertNotNull(updatedOrderStatus, "updatedOrderStatus Should Be Not Null");
        Assertions.assertEquals(orderHistoryUpdateDTO.getOrderStatus(), "완료");
        log.info("=== End Update History Status Mapper ===");
    }

    // Read Order And OrderHistory And Product And Product Image And Store
    @Test
    @Transactional
    @DisplayName("주문, 주문이력, 상품, 상품이미지, 가맹점 정보 상세조회")
    public void readOrderProductOrderHistoryProductImageStore() {
        log.info("=== Start Read Order & Order History & Product & Product Image & Store Mapper ===");
        OrderDTO readEveryThing = orderMapper.readOrder(TEST_ONO);
        log.info(readEveryThing);
        Assertions.assertNotNull(readEveryThing, "readEveryThing Should Be Not Null");
        log.info("=== End Read Order & Order History & Product & Product Image & Store Mapper ===");
    }

    // Create Order And OrderHistory
    @Test
    @Transactional
    @DisplayName("주문과 주문이력 생성 매퍼 테스트")
    public void createOrderAndOrderHistoryMapper() {
        log.info("=== Start Create Order & History Mapper ===");
        Long createOrder = orderMapper.createOrder(orderCreateDTO);

        Long ono = orderCreateDTO.getOno();
        orderHistoryCreateDTO.setOno(ono);

        Long createOrderHistory = orderHistoryMapper.createHistory(orderHistoryCreateDTO);

        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_ORDER_COUNT, 5L);
        Assertions.assertEquals(TEST_SNO, 1L);
        Assertions.assertEquals(TEST_PNO, 22L);
        log.info("=== End Create Order & History Mapper ===");
    }

    // Create Order And Order History
    @Test
    @Transactional
    @DisplayName("주문과 주문이력 다른 멤버 생성 테스트")
    public void createOrderAndOrderHistoryOtherMember() {
        log.info("=== Start Create Order & History Other Mapper ===");
        Long createOrder = orderMapper.createOrder(orderMemberCreateOrderDTO);

        Long ono = orderMemberCreateOrderDTO.getOno();
        orderHistoryCreateDTO.setOno(ono);
        Long createOrderHistory = orderHistoryMapper.createHistory(orderHistoryCreateDTO);

        log.info("=== End Create Order & History Other Mapper ===");
    }

    // List Order And Order History And Product And Product Image
    @Test
    @Transactional
    @DisplayName("주문, 주문이력, 상품, 상품이미지 리스트")
    public void listOrderAndOrderHistoryProductProductImage() {
        // GIVEN
        log.info("=== Start List Order & Order History & Product & Product Image Mapper ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<OrderAndHistoryListDTO> listEveryThing = orderMapper.listOrderAndHistory(pageRequestDTO);
        // THEN
        log.info(listEveryThing);
        Assertions.assertNotNull(listEveryThing, "listEveryThing Should Be Not Null");
        log.info("=== End List Order & Order History & Product & Product Image Mapper ===");
    }
}

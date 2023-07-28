package com.project.fastpickup.order.service;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

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
import com.project.fastpickup.admin.order.service.OrderService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

// Order Service Test Class
@Log4j2
@SpringBootTest
public class OrderServiceTests {

    // 의존성 주입
    @Autowired
    private OrderService orderService;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    // tbl_product
    private static final Long TEST_PNO = 22L;

    // tbl_member
    private static final String TEST_EMAIL = "thistrik@naver.com";

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
    private OrderUpdateDTO orderUpdateDTO;
    private OrderHistoryCreateDTO orderHistoryCreateDTO;
    private OrderHistoryUpdateDTO orderHistoryUpdateDTO;

    @BeforeEach
    public void setUp() {
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

    // Create Order And OrderHistory
    @Test
    @Transactional
    @DisplayName("Service: 주문과 주문이력 생성")
    public void createOrderAndOrderHistory() {
        // GIVEN
        log.info("=== Start Create Order & History Service ===");
        // WHEN
        Long createOrder = orderService.createOrder(orderCreateDTO);
        // THEN
        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_ORDER_COUNT, 5L);
        Assertions.assertEquals(TEST_SNO, 1L);
        Assertions.assertEquals(TEST_PNO, 22L);
        log.info("=== End Create Order & History Service ===");
    }

    // Update Order Count
    @Test
    @Transactional
    @DisplayName("Service: 주문의 주문개수 업데이트 서비스")
    public void updateOrderCount() {
        // GIVEN
        log.info("=== Start Update Order OrderCount Service ===");
        // WHEN
        Long createOrder = orderService.updateOrder(orderUpdateDTO);
        // THEN
        OrderDTO readUpdatedOrder = orderService.readOrder(TEST_ONO);
        Assertions.assertNotNull(readUpdatedOrder, "readUpdatedOrder Should Be Not null");
        Assertions.assertEquals(orderUpdateDTO.getOrderCount(), 7, "OrderCount Should Be 7");
        log.info("=== End Update Order OrderCount Service ===");
    }

    // Read OrderHistory 
    @Test
    @Transactional
    @DisplayName("Service: 주문 상태 상세조회")
    public void readOrderHistory() {
        // GIVEN
        log.info("=== Start Read Order History ===");
        // WHEN 
        OrderHistoryDTO readHistory = orderService.readHistory(TEST_ONO);
        // THEN 
        log.info(readHistory);
        Assertions.assertNotNull(readHistory, "readHistory Should Be Not null");
        log.info("=== End Read Order History ===");
    }

    // Update OrderHistory Status
    @Test
    @Transactional
    @DisplayName("Service: 주문 이력의 상태 업데이트")
    public void updateHistoryStatus() {
        // GIVEN
        log.info("=== Start Update History Status Service ===");
        // WHEN
        orderService.updateHistory(orderHistoryUpdateDTO);
        // THEN
        OrderHistoryDTO updatedHistory = orderService.readHistory(TEST_ONO);
        Assertions.assertNotNull(updatedHistory, "updatedHistory Should Be Not Null");
        Assertions.assertEquals(orderHistoryUpdateDTO.getOrderStatus(), "완료");
        log.info("=== End Update History Status Service ===");
    }

    // Read Order And OrderHistory And Product And Product Image And Store
    @Test
    @Transactional
    @DisplayName("Service: 주문, 주문이력, 상품, 상품이미지, 가맹점 정보 상세조회")
    public void readOrderProductOrderHistoryProductImageStore() {
        // GIVEN
        log.info("=== Start Read Order & Order History & Product & Product Image & Store Service ===");
        // WHEN
        OrderDTO readEveryThing = orderService.readOrder(TEST_ONO);
        // THEN
        log.info(readEveryThing);
        Assertions.assertNotNull(readEveryThing, "readEveryThing Should Be Not null");
        log.info("=== End Read Order & Order History & Product & Product Image & Store Service ===");
    }

    // List Order And OrderHistory And Product And Product Image And Store
    @Test
    @Transactional
    @DisplayName("Service: 주문, 주문이력, 상품, 상품이미지, 가맹점 정보 리스트")
    public void listOrderProductOrderHistoryProductImageStore() {
        // GIVEN
        log.info("=== Strat List Order & Order History & Product & Product Image & Store ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<OrderAndHistoryListDTO> listEveryThing = orderService.listOrderAndHistory(pageRequestDTO);
        // THEN
        log.info(listEveryThing.getList());
        Assertions.assertNotNull(listEveryThing, "listEveryThing Should Be Not Null");
        log.info("=== End List Order & Order History & Product & Product Image & Store ===");
    }
}

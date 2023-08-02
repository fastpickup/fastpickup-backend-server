package com.project.fastpickup.admin.order.controller;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryUpdateDTO;
import com.project.fastpickup.admin.order.service.OrderService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("admin/order/")
public class OrderController {

    // 의존성 주입
    private final OrderService orderService;

    // Autowired 명시적 표시
    @Autowired
    public OrderController(OrderService orderService) {
        log.info("Constructor Called, Service Injected.");
        this.orderService = orderService;
    }

    // 페이지 체크
    @ModelAttribute("pageName")
    public String pageName() {
        return "order";
    }

    // GET : Create Order
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getCreateOrder() {
        log.info("GET | Admin Create Order");
        return "admin/order/create";
    }

    // GET : List Order
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getListOrder(PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | Admin List Order");
        PageResponseDTO<OrderAndHistoryListDTO> listOrder = orderService.listOrderAndHistory(pageRequestDTO);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/list";
    }

    // GET : List For Store Order
    @GetMapping("list/{sno}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getListOrderForStore(@PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | Admin List Order");
        PageResponseDTO<OrderAndHistoryListDTO> listOrder = orderService.listForStoreOrderAndHistory(pageRequestDTO, sno);
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("sno", sno);
        return "admin/order/list";
    }

    // GET : Read Order
    @GetMapping("read/{ono}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getReadOrder(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Read Order");
        orderService.checkOrderNumber(ono); // 주문 번호 Check
        OrderDTO listOrder = orderService.readOrder(ono);
        Long sno = listOrder.getSno();
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("sno", sno);
        return "admin/order/read";
    }

    // GET : Update Order
    @GetMapping("update/{ono}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getUpdateOrder(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Update Order");
        orderService.checkOrderNumber(ono); // 주문 번호 Check
        OrderDTO listOrder = orderService.readOrder(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/update";
    }

    // GET : Read OrderHistory
    @GetMapping("readhistory/{ono}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getReadOrderHistory(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Read Order History");
        orderService.checkOrderNumber(ono); // 주문 번호 Check
        OrderHistoryDTO listOrder = orderService.readHistory(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/readhistory";
    }

    // GET : Update Order History
    @GetMapping("updatehistory/{ono}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String getUpdateOrderHistory(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Update Order History");
        orderService.checkOrderNumber(ono); // 주문 번호 Check
        OrderHistoryDTO listOrder = orderService.readHistory(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/updatehistory";
    }

    // POST : Update Order History
    @PostMapping("updatehistory")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public String postUpdateOrderHistory(OrderHistoryUpdateDTO orderHistoryUpdateDTO,
            RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Update Order History");
        Long updateOrderHistory = orderService.updateHistory(orderHistoryUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "주문 상태 이력 업데이트 완료");
        return "redirect:/admin/order/read/" + orderHistoryUpdateDTO.getOno();
    }
}

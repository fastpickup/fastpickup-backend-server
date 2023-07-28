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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // GET : Create Order
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('USER')")
    public String getCreateOrder() {
        log.info("GET | Admin Create Order");
        return "admin/order/create";
    }

    // GET : List Order
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('USER')")
    public String getListOrder(PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | Admin List Order");
        PageResponseDTO<OrderAndHistoryListDTO> listOrder = orderService.listOrderAndHistory(pageRequestDTO);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/list";
    }

    // GET : Read Order
    @GetMapping("read/{ono}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getReadOrder(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Read Order");
        OrderDTO listOrder = orderService.readOrder(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/read";
    }

    // GET : Update Order
    @GetMapping("update/{ono}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateOrder(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Update Order");
        OrderDTO listOrder = orderService.readOrder(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/update";
    }

    // GET : Read OrderHistory
    @GetMapping("readhistory/{ono}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getReadOrderHistory(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Read Order History");
        OrderHistoryDTO listOrder = orderService.readHistory(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/readhistory";
    }

    // GET : Update Order History
    @GetMapping("updatehistory/{ono}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getUpdateOrderHistory(@PathVariable("ono") Long ono, Model model) {
        log.info("GET | Admin Update Order History");
        OrderHistoryDTO listOrder = orderService.readHistory(ono);
        model.addAttribute("listOrder", listOrder);
        return "admin/order/updatehistory";
    }

    // POST : Update Order History
    @PostMapping("updatehistory")
    @PreAuthorize("hasAnyRole('USER')")
    public String postUpdateOrderHistory(OrderHistoryUpdateDTO orderHistoryUpdateDTO,
            RedirectAttributes redirectAttributes) {
        log.info("POST | Admin Update Order History");
        Long updateOrderHistory = orderService.updateHistory(orderHistoryUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "주문 상태 이력 업데이트 완료");
        return "redirect:/admin/order/read/" + orderHistoryUpdateDTO.getOno();
    }
}

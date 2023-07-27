package com.project.fastpickup.admin.product.controller;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.util.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("admin/product/")
public class ProductController {

  //List Page
  @GetMapping("list")
  @PreAuthorize("permitAll")
  public void getList(PageRequestDTO pageRequestDTO){
    log.info("Product List...................................................");

  }
}

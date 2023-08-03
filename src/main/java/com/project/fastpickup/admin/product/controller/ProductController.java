package com.project.fastpickup.admin.product.controller;

/*
 * Date   : 2023.07.28
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.product.service.ProductService;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.ManegementCookie;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("admin/product/")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
  //의존성 주입
  private final ProductService productService;
  private final StoreService storeService;
  private final ManegementCookie manegementCookie;

  //페이지 체크
  @ModelAttribute("pageName")
  public String pageName(){
    return "product";
  }

  //List Page
  @GetMapping("list")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public void getList(
    PageRequestDTO pageRequestDTO, Model model
  ){
    log.info("GET | Product List =================");
    PageResponseDTO<ProductListDTO> list = productService.getList(pageRequestDTO);

    model.addAttribute("productList", list);
  }

  //List Store JSON DATA
  @GetMapping("{sno}/list")
  @PreAuthorize("permitAll")
  @ResponseBody
  public PageResponseDTO<ProductListDTO> getStoreList(
    @PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO
  ){

    return productService.getStoreList(pageRequestDTO, sno);
  }


  //Create Page
  //Get
  @GetMapping("create/{sno}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String getCreate(@PathVariable("sno") Long sno, Model model){
    log.info("GET | Product Create =================");

    StoreDTO storeDTO = storeService.readStore(sno);
    model.addAttribute("createSno", storeDTO);
    return "admin/product/create";
  }

  //Post
  @PostMapping("create")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String postCreate(
    ProductRegistDTO productRegistDTO, ProductCategoryDTO productCategoryDTO, RedirectAttributes rttr
  ){
    log.info("POST | Product Create =================");
    productService.createProduct(productRegistDTO, productCategoryDTO);

    //상품 등록 후 1회성 메세지 전달
    rttr.addFlashAttribute("message", "상품 등록이 완료 되었습니다.");

    return "redirect:/admin/product/list";
  }
  // /Create Controller Page

  //Read Page
  @GetMapping("read/{pno}")
  @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
  public String getRead(
    PageRequestDTO pageRequestDTO, @PathVariable("pno") Long pno, HttpServletRequest request, HttpServletResponse response, Model model
  ){
    log.info("GET | Product Read =================");
    productService.checkPno(pno); // Check Pno 
    ProductDTO productDTO = productService.selectOne(pno);
    StoreDTO storeDTO = storeService.readStore(productDTO.getSno());

    //조회수 체크
    if(manegementCookie.createCookie(request, response, pno)){
      productService.viewCount(pno);
      log.info("Cookie Check");
    }

    
    model.addAttribute("productRead", productDTO);
    model.addAttribute("productStore", storeDTO);
    return "admin/product/read";
  }

  //Delete Page
  @PostMapping("delete/{pno}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String postDelete(
    @PathVariable("pno") Long pno, RedirectAttributes rttr
  ){
    log.info("POST | Product Delete =================");
    productService.checkPno(pno); // Check Pno
    productService.deleteProduct(pno);

    //상품 삭제 후 1회성 메세지 전달
    rttr.addFlashAttribute("message", pno + "번 상품이 삭제 되었습니다.");

    return "redirect:/admin/product/list";
  }

  //Update Page
  @GetMapping("update/{pno}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String getUpdate(
    PageRequestDTO pageRequestDTO, @PathVariable("pno") Long pno, Model model
  ){
    log.info("GET | Product Update =================");
    productService.checkPno(pno); // Check Pno
    ProductDTO productDTO = productService.selectOne(pno);
    StoreDTO storeDTO = storeService.readStore(productDTO.getSno());

    model.addAttribute("productRead", productDTO);
    model.addAttribute("productStore", storeDTO);
    return "admin/product/update";
  }

  @PostMapping("update/{pno}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String postUpdate(
    ProductDTO productDTO, ProductCategoryDTO productCategoryDTO, RedirectAttributes rttr
  ){
    log.info("POST | Product Update =================");
    productService.checkPno(productDTO.getPno()); // Check Pno
    productService.updateProduct(productDTO, productCategoryDTO);

    rttr.addFlashAttribute("message", productDTO.getPno() + "번 상품이 수정 되었습니다.");

    return "redirect:/admin/product/read/" + productDTO.getPno();
  }
  // /Update Page
}

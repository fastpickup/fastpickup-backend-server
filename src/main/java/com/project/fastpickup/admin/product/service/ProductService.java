package com.project.fastpickup.admin.product.service;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ProductService {
  //Create Product
  void createProduct(ProductRegistDTO productRegistDTO);

  //List Product
  PageResponseDTO<ProductListDTO> getList(PageRequestDTO pageRequestDTO);
  //List count
  long listCount(PageRequestDTO pageRequestDTO);
  // /List Product

  //Read Product
  ProductDTO selectOne(Long pno);

  //Delete Product
  void deleteProduct(Long pno);

  //Update Product
  void updateProduct(ProductDTO productDTO);

  //Update ViewCount
  int viewCount(Long pno);

}

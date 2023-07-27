package com.project.fastpickup.admin.product.service.impl;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.product.mappers.ProductMapper;
import com.project.fastpickup.admin.product.service.ProductService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

  //의존성 주입
  private final ProductMapper productMapper;

  @Override
  public void createProduct(ProductRegistDTO productRegistDTO) {
    log.info("============ Product Create Service ============");
    int count = productMapper.createProduct(productRegistDTO);
  }

  @Override
  public PageResponseDTO<ProductListDTO> getList(PageRequestDTO pageRequestDTO) {
    return null;
  }

  @Override
  public long listCount(PageRequestDTO pageRequestDTO) {
    return 0;
  }

  @Override
  public ProductDTO selectOne(Long pno) {
    return null;
  }

  @Override
  public void deleteProduct(Long pno) {

  }

  @Override
  public void updateProduct(ProductDTO productDTO) {

  }

  @Override
  public int viewCount(Long pno) {
    return 0;
  }
}

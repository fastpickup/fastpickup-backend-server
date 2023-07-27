package com.project.fastpickup.product.mappers;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.product.mappers.ProductMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Product Mapper Test Class
@SpringBootTest
@Log4j2
public class ProductMapperTests {
  //의존성 주입
  @Autowired(required = false)
  private ProductMapper productMapper;

  //Test 시작시 메모리에 private static final 로 먼저 올려놓는다
  private static final Long TEST_PNO = 8L;
  private static final String TEST_PRODUCT_NAME = "Junit Product Name Mapper Test";
  private static final String TEST_PRODUCT_CONTENT = "Junit Product Content Mapper Test";
  private static final int TEST_PRODUCT_PRICE = 5000;
  private static final int TEST_PRODUCT_VIEWCOUNT = 0;
  private static final int TEST_PRODUCT_LIKECOUNT = 0;
  private static final int TEST_PRODUCT_RECOMMEND = 0;
  private static final boolean TEST_PRODUCT_DELETED = true;

  //DTO 정의
  private ProductDTO productDTO;
  private ProductListDTO productListDTO;
  private ProductRegistDTO productRegistDTO;
  private PageRequestDTO pageRequestDTO;

  //@BeforeEach 사용을 위한 셋팅
  @BeforeEach
  public void init(){
    //상품 등록
    productRegistDTO = ProductRegistDTO.builder()
      .productName(TEST_PRODUCT_NAME)
      .productContent(TEST_PRODUCT_CONTENT)
      .productPrice(TEST_PRODUCT_PRICE)
      .isRecommend(TEST_PRODUCT_RECOMMEND)
      .build();

    //상품 리스트
    pageRequestDTO = PageRequestDTO.builder().build();

    //상품 수정
    productDTO = ProductDTO.builder()
      .pno(TEST_PNO)
      .productName(TEST_PRODUCT_NAME)
      .productContent(TEST_PRODUCT_CONTENT)
      .productPrice(TEST_PRODUCT_PRICE)
      .isRecommend(TEST_PRODUCT_RECOMMEND)
      .build();
  }

  //Create Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 등록 매퍼 테스트")
  public void testCreateProduct(){
    //GIVEN
    log.info("=== Start Create Product Test Mapper ===");
    //WHEN
    int registCount = productMapper.createProduct(productRegistDTO);
    //THEN
    //ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, registCount, "Product Register Test Fail");
    log.info("=== End Create Product Test Mapper ===");
  }

  //List Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 리스트 매퍼 테스트")
  public void testListProduct(){
    //GIVEN
    log.info("=== Start List Product Test Mapper ===");
    //WHEN
    List<ProductListDTO> list = productMapper.getList(pageRequestDTO);
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Product List is Null");
    log.info("=== End List Product Test Mapper ===");
  }

  //Read Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 조회 매퍼 테스트")
  public void testReadProduct(){
    //GIVEN
    log.info("=== Start Read Product Test Mapper ===");
    //WHEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    //THEN
    log.info(dto);
    Assertions.assertNotNull(dto, "Product Read is Null");
    log.info("=== End Read Product Test Mapper ===");
  }

  //Delete Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 삭제 매퍼 테스트")
  public void testDeleteProduct(){
    //GIVEN
    log.info("=== Start Delete Product Test Mapper ===");
    //WHEN
    int deleteCount = productMapper.deleteProduct(TEST_PNO);
    //THEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, deleteCount, "Product Delete Not Success");
    Assertions.assertNotNull(dto, "Product Delete is Null");
    Assertions.assertFalse(dto.isDeletedProduct(), "Product Delete is Not False");
    log.info("=== End Delete Product Test Mapper ===");
  }

  //Update Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 수정 매퍼 테스트")
  public void testUpdateProduct(){
    //GIVEN
    log.info("=== Start Update Product Test Mapper ===");
    //WHEN
    int updateCount = productMapper.updateProduct(productDTO);
    //THEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, updateCount, "Product Update Not Success");
    Assertions.assertNotNull(dto, "Product Update is Null");
    //Assertions.assertEquals(TEST_PRODUCT_NAME, "Junit Product Name Mapper Test2");
    log.info("=== End Update Product Test Mapper ===");
  }

  //List Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 리스트 토탈 매퍼 테스트")
  public void testListCountProduct(){
    //GIVEN
    log.info("=== Start List Count Product Test Mapper ===");
    //WHEN
    long listTotal = productMapper.listCount(pageRequestDTO);
    //THEN
    log.info(listTotal);
    Assertions.assertNotNull(listTotal, "Product List is Null");
    log.info("=== End List Count Product Test Mapper ===");
  }

}

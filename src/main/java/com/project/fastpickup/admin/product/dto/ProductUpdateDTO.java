package com.project.fastpickup.admin.product.dto;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductUpdateDTO {
  //변수
  private Long pno;                     //PK
  private String productName;           //상품명
  private String productContent;        //상품상세
  private int productPrice;             //상품가격
  private boolean isDeletedProduct;     //상품삭제여부
  private List<String> fileNames;       //파일명
}

package com.project.fastpickup.admin.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRegistDTO {
  //변수
  private String productName;           //상품명
  private String productContent;        //상품상세
  private int productPrice;             //상품가격
  private int isRecommend;              //추천상품여부
  private List<String> fileNames;       //파일명
}

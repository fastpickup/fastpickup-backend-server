package com.project.fastpickup.admin.product.mappers;

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;

public interface ProductCategoryMapper {
  //Create Product Category
  int createCategory(ProductCategoryDTO productCategoryDTO);
}

package com.project.fastpickup.admin.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreListDTO {
    // tbl_store
    @NotNull
    private Long sno;
    @NotBlank
    private String storeName;
    @NotBlank
    private String storeNumber;
    @NotBlank
    private String storeAddress;
    @NotBlank
    private String email;
    @NotBlank  
    private String storePhone;
    @NotBlank
    private String fileName;
}

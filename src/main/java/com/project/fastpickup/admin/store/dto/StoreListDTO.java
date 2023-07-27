package com.project.fastpickup.admin.store.dto;

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
    private Long sno;
    private String storeName;
    private String storeNumber;
    private String storeAddress;
    private String email;
}

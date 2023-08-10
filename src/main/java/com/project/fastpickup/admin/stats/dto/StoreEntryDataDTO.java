package com.project.fastpickup.admin.stats.dto;

/*
 * Date   : 2023.07.31
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
public class StoreEntryDataDTO {
    private Long sno;
    private String storeName;
    private String registMonth;
    private Long storeCount;
    private String registDate;
    private String formattedRegistDate;
}

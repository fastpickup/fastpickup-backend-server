package com.project.fastpickup.admin.store.dto;

import java.time.LocalDate;

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
public class StoreSalesDTO {
    private Long sno;
    private LocalDate registDate;
    private int totalSales; 
    private String registMonth;
    private String productName;
}

package com.project.fastpickup.admin.store.dto;

import java.time.LocalDate;
import java.time.YearMonth;

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

package com.project.fastpickup.admin.stats.dto;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    
    @NotNull(message = "Sno Cant Not Be Null")
    private Long sno;

    @NotBlank(message = "Store Name Cant Not Be Balnk")
    private String storeName;

    @NotBlank(message = "RegistMonth Cant Not Be Blank")
    private String registMonth;

    @NotNull(message = "StoreCount Can Not Be Null")
    private Long storeCount;

    @NotBlank(message = "RegistDate Cant Not Be Blank")
    private String registDate;

    @NotBlank(message = "ForMattedReigstDate Can Not Be Blank")
    private String formattedRegistDate;
}

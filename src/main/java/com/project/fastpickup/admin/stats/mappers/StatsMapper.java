package com.project.fastpickup.admin.stats.mappers;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;

// Status Mapper Interface 
@Mapper
public interface StatsMapper {

    // 가맹점 월별 입점 통계 
    List<StoreEntryDataDTO> storeMonthEntryData();

    // 가맹점 일별 입점 통계 
    List<StoreEntryDataDTO> storeDayEntryData();

    // 월별 회원가입 통계 
    List<MemberEntryDataDTO> memberMonthEntryData();

    // 일별 회원가입 통계 
    List<MemberEntryDataDTO> memberDayEntryData();
}

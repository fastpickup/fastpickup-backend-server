package com.project.fastpickup.admin.stats.service;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;

// Stats Service Interface
@Service
public interface StatsService {

    // 월별 가맹점 입점 데이터
    List<StoreEntryDataDTO> storeMonthEntryData();

    // 일별 가맹점 입점 데이터 
    List<StoreEntryDataDTO> storeDayEntryData();

    // 월별 회원가입 데이터 
    List<MemberEntryDataDTO> memberMonthEntryData();

    // 일별 회원가입 데이터 
    List<MemberEntryDataDTO> memberDayEntryData();
}

package com.project.fastpickup.admin.stats.service.impl;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.stats.dto.MemberEntryDataDTO;
import com.project.fastpickup.admin.stats.dto.StoreEntryDataDTO;
import com.project.fastpickup.admin.stats.mappers.StatsMapper;
import com.project.fastpickup.admin.stats.service.StatsService;

import lombok.extern.log4j.Log4j2;

// Stats ServiceImpl Class
@Log4j2
@Service
public class StatsServiceImpl implements StatsService {

  // 의존성 주입
  private final StatsMapper statsMapper;

  // Autowired 명시적 표시
  @Autowired
  public StatsServiceImpl(StatsMapper statsMapper) {
    this.statsMapper = statsMapper;
  }

  // Store Month Entry Data ServiceImpl
  @Override
  @Transactional(readOnly = true)
  public List<StoreEntryDataDTO> storeMonthEntryData() {
    log.info("Is Running Store Month Entry Data ServiceImpl");
    return statsMapper.storeMonthEntryData();
  }

  // Member Month Entry Data ServiceImpl
  @Override
  @Transactional(readOnly = true)
  public List<MemberEntryDataDTO> memberMonthEntryData() {
    log.info("Is Running Member Month Entry Data ServiceImpl");
    return statsMapper.memberMonthEntryData();
  }

  // Member Day Entry Data ServiceImpl
  @Override
  @Transactional(readOnly = true)
  public List<MemberEntryDataDTO> memberDayEntryData() {
    log.info("Is Running Member Day Entry Data ServiceImpl");
    return statsMapper.memberDayEntryData();
  }

  // Store Day Entry Data ServiceImpl
  @Override
  @Transactional(readOnly = true)
  public List<StoreEntryDataDTO> storeDayEntryData() {
    log.info("Is Running Store Day Entry Data ServiceImpl");
    return statsMapper.storeDayEntryData();
  }
}

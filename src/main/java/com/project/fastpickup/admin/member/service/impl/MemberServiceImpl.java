package com.project.fastpickup.admin.member.service.impl;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.mappers.MemberMapper;
import com.project.fastpickup.admin.member.service.MemberService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

// Member ServiceImpl Class
@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

    // 의존성 주입
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    // Autowired 명시적 표시
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        log.info("Constructor Called, Mapper Injected.");
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Join Member ServiceImpl
    @Override
    @Transactional
    public int joinMember(MemberConvertDTO memberConvertDTO) {
        log.info("Is Running JoinMember ServiceImpl");
        String rolename = "USER";
        passwordEncoder.encode(memberConvertDTO.getMemberPw());
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), rolename);
        return memberMapper.joinMember(memberConvertDTO);
    }

    // Join Store Meber ServiceImpl
    @Override
    @Transactional
    public int joinStoreMember(MemberConvertDTO memberConvertDTO) {
        log.info("Is Running JoinStoreMember ServiceImpl");
        String rolename = "USER";
        passwordEncoder.encode(memberConvertDTO.getMemberPw());
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), rolename);
        return memberMapper.joinStoreMember(memberConvertDTO);
    }

    // Delete Member ServiceImpl
    @Override
    @Transactional
    public int deleteMember(String email) {
        log.info("Is Running DeleteMember ServiceImpl");
        memberMapper.deleteMemberRole(email);
        return memberMapper.deleteMember(email);
    }

    // Update Member ServiceImpl
    @Override
    @Transactional
    public int updateMember(MemberConvertDTO memberConvertDTO) {
        log.info("Is Running UpdateMember ServiceImpl");
        return memberMapper.updateMember(memberConvertDTO);
    }

    // Read Meber ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO readMember(String email) {
        log.info("Is Running ReadMember ServiceImpl");
        return memberMapper.readMember(email);
    }

    // List Meber ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<MemberConvertDTO> listMember(PageRequestDTO pageRequestDTO) {
        log.info("Is Running ListMember ServiceImpl");
        List<MemberConvertDTO> list = memberMapper.listMember(pageRequestDTO);
        int total = memberMapper.total(pageRequestDTO);
        return PageResponseDTO.<MemberConvertDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}

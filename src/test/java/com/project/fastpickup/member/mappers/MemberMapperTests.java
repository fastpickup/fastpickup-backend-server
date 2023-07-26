package com.project.fastpickup.member.mappers;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.mappers.MemberMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

// Member Mapper Test Class
@Log4j2
@SpringBootTest
public class MemberMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_EMAIL_VERSION_2 = "twefiwefew@naver.com";
    private static final String TEST_EMAIL_VERSION_3 = "wfewfewfew@naver.com";
    private static final String TEST_EMAIL_VERSION_4 = "wfewfiewfewe@naver.com";
    private static final String TEST_MEMBER_PW = "1111";
    private static final String TEST_MEMBER_NAME = "권성준";
    private static final String TEST_MEMBER_PHONE = "010-3099-0648";
    private static final String TEST_STORE = "s";

    // DTO 정의
    private MemberConvertDTO memberConvertDTO;
    private MemberConvertDTO updateMemberConvertDTO;
    private MemberConvertDTO joinStoreMemberDTO;
    private MemberConvertDTO updateStoreMeberDTO;

    // BeforeEach 사용을 위한 setUp
    @BeforeEach
    public void setUp() {
        // 회원가입
        memberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL_VERSION_2)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .build();

        // 회원 업데이트
        updateMemberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .build();

        // 가맹점 가입
        joinStoreMemberDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL_VERSION_4)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .store(TEST_STORE)
                .build();

        // 가맹점 업데이트 
        updateStoreMeberDTO = MemberConvertDTO.builder()
        .email(TEST_EMAIL_VERSION_3)
        .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
        .memberName(TEST_MEMBER_NAME)
        .memberPhone(TEST_MEMBER_PHONE)
        .store(TEST_STORE)
        .build();
        
    }

    // join Member Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 회원가입 테스트")
    public void joinStoreMeberTestMapper() {
        // GIVEN
        log.info("=== Start Join Store Test Mapper ===");
        // WHEN
        String rolename = "USER";
        memberMapper.joinStoreMember(joinStoreMemberDTO);
        memberMapper.createJoinMemberRole(joinStoreMemberDTO.getEmail(), rolename);
        // THEN 
        Assertions.assertEquals(joinStoreMemberDTO.getEmail(), "wfewfiewfewe@naver.com");
        log.info("=== End Join Store Test Mapper ===");
    }
    
    // update Member Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 업데이트 테스트")
    public void updateStoreMemberTestMapper() {
        // GIVEN
        log.info("=== Start Update Store Test Mapper ===");
        // WHEN 
        memberMapper.updateMember(updateStoreMeberDTO);
        // THEN 
        MemberConvertDTO storeReadMember = memberMapper.readMember(TEST_EMAIL);
        Assertions.assertNotNull(storeReadMember, "storeReadMeber Should Be Not Null");
        Assertions.assertEquals(updateStoreMeberDTO.getEmail(), "wfewfewfew@naver.com");
        log.info("=== End Update Store Test Mapper ===");
    }

    // Join Member Mapper Test
    @Test
    @Transactional
    @DisplayName("멤버 회원가입 테스트")
    public void joinMemberTestMapper() {
        // GIVEN
        log.info("=== Start Join Member Test Mapper ===");
        // WHEN
        String rolename = "USER";
        memberMapper.joinMember(memberConvertDTO);
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), rolename);
        // THEN
        Assertions.assertEquals(memberConvertDTO.getEmail(), "twefiwefew@naver.com");
        log.info("=== End Join Member Test Mapper ===");
    }

    // Read Member Mapper Test
    @Test
    @Transactional
    @DisplayName("멤버 상세조회 테스트")
    public void readMemberTestMapper() {
        // GIVEN
        log.info("=== Start Read Member Test Mapper ===");
        // WHEN
        MemberConvertDTO readMember = memberMapper.readMember(TEST_EMAIL);
        // THEN
        Assertions.assertNotNull(readMember, "readMember Should Be Not Null");
    }

    // Delete Member Mapper Test
    @Test
    @Transactional
    @DisplayName("멤버 회원탈퇴 테스트")
    public void deleteMemberTestMapper() {
        // GIVEN
        log.info("=== Start Delete Member Test Mapper ===");
        // WHEN
        memberMapper.deleteMember(TEST_EMAIL);
        memberMapper.deleteMemberRole(TEST_EMAIL);
        // THEN
        MemberConvertDTO deletedMember = memberMapper.readMember(TEST_EMAIL);
        Assertions.assertNull(deletedMember, "deletedMember Should Be Null");
        log.info("=== End Delete Member Test Mapper ===");
    }

    // Update Member Mapper Test
    @Test
    @Transactional
    @DisplayName("멤버 업데이트 테스트")
    public void updateMemberTestMapper() {
        // GIVEN
        log.info("=== Start Update Member Test Mapper ===");
        // WHEN
        memberMapper.updateMember(updateMemberConvertDTO);
        // THEN
        MemberConvertDTO updatedMember = memberMapper.readMember(TEST_EMAIL);
        Assertions.assertNotNull(updatedMember, "updatedMember Should Be Not Null");
        Assertions.assertEquals(updateMemberConvertDTO.getEmail(), "thistrik@naver.com");
        Assertions.assertEquals(updateMemberConvertDTO.getMemberName(), "권성준");
        log.info("=== End Update Member Test Mapper ===");
    }

    // List Member Mapper Test 
    @Test
    @Transactional
    @DisplayName("멤버 리스트 테스트")
    public void listMemberTestMapper() {
        // GIVEN 
        log.info("=== Start List Member Test Mapper ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<MemberConvertDTO> listMember = memberMapper.listMember(pageRequestDTO);
        log.info(listMember);
        Assertions.assertNotNull(listMember, "listMember Should Be Not Null");
        log.info("=== End List Member Test Mapper ===");
    }

    // Total Member Mapper Test
    @Test
    @Transactional
    @DisplayName("토탈 테스트")
    public void totalMemberTestMapper() {
        // GIVEN 
        log.info("=== Start Total Member Test Mapper ===");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        int totalCount = memberMapper.total(pageRequestDTO);
        // THEN 
        log.info(totalCount);
        Assertions.assertNotNull(totalCount, "totalCount Should Be Not Null");
        log.info("=== End Total Member Test Mapper ===");
    }
}

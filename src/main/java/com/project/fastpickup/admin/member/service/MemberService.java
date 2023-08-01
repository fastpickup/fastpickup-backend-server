package com.project.fastpickup.admin.member.service;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */


import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

// Member Service Interface
public interface MemberService {
    
    // Create Member 
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Create Store Member
    int joinStoreMember(MemberConvertDTO memberConvertDTO);

    // Delete Member 
    int deleteMember(String email);

    // Update Member 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Read Member
    MemberConvertDTO readMember(String email);

    // List Member
    PageResponseDTO<MemberConvertDTO> listMember(PageRequestDTO pageRequestDTO);

    // checkEmailAlreadyExists
    void checkEmailAlreadyExists(String email);

    // searchUser
    void searchUser(String email);
}

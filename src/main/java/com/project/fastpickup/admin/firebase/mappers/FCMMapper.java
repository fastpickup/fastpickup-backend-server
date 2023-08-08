package com.project.fastpickup.admin.firebase.mappers;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import org.apache.ibatis.annotations.Mapper;

import com.project.fastpickup.admin.firebase.dto.FCMDTO;

// Fcm Mapper Interface 
@Mapper
public interface FCMMapper {
    
    // Read Fcm Info 
    FCMDTO readFcmInfo(String email);

    // Create Fcm Token
    int createFcmToken(FCMDTO fcmDTO);
}

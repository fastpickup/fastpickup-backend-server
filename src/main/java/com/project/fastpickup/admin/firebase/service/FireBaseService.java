package com.project.fastpickup.admin.firebase.service;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import com.project.fastpickup.admin.firebase.dto.FCMDTO;
import com.project.fastpickup.admin.firebase.dto.FCMNotificationRequestDTO;

// FireBase Interface
public interface FireBaseService {
    
    // Create Fcm Token
    int createFcmToken(FCMDTO fcmDTO);

    // Sending Message By Token
    String sendingMessageByToken(FCMNotificationRequestDTO fcmNotificationRequestDTO);
}

package com.project.fastpickup.admin.firebase.service.impl;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.project.fastpickup.admin.firebase.dto.FCMDTO;
import com.project.fastpickup.admin.firebase.dto.FCMNotificationRequestDTO;
import com.project.fastpickup.admin.firebase.mappers.FCMMapper;
import com.project.fastpickup.admin.firebase.service.FireBaseService;

import lombok.extern.log4j.Log4j2;

// FireBaseServiceImpl Class
@Log4j2
@Service
public class FireBaseServiceImpl implements FireBaseService {

   // 의존성 주입
   private final FCMMapper fcmMapper;
   private final FirebaseMessaging firebaseMessaging;

   // Autowired 명시적 표시
   @Autowired
   public FireBaseServiceImpl(FCMMapper fcmMapper, FirebaseMessaging firebaseMessaging) {
      log.info("Constructor Called, Mapper Injected.");
      this.fcmMapper = fcmMapper;
      this.firebaseMessaging = firebaseMessaging;
   }

   // Create Fcm Token
   @Override
   @Transactional
   public int createFcmToken(FCMDTO fcmdto) {
      log.info("Is Running Crate Fcm Token ServiceImpl");
      return fcmMapper.createFcmToken(fcmdto);
   }

   // Sending Message By Token
   @Override
   @Transactional
   public String sendingMessageByToken(FCMNotificationRequestDTO fcmNotificationRequestDTO) {
      log.info("Is Running Sending Message By Token ServiceImpl");
      FCMDTO fcmdto = fcmMapper.readFcmInfo(fcmNotificationRequestDTO.getEmail());
      log.info("fcmdto: ",fcmdto);
      if (fcmdto != null) {
         if (fcmdto.getFcmToken() != null) {
            Notification notification = Notification.builder()
                  .setTitle(fcmNotificationRequestDTO.getTitle())
                  .setBody(fcmNotificationRequestDTO.getBody())
                  .build();
            Message message = Message.builder()
                  .setToken(fcmdto.getFcmToken())
                  .setNotification(notification)
                  .build();
            try {
               firebaseMessaging.send(message);
               return "알림을 성공적으로 전송하였습니다.";

            } catch (FirebaseMessagingException e) {
               e.printStackTrace();
               return "실패";
            }
         } else {
            return "서버에 저장된 FCMToken이 없습니다.";
         }
      } else {
         return "해당 유저가 존재하지않습니다.";
      }
   }
}

package com.project.fastpickup.admin.firebase.controller;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.firebase.dto.FCMDTO;
import com.project.fastpickup.admin.firebase.dto.FCMNotificationRequestDTO;
import com.project.fastpickup.admin.firebase.service.FireBaseService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class FCMController {

    // 의존성 주입 
    private final FireBaseService fireBaseService;

    // Autowired 명시적 표시 
    @Autowired
    public FCMController(FireBaseService firebaseService) {
        log.info("Constructor Called, Like Service Injected.");
        this.fireBaseService = firebaseService;
    }

    // POST : Create Fcm Token 
    @PostMapping("fcm/token")
    public ResponseEntity<Integer> createFcmToken(@RequestBody FCMDTO orderDTO) {
        log.info("RestController | Admin Create Fcm Token");
        int result = fireBaseService.createFcmToken(orderDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST : Create Sending Message 
    @PostMapping("/v1/notification")
    public String sendNotification(@RequestBody FCMNotificationRequestDTO dto) {
        log.info("RestController | Admin Crate Message");
        return fireBaseService.sendingMessageByToken(dto);
    }
}

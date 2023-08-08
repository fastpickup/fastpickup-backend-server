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

    private final FireBaseService fireBaseService;

    @Autowired
    public FCMController(FireBaseService firebaseService) {
        this.fireBaseService = firebaseService;
    }

    @PostMapping("fcm/token")
    public ResponseEntity<Integer> createFcmToken(@RequestBody FCMDTO orderDTO) {
        log.info("RestController | Admin Create Fcm Token");
        log.info(orderDTO+"ORDERDTO");
        log.info("토큰: " + orderDTO.getFcmToken());
        log.info("토큰");
        int result = fireBaseService.createFcmToken(orderDTO);
        log.info(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("v1/notification")
    public ResponseEntity<String> sendingMessageToClient(
            @RequestBody FCMNotificationRequestDTO fcmNotificationRequestDTO) {
        log.info("RestController | Admin Sending Message Token");
        String result = fireBaseService.sendingMessageByToken(fcmNotificationRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

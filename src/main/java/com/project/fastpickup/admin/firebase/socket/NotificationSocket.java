package com.project.fastpickup.admin.firebase.socket;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@CrossOrigin
@ServerEndpoint("/notifications")
public class NotificationSocket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection opened!");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for (Session sess : session.getOpenSessions()) {
            sess.getBasicRemote().sendText(message);
        }
    }
}
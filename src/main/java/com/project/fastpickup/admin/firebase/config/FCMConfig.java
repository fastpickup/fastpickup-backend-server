package com.project.fastpickup.admin.firebase.config;

/*
 * Date   : 2023.08.08
 * Author : 권성준
 * Author : 이주용 
 * E-mail : thistrik@naver.com
 * E-mail : wndyd0110@gmail.com
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FCMConfig {

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {

        ClassPathResource resource = new ClassPathResource(
                "firebase/fastpasstest-c42c7-firebase-adminsdk-ckwex-5e007d2c8a.json");

        // Firebase Admin SDK(FCM 서버에 인증하려면 필요 함)json 파일을 읽는 작업
        InputStream refreshToken = resource.getInputStream();

        // Firebase 초기화 작업
        FirebaseApp firebaseApp = null;

        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

        if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
            for (FirebaseApp app : firebaseAppList) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    firebaseApp = app;
                }
            }
        } else {
            // Firebase Admin SDK로 Google Oauth2 토큰 인증
            // => Firebase에 인증이 성공하여 서비스를 이용 할 수 있게 된다.
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        }

        return FirebaseMessaging.getInstance(firebaseApp);

    }

}

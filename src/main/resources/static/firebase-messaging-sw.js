// firebase-messaging-sw.js
importScripts(
  "https://www.gstatic.com/firebasejs/9.0.0/firebase-app-compat.js"
);
importScripts(
  "https://www.gstatic.com/firebasejs/9.0.0/firebase-messaging-compat.js"
);

// the Firebase config object
const firebaseConfig = {
  apiKey: "AIzaSyBNrqJO66WP-DXciF1QODDspWihbDV-XMU",
  authDomain: "fastpasstest-c42c7.firebaseapp.com",
  projectId: "fastpasstest-c42c7",
  storageBucket: "fastpasstest-c42c7.appspot.com",
  messagingSenderId: "842980145908",
  appId: "1:842980145908:web:e764504e57b9dfaca6bca1",
  measurementId: "G-QSHDKEHL13",
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();

messaging.onBackgroundMessage(function (payload) {
  console.log("Received background message ", payload);
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});

messaging.onMessage((payload) => {
    console.log("Received message ", payload);
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
      body: payload.notification.body,
    };
  
    // 웹 앱의 포그라운드 또는 백그라운드에서 알림을 표시
    self.registration.showNotification(notificationTitle, notificationOptions);
  });


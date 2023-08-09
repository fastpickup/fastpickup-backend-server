import { initializeApp } from "https://www.gstatic.com/firebasejs/9.0.2/firebase-app.js";
import { getMessaging, getToken, onMessage  } from "https://www.gstatic.com/firebasejs/9.0.2/firebase-messaging.js";

const firebaseConfig = {
  apiKey: "AIzaSyBNrqJO66WP-DXciF1QODDspWihbDV-XMU",
  authDomain: "fastpasstest-c42c7.firebaseapp.com",
  projectId: "fastpasstest-c42c7",
  storageBucket: "fastpasstest-c42c7.appspot.com",
  messagingSenderId: "842980145908",
  appId: "1:842980145908:web:e764504e57b9dfaca6bca1",
  measurementId: "G-QSHDKEHL13",
};

const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

getToken(messaging, {
    vapidKey: `BM5dOQVKVrBlXo4fzzTzbAoY_2KbPLNl0Q2txRKBBVa69k5d0iP0Wxgip-1z9gNSqkI86VXcCQT7lMU9nHBqFDg`,
  })
    .then((currentToken) => {
      if (currentToken) {
        console.log("Client Token: ", currentToken);
        setCookie("Token", currentToken, 1);
      } else {
        console.log("Failed to generate the app registration token.");
      }
    })
    .catch((err) => {
      console.log("An error occurred when requesting to receive the token.", err);
    });

const onMessageListener = () =>
  new Promise((resolve) => {
    onMessage((payload) => {
      resolve(payload);
    });
  });

importScripts('https://www.gstatic.com/firebasejs/5.5.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.5.2/firebase-messaging.js');

var config = {
    apiKey: "AIzaSyA55pkgysAfPFIETtD-eMPn0ORPN4LVmec",
    authDomain: "causk-16a1b.firebaseapp.com",
    databaseURL: "https://causk-16a1b.firebaseio.com",
    projectId: "causk-16a1b",
    storageBucket: "causk-16a1b.appspot.com",
    messagingSenderId: "843739767329"
  };
  firebase.initializeApp(config);

const messaging = firebase.messaging();

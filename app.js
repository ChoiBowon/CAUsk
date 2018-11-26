
// Initialize Firebase
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
messaging.requestPermission()
.then(function(){
  console.log('Have Permission');
  return messaging.getToken();
})
.then(function(token){
  console.log(token);
})
.catch(function(err){
  console.log('Error Occured', err);
})

messaging.onMessage(function(payload){
console.log('on Message: ', payload);
});

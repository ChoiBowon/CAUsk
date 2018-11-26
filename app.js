var {google} = require('googleapis');
var MESSAGING_SCOPE = 'https://www.googleapis.com/auth/firebase.messaging';
var SCOPES = [MESSAGING_SCOPE];
function getAccessToken() {
    return new Promise(function(resolve, reject) {
        var key = require('./service-account.json');
        var jwtClient = new google.auth.JWT(
            key.client_email,
            null,
            key.private_key,
            SCOPES,
            null
        );
        jwtClient.authorize(function(err, tokens) {
            if (err) {
                reject(err);
                return;
            }
            resolve(tokens.access_token);
        });
   });
}
var PROJECT_ID = 'causk-16a1b';
var HOST = 'fcm.googleapis.com';
var PATH = '/v1/projects/' + PROJECT_ID + '/messages:send';
function sendFcmMessage(fcmMessage) {
    getAccessToken().then(function(accessToken) {
        var options = {
            hostname: HOST,
            path: PATH,
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + accessToken
            }
            // … plus the body of your notification or data message
        };
        var request = https.request(options, function(resp) {
            resp.setEncoding('utf8');
            resp.on('data', function(data) {
                console.log('Message sent to Firebase for delivery, response:');
                console.log(data);
            });
        });
        request.on('error', function(err) {
            console.log('Unable to send message to Firebase');
            console.log(err);
        });
        request.write(JSON.stringify(fcmMessage));
        request.end();
    });
}
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

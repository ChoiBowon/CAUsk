package com.example.geehyes2.causk_new;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.location.Geocoder;
import android.location.Address;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import android.widget.TextView;
import android.Manifest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.speech.tts.TextToSpeech.ERROR;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;
import java.util.Locale;
import android.os.Handler;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.telephony.PhoneNumberUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.speech.tts.TextToSpeech.ERROR;


public class GPSSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Button searchGpsbtn;

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;


    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    double latitude;
    double longitude;

    // GPSTracker class
    private GpsInfo gps;

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_STORENAME = "storeName";
    private TextView mTextViewResult_gps;
    private TextView textView;

    private TextToSpeech tts;  // TTS 변수 선언
    SoundPool sp;
    SoundPool sound;    //sound
    int soundId;        //sound

    String store;
    String UserID;

    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList_gps;
    //EditText mEditTextSearchKeyword;
    String mJsonString;
    String storename;

    String userid;
    String PN;

    private static String TAG_cell = "휴대폰 정보 가져오기";

    int ClickCount = 0;

    String[] StoreArray = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpssearch);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

        mTextViewResult_gps = (TextView)findViewById(R.id.textView_main_result_gps);
        mListViewList_gps = (ListView) findViewById(R.id.listView_main_list_gps);




        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);

                    //tts.speak(("쥐피에스를 이용한 음식점 검색 화면입니다. " + "음식점 검색하기 버튼을 눌러서, 주위의 음식점을 확인하시고, 선택하세요").toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });



        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        } else {

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

            userid = telephonyManager.getLine1Number().replace("+82", "0");
            Log.d("전화번호",userid);
        }

        PN = PhoneNumberUtils.formatNumber(userid);
        System.out.println("유저아이디 : " + PN);

        if (ContextCompat.checkSelfPermission(this,     //권한요청
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }


        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);

        searchGpsbtn = (Button) findViewById(R.id.searchGpsbtn);

        searchGpsbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                sound.play(soundId,1f,1f,0,0,1f);


                if(isConnected()){
                    // 권한 요청을 해야 함
                    if (!isPermission) {
                        callPermission();
                        return;
                    }
                    System.out.println("gps 선언");
                    gps = new GpsInfo(GPSSearchActivity.this);

                    // GPS 사용유무 가져오기
                    if (gps.isGetLocation()) {
                        System.out.println("gps 사용유무");
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();

                        System.out.println("위도: " + latitude + "\n경도: " + longitude);


                        Handler handlerGps = new Handler();
                        handlerGps.postDelayed(new Runnable() {
                            public void run() { //딜레이 후에
                                System.out.println("핸들러,,");
                                mArrayList.clear();

                                GetData task = new GetData();
                                task.execute();

                            }
                        }, 500);  // 0.5초 딜레이

                        mArrayList = new ArrayList<>();


                        Geocoder gCoder = new Geocoder(GPSSearchActivity.this, Locale.getDefault());
                        System.out.println("Geocoder 시작");

                        List<Address> addr = null;
                        System.out.println("list address 선언");

                        try{
                            System.out.println("try segment start");

                            addr = gCoder.getFromLocation(latitude,longitude,1);
                            System.out.println("addr 선언");

                            //Address = gCoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                            Address a = addr.get(0);

                            for (int i=0;i <= a.getMaxAddressLineIndex();i++) {
                                //여기서 변환된 주소 확인할  수 있음
                                System.out.println("for seg");

                                Log.v("알림", "AddressLine(" + i + ")" + a.getAddressLine(i) + "\n");

                                System.out.println("AddressLine(" + i + ")" + a.getAddressLine(i));
                            }

                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        if (addr != null) {
                            if (addr.size()==0) {
                                Toast.makeText(GPSSearchActivity.this,"주소정보 없음", Toast.LENGTH_LONG).show();
                            }
                        }

                    } else {

                        // GPS 를 사용할수 없으므로
                        gps.showSettingsAlert();

                    }

                }
                else{
                    System.out.println("인터넷 미연결시");
                    tts.speak("인터넷을 연결해주세요!".toString(),TextToSpeech.QUEUE_FLUSH,null);
                }

            }



        });

        callPermission();  // 권한 요청을 해야 함

        mListViewList_gps.setOnItemClickListener(new AdapterView.OnItemClickListener() { //리스트뷰 클릭시
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sound.play(soundId,1f,1f,0,0,1f);

                Intent changeintent_gps = new Intent(GPSSearchActivity.this,SecondActivity.class); //다음 activity 생성안함

                String kk = mArrayList.get(position).get(TAG_STORENAME).toString();
                changeintent_gps.putExtra("store",kk.toString());
                changeintent_gps.putExtra("UserID",PN.toString()); //다음페이지로 intent값 전달


                startActivity(changeintent_gps);

            }
        });

    } //oncreate()



    public  boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        if (net!=null && net.isAvailable() && net.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    // 전화번호 권한 요청
    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }

    private class GetData extends AsyncTask<String, Void, String> {

        //ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onpreexecute ok");

            //progressDialog = ProgressDialog.show(GPSSearchActivity.this, null, null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onpostexecute ok");

            //progressDialog.dismiss();
            mTextViewResult_gps.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult_gps.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {


            System.out.println("parmeter start");

            //String searchKeyword = params[0];

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/nearbyStore5m.php";
            String postParameters = "latitude=" + latitude +"&" +"longitude=" + longitude;

            System.out.println("GetDate** \n위도: " + latitude + "\n경도: " + longitude);

            System.out.println("parmeter : "+postParameters);


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    } //GetData 괄호

    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            Log.e("hashmap", "hashmap");

            for(int i=0;i<jsonArray.length();i++){
                Log.e("hashmap", "hashmap");
                JSONObject item = jsonArray.getJSONObject(i);


                String storename = item.getString(TAG_STORENAME);

                StoreArray[i] = item.getString(TAG_STORENAME);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_STORENAME, storename);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    GPSSearchActivity.this, mArrayList, R.layout.item_list_gps,
                    new String[]{TAG_STORENAME},
                    new int[]{R.id.textView_list_gps}
            );

            mListViewList_gps.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    @Override
    protected void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }



        super.onDestroy();

    }


}

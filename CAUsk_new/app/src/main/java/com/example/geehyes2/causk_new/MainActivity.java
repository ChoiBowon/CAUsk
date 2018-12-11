package com.example.geehyes2.causk_new;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.telephony.PhoneNumberUtils;
import static android.speech.tts.TextToSpeech.ERROR;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Intent intent;
    Intent intent2;
    SpeechRecognizer mRecognizer;
    TextView textView;
    TextView textView2;
    Button STTbtn,Nextbtn;


    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_STORENAME = "storeName";

    ArrayList<HashMap<String, String>> mArrayList;
    private ListView mListViewList; //변경필
    private TextView mTextViewResult;
    //EditText mEditTextSearchKeyword;
    String mJsonString;
    String sn;

    String[] StoreArray = new String[20];


    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private TextToSpeech tts;  // TTS 변수 선언
    protected String mRecordingFile;
    SoundPool sound;    //sound
    SoundPool soundSt;
    SoundPool sp;
    int soundId;        //sound
    int soundStart;
    String store;
    String UserID;
    Toolbar toolbar;
    String userid;
    String PN;

    private static String TAG_cell = "휴대폰 정보 가져오기";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김


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

        mTextViewResult = (TextView)findViewById(R.id.textView_main_result_stt);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_stt);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  //음성인식 intent 생성
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName()); //데이터 설정
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR"); //한국어로 설정


        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this); //음성인식 객체
        mRecognizer.setRecognitionListener(recognitionListener); //음성인식 리스너 적용


        final SoundPool sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        // 최대 음악파일의 개수, 스트림 타입, 음질(기본값 0)
        int soundID = sp.load(this,R.raw.sound,0);
        final int streamID = sp.play(soundID, 1f, 1f, 1, 0, 1f);;

        /*int soundID2 = sp.load(this,R.raw.start,1);
        int soundID3 = sp.load(this,R.raw.menuorder,2);
        final int soundID4 = sp.load(this,R.raw.pa,1);
        final int soundID5 = sp.load(this,R.raw.sol,1);
        final int soundID6 = sp.load(this,R.raw.ra,1);
        final int soundID7 = sp.load(this,R.raw.si,1);
        final int soundID8 = sp.load(this,R.raw.highdoo,1);*/

        textView = (TextView) findViewById(R.id.textView);


        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);


                    //tts.speak(("음성검색을 통한 음식점 검색 화면입니다. 화면을 한번 터치하고 음식점 이름을 말해주세요.").toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        //textView.setText("파스쿠찌 중앙대점"); //테스트 시

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);

        StoreArray[0] = "NULL";


        final Button STTbtn = (Button)findViewById(R.id.STTbtn);
        STTbtn.setOnClickListener(new View.OnClickListener() { //GPS 버튼 누를시, gps 검색화면으로 변경
            @Override
            public void onClick(View view) {

                if(isConnected()){
                    mRecognizer.startListening(intent); //음식점 이름검색

                    Handler handlerStt = new Handler();
                    handlerStt.postDelayed(new Runnable() {
                        public void run() { //딜레이 후에
                            System.out.println("핸들러,,");

                            mArrayList.clear();

                            GetData task = new GetData();
                            task.execute();
                        }
                    }, 3000);  // 5초 딜레이

                    mArrayList = new ArrayList<>();

                }
                else{

                    tts.speak("인터넷을 연결해주세요!".toString(),TextToSpeech.QUEUE_FLUSH,null);
                }


            }
        });






        System.out.println("*** "+StoreArray[0]);

        final Button Nextbtn = (Button) findViewById(R.id.Nextbtn);
        Nextbtn.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                System.out.println("*** "+StoreArray[0]);
                if((StoreArray[0].equals("NULL"))){


                }

                else{
                    sound.play(soundId,1f,1f,0,0,1f);
                    Intent changeintent = new Intent(MainActivity.this, SecondActivity.class);

                    changeintent.putExtra("store",textView.getText().toString()); //다음페이지로 intent값 전달
                    changeintent.putExtra("UserID",PN.toString()); //다음페이지로 intent값 전달

                    startActivity(changeintent);
                }

            }
        });


    }//onCreate 제일 마지막 괄호


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

    private void Speech() {

        String text = textView.getText().toString().trim();
        tts.setPitch((float) 0.1);      // 음량
        tts.setSpeechRate((float) 1.0); // 재생속도
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }


    private RecognitionListener recognitionListener = new RecognitionListener() { //음성인식 리스너
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech () {

        }

        @Override
        public void onError(int i) {
            //textView.setText("시간 초과입니다. 버튼을 누르시고 다시 말해주세요.");
            tts.speak("시간 초과입니다. 버튼을 누르시고 다시 말해주세요.".toString(),TextToSpeech.QUEUE_FLUSH,null);

        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView.setText(rs[0]);
            sn = textView.getText().toString();

            System.out.println("**Main "+ StoreArray[0]);

            Handler handlerStt1 = new Handler();
            handlerStt1.postDelayed(new Runnable() {
                public void run() { //딜레이 후에
                    if(StoreArray[0].equals("NULL")){
                        System.out.println("** 검색 시 음식점 이름이 존재하지 않을 때");
                        tts.speak("검색하신 음식점이 없습니다. 재검색하시거나 쥐피에스 검색을 이용해주세요.".toString(),TextToSpeech.QUEUE_FLUSH,null);
                        //버튼 비활성화
                        System.out.println("** 버튼 비활성화");
                    }
                    else{
                        System.out.println("** 검색 시 음식점 이름이 존재할 때");
                        tts.speak("검색하실 음식점이 " + textView.getText() + (" 맞으십니까? "
                                + "맞으시면 다음 페이지로 이동 버튼을 눌러주세요.").toString(),TextToSpeech.QUEUE_FLUSH,null);
                        //버튼 누르기 구현
                        //Nextbtn.setEnabled(false);
                        System.out.println("** 버튼 활성화");

                    }


                }
            }, 2000);  // 5초 딜레이


        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    }; //RecognitionListener 괄호


    private class GetData extends AsyncTask<String, Void, String> {

        //ProgressDialog progressDialog;
        String errorString = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("** Main : onPreExecure 실행");

            //progressDialog = ProgressDialog.show(SecondActivity.this, " ", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //progressDialog.dismiss();
            mTextViewResult.setText(result);
            System.out.println("result 값 : " + result);

            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();

            }
        }


        @Override
        protected String doInBackground(String... params) {

            //String searchKeyword = params[0];
            //url 수정필
            System.out.println("**Main "+sn);
            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/storeCheck.php";
            String postParameters = "storeName=" + sn;

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

                String StoreName = item.getString(TAG_STORENAME);

                StoreArray[i] = item.getString(TAG_STORENAME);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_STORENAME, StoreName);

                mArrayList.add(hashMap);

            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, mArrayList, R.layout.item_list_stt,
                    new String[]{TAG_STORENAME},
                    new int[]{R.id.textView_list_stt}
            );

            mListViewList.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }





    @Override
    public void onStop() {

        super.onStop();


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

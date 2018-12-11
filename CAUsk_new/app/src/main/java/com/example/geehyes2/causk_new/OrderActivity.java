package com.example.geehyes2.causk_new;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import static android.speech.tts.TextToSpeech.ERROR;

public class OrderActivity extends AppCompatActivity {

    String store7;
    String Category7;
    String menu7;
    String size7;
    String HI7;
    String UserID7; //1이 ice 1이 hot
    String price7;
    String storetel7;

    Toolbar toolbar;
    SoundPool sound;    //sound
    int soundId;        //sound

    private TextToSpeech tts;  // TTS 변수 선언

    Button telbtn7;
    Button button_7th;

    TextView textView7_1;
    TextView textView7_2;
    TextView textView7_3;
    TextView textView7_4;
    TextView textView7_5;
    TextView textView7_6;
    TextView textView7_7;

    TextView mTextViewResult_7;

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_PRICE = "price";

    private TextView mTextViewResult_6;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

        mTextViewResult_7 = (TextView)findViewById(R.id.textView_main_result_7);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_7);

        final TextView textView7_1 = (TextView)findViewById(R.id.textView7_1);
        final TextView textView7_2 = (TextView)findViewById(R.id.textView7_2);
        final TextView textView7_3 = (TextView)findViewById(R.id.textView7_3);
        final TextView textView7_4 = (TextView)findViewById(R.id.textView7_4);
        final TextView textView7_5 = (TextView)findViewById(R.id.textView7_5);
        final TextView textView7_6 = (TextView)findViewById(R.id.textView7_6);
        final TextView textView7_7 = (TextView)findViewById(R.id.textView7_7);

        Intent intent6 = getIntent();

        UserID7 = intent6.getStringExtra("UserID6");
        textView7_1.setText(UserID7);

        store7 = intent6.getStringExtra("store6");
        textView7_2.setText(store7);

        Category7 = intent6.getStringExtra("category6");
        textView7_3.setText(Category7);

        menu7= intent6.getStringExtra("menu6");
        textView7_4.setText(menu7);

        size7 = intent6.getStringExtra("size6");
        textView7_5.setText(size7);

        HI7 = intent6.getStringExtra("HI6");
        textView7_6.setText(HI7);

        price7 = intent6.getStringExtra("price6");
        textView7_7.setText(price7);

        storetel7 = intent6.getStringExtra("storetel6");

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);

                    //tts.speak("주문이 완료되었습니다.".toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });


        Handler handler_7 = new Handler();
        handler_7.postDelayed(new Runnable() {
            public void run() { //딜레이 후에
                mArrayList.clear();

                GetData task = new GetData();
                task.execute();


            }
        }, 100);  // 0.5초 딜레이

        mArrayList = new ArrayList<>();

        Button telbtn7 = (Button)findViewById(R.id.telbtn7);
        telbtn7.setText(store7 + " 전화하기");

        telbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = storetel7;
                //인텐트 객체 생성
                //인텐트로 전화 걸기 옵션 선언
                //위에서 받은 전화번호 데이터 넣어주기
                Intent telintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ receiver));

                //실행
                startActivity(telintent);

            }
        });

        Button button_7th = (Button)findViewById(R.id.button_7th);
        button_7th.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                Intent changeintent_in = new Intent(OrderActivity.this,StoreSearchActivity.class); //다음 activity 생성안함
                startActivity(changeintent_in);


            }
        });

    }//oncreate()



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


    private class GetData extends AsyncTask<String, Void, String> {

        //ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onpreexecute ok");

            //progressDialog = ProgressDialog.show(OrderActivity.this," ", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onpostexecute ok");

            //progressDialog.dismiss();
            mTextViewResult_7.setText(result);

            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult_7.setText(errorString);
            }
            else {

                mJsonString = result;
            }
        }


        @Override
        protected String doInBackground(String... params) {


            System.out.println("parmeter start");

            //String searchKeyword = params[0];

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/orderTransmission.php";
            String postParameters = "userId=" + UserID7 +"&"+"storeName=" + store7 +"&"+"menuName=" + menu7 +"&"+"size=" + size7+"&"+"hotIce=" + HI7 + "&"+"price=" + price7;

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

    @Override
    protected void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

    }




}

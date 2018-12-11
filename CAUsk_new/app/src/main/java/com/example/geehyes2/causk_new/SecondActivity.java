package com.example.geehyes2.causk_new;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.SoundPool;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import android.os.Handler;
import android.net.Uri;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import static android.speech.tts.TextToSpeech.ERROR;
import android.view.MenuItem;
import android.widget.AdapterView;


public class SecondActivity extends AppCompatActivity {

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_Category = "category";
    private static final String TAG_Tel = "tel";

    private TextView mTextViewResult;
    private TextView textView2_1;


    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    //EditText mEditTextSearchKeyword;
    String mJsonString;
    String storename;
    String tel=null;

    int ClickCount = 0;

    String[] CategoryArray = new String[20];
    String[] TelArray = new String[20];


    SpeechRecognizer mRecognizer;
    private TextToSpeech tts;  // TTS 변수 선언
    SoundPool sp;
    SoundPool sound;    //sound
    int soundId;        //sound
    String store2;
    String category2;
    String UserID2;
    Intent intent;
    Toolbar toolbar;
    Button telbtn;
    String storetel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김



        final TextView textView2 = (TextView)findViewById(R.id.textView2);
        final TextView textView2_1 = (TextView)findViewById(R.id.textView2_1);


        Intent intent2 = getIntent();
        store2 = intent2.getStringExtra("store");
        textView2.setText(store2);
        UserID2 = intent2.getStringExtra("UserID");

        System.out.println("두번째 액티비티 : "+ UserID2);


        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mListViewList = (ListView) findViewById(R.id.listView_main_list);

        SoundPool sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        // 최대 음악파일의 개수, 스트림 타입, 음질(기본값 0)
        int soundID = sp.load(this,R.raw.orders,0);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);

                    //tts.speak("대분류를 선택할수 있는 화면입니다.".toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);

        final Button telbtn = (Button) findViewById(R.id.telbtn);

        Handler handler_2 = new Handler();
        handler_2.postDelayed(new Runnable() {
        public void run() { //딜레이 후에
            mArrayList.clear();

            GetData task = new GetData();
            task.execute(textView2.getText().toString());

            Handler handler_2_1 = new Handler();
            handler_2_1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    telbtn.setText(store2 + " 전화하기");
                    System.out.println(store2 + "에 전화하기");



                }
            },100);

        }
            }, 300);  // 0.5초 딜레이

        mArrayList = new ArrayList<>();




        //전화 연결
        telbtn.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = TelArray[0];
                //인텐트 객체 생성
                //인텐트로 전화 걸기 옵션 선언
                //위에서 받은 전화번호 데이터 넣어주기
                Intent telintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ receiver));

                //실행
                startActivity(telintent);



            }
        });

        mListViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //리스트뷰 클릭시
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sound.play(soundId,1f,1f,0,0,1f);

                Intent changeintent2 = new Intent(SecondActivity.this,ThirdActivity.class); //다음 activity 생성안함
                changeintent2.putExtra("store2",store2.toString()); //다음페이지로 intent값 전달
                changeintent2.putExtra("UserID2",UserID2.toString()); //다음페이지로 intent값 전달
                changeintent2.putExtra("storetel2",TelArray[0].toString());
                String kk = mArrayList.get(position).get(TAG_Category).toString();
                changeintent2.putExtra("category2",kk);

                startActivity(changeintent2);

            }
        });


    }//oncreate 괄호


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


    private class GetData extends AsyncTask<String, Void, String>{

        //ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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

            String searchKeyword = params[0];

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/categoryTelSearch.php";
            String postParameters = "storeName=" + searchKeyword;

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

                String Category = item.getString(TAG_Category);
                TelArray[i] = item.getString(TAG_Tel);

                System.out.println("tel array : " + i + "번" + TelArray[i]);

                CategoryArray[i] = item.getString(TAG_Category);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_Category, Category);

                mArrayList.add(hashMap);


            }

            ListAdapter adapter = new SimpleAdapter(
                    SecondActivity.this, mArrayList, R.layout.item_list_sec,
                    new String[]{TAG_Category},
                    new int[]{R.id.textView_list_id_2}
            );

            mListViewList.setAdapter(adapter);

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

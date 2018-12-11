package com.example.geehyes2.causk_new;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.util.Timer;
import java.util.TimerTask;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;


import static android.speech.tts.TextToSpeech.ERROR;

public class FifthActivity extends AppCompatActivity {

    private TextToSpeech tts;

    int ClickCount5 = 0;

    Toolbar toolbar;

    SoundPool sound;    //sound
    int soundId;        //sound


    String store5;
    String Category5;
    String menu5;
    String size5;
    String UserID5;
    String HI5; //1이 ice 0이 hot
    String storetel5;

    int arraylistnum;


    TextView textView5_1;
    TextView textView5_2;
    TextView textView5_3;
    TextView textView5_4;
    Button telbtn5;

    String[] HIArray = new String[20];

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_HI = "hotIce";

    private TextView mTextViewResult_5;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김




        mTextViewResult_5 = (TextView)findViewById(R.id.textView_main_result_5);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_5);

        final TextView textView5_1 = (TextView)findViewById(R.id.textView5_1);
        final TextView textView5_2 = (TextView)findViewById(R.id.textView5_2);
        final TextView textView5_3 = (TextView)findViewById(R.id.textView5_3);
        final TextView textView5_4 = (TextView)findViewById(R.id.textView5_4);

        Intent intent5 = getIntent();
        store5 = intent5.getStringExtra("store4");
        textView5_1.setText(store5);

        Category5 = intent5.getStringExtra("category4");
        textView5_2.setText(Category5);

        menu5 = intent5.getStringExtra("menu4");
        textView5_3.setText(menu5);

        size5 = intent5.getStringExtra("size4");
        textView5_4.setText(size5);

        UserID5 = intent5.getStringExtra("UserID4");
        storetel5 = intent5.getStringExtra("storetel4");

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    //tts.speak("hot과 ice를 선택하는 화면입니다.".toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        Button button_5th = (Button) findViewById(R.id.button_5th);

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);


        Handler handler_5 = new Handler();
        handler_5.postDelayed(new Runnable() {
            public void run() { //딜레이 후에
                mArrayList.clear();

                GetData task = new GetData();
                task.execute();
                //핸들러 시작
                Handler handler_5_1 = new Handler();
                handler_5_1.postDelayed(new Runnable() {
                    public void run() { //딜레이 후에
                        arraylistnum = mArrayList.size();
                        System.out.println("5th 액티 array list 의 원소의 개수 : " + arraylistnum);   //arraylist 에 있는 원소 갯수 구하기
                        System.out.println("5th activity : " + HIArray[0]);
                        if((arraylistnum == 1)){
                            Handler handler_5_1_1 = new Handler();
                            handler_5_1_1.postDelayed(new Runnable() {
                                public void run() { //딜레이 후에
                                    Intent changeintent5 = new Intent(FifthActivity.this,SixthActivity.class); //다음 activity 생성안함

                                    changeintent5.putExtra("store5",store5.toString()); //다음페이지로 intent값 전달
                                    changeintent5.putExtra("category5",Category5.toString());
                                    changeintent5.putExtra("menu5",menu5.toString());
                                    changeintent5.putExtra("size5",size5.toString());
                                    changeintent5.putExtra("UserID5",UserID5.toString());
                                    changeintent5.putExtra("storetel5",storetel5.toString());
                                    changeintent5.putExtra("HI5",HIArray[0].toString());

                                    startActivity(changeintent5);

                                }
                            }, 100);  // 0.1초 딜레이
                        }



                    }
                }, 100);  // 0.1초 딜레이

            }
        }, 100);  // 0.1초 딜레이

        mArrayList = new ArrayList<>();


        Button telbtn5 = (Button)findViewById(R.id.telbtn5);
        telbtn5.setText(store5 + " 전화하기");


        telbtn5.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = storetel5;
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

                Intent changeintent5 = new Intent(FifthActivity.this,SixthActivity.class); //다음 activity 생성안함

                String kk = mArrayList.get(position).get(TAG_HI).toString();

                changeintent5.putExtra("store5",store5.toString()); //다음페이지로 intent값 전달
                changeintent5.putExtra("category5",Category5.toString());
                changeintent5.putExtra("menu5",menu5.toString());
                changeintent5.putExtra("size5",size5.toString());
                changeintent5.putExtra("UserID5",UserID5.toString());
                changeintent5.putExtra("storetel5",storetel5.toString());
                changeintent5.putExtra("HI5",kk.toString());

                startActivity(changeintent5);


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

            //progressDialog = ProgressDialog.show(FifthActivity.this, " ", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onpostexecute ok");

            //progressDialog.dismiss();
            mTextViewResult_5.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult_5.setText(errorString);
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

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/hotIceSearch.php";
            String postParameters = "storeName=" + store5 +"&" +"category=" + Category5+ "&" +"menuName=" + menu5;

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

                String HI = item.getString(TAG_HI);
                System.out.println("5th 액티 : "+ HI);

                HIArray[i] = item.getString(TAG_HI);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(TAG_HI, HI);

                System.out.println("5th 액티 : "+ TAG_HI);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    FifthActivity.this, mArrayList, R.layout.item_list_5th,
                    new String[]{TAG_HI},
                    new int[]{R.id.textView_list_hi}
            );

            mListViewList.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }


    }


    void OrderCategory() {
        for (int i = 0; i < 40; i++) {
            try { Thread.sleep(100); } catch (InterruptedException e) {;}

            tts.setSpeechRate((float) 1);
            tts.speak( " 선택하신 번호가 "+(ClickCount5-1)+"번 "+ HIArray[ClickCount5-2] + (" 맞습니까? " +
                    "맞으시면 버튼을 길게 누르셔서 다음페이지로 이동해 주세요").toString(),TextToSpeech.QUEUE_FLUSH,null);

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

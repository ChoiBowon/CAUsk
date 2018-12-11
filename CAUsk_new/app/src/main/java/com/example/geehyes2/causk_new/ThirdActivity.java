package com.example.geehyes2.causk_new;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.Timer;
import java.util.TimerTask;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;



import static android.speech.tts.TextToSpeech.ERROR;

public class ThirdActivity extends AppCompatActivity {
    private TextToSpeech tts;

    int ClickCount3 = 0;


    String store3;
    String Category3;
    String menu3;
    String UserID3;
    String storetel3;

    SoundPool sound;    //sound
    int soundId;        //sound

    TextView textView4;
    TextView textView3;

    Button telbtn3;

    String[] MenuArray = new String[20];

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_MENUNAME = "menuName";



    private TextView mTextViewResult_3;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김



        mTextViewResult_3 = (TextView)findViewById(R.id.textView_main_result_3);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_3);
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final TextView textView4 = (TextView)findViewById(R.id.textView4);


        Intent intent2 = getIntent();
        store3 = intent2.getStringExtra("store2");
        textView3.setText(store3);

        Category3 = intent2.getStringExtra("category2");
        textView4.setText(Category3);

        UserID3 = intent2.getStringExtra("UserID2");
        storetel3 = intent2.getStringExtra("storetel2");

        System.out.println("** 스토어 전화번호 출력 ** " + storetel3);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    //tts.speak("메뉴를 선택 할 수 있는 화면입니다.".toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        Button button_thr = (Button) findViewById(R.id.button_thr);

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);

        Handler handler_3 = new Handler();
        handler_3.postDelayed(new Runnable() {
            public void run() { //딜레이 후에
                mArrayList.clear();

                GetData task = new GetData();
                task.execute();
            }
        }, 100);  // 0.5초 딜레이

        mArrayList = new ArrayList<>();


        Button telbtn3 = (Button)findViewById(R.id.telbtn3);
        telbtn3.setText(store3 + " 전화하기");

        telbtn3.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = storetel3;
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

                Intent changeintent3 = new Intent(ThirdActivity.this,FourthActivity.class); //다음 activity 생성안함

                String kk = mArrayList.get(position).get(TAG_MENUNAME).toString();
                changeintent3.putExtra("category3",Category3.toString());
                changeintent3.putExtra("store3",store3.toString()); //다음페이지로 intent값 전달
                changeintent3.putExtra("UserID3",UserID3.toString()); //다음페이지로 intent값 전달
                changeintent3.putExtra("storetel3",storetel3.toString()); //다음페이지로 intent값 전달
                changeintent3.putExtra("menu3",kk.toString());


                startActivity(changeintent3);

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
                System.out.println("onpreexecute ok");

                //progressDialog = ProgressDialog.show(ThirdActivity.this," ", null, true, true);
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                System.out.println("onpostexecute ok");

                //progressDialog.dismiss();
                mTextViewResult_3.setText(result);
                Log.d(TAG, "response - " + result);

                if (result == null){

                    mTextViewResult_3.setText(errorString);
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

                String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/menuSearch.php";
                String postParameters = "storeName=" + store3 +"&" +"category=" + Category3;

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


                    String menuname = item.getString(TAG_MENUNAME);

                    MenuArray[i] = item.getString(TAG_MENUNAME);

                    HashMap<String,String> hashMap = new HashMap<>();

                    hashMap.put(TAG_MENUNAME, menuname);


                    mArrayList.add(hashMap);
                }

                ListAdapter adapter = new SimpleAdapter(
                        ThirdActivity.this, mArrayList, R.layout.list_list_3rd,
                        new String[]{TAG_MENUNAME},
                        new int[]{R.id.textView_list_menuname}
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
            tts.speak( " 선택하신 번호가 "+(ClickCount3-1)+"번 "+ MenuArray[ClickCount3-2] + (" 맞습니까? " +
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

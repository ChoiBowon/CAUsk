package com.example.geehyes2.causk_new;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;
import android.os.Handler;
import android.content.Intent;
import android.view.View;
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
import android.support.v7.widget.Toolbar;



import static android.speech.tts.TextToSpeech.ERROR;

public class FourthActivity extends AppCompatActivity {
    private TextToSpeech tts;

    int ClickCount4 = 0;

    Toolbar toolbar;

    String store4;
    String Category4;
    String menu4;
    String size4;
    String UserID4;
    String storetel4;

    SoundPool sound;    //sound
    int soundId;        //sound

    int arraylistnum = 0;


    TextView textView4_1;
    TextView textView4_2;
    TextView textView4_3;
    String[] SizeArray = new String[20];

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_SIZE = "size";

    private TextView mTextViewResult_4;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김



        mTextViewResult_4 = (TextView)findViewById(R.id.textView_main_result_4);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_4);

        final TextView textView4_1 = (TextView)findViewById(R.id.textView4_1);
        final TextView textView4_2 = (TextView)findViewById(R.id.textView4_2);
        final TextView textView4_3 = (TextView)findViewById(R.id.textView4_3);

        Intent intent4 = getIntent();
        store4 = intent4.getStringExtra("store3");
        textView4_1.setText(store4);

        Category4 = intent4.getStringExtra("category3");
        textView4_2.setText(Category4);

        menu4 = intent4.getStringExtra("menu3");
        textView4_3.setText(menu4);

        UserID4 = intent4.getStringExtra("UserID3");

        storetel4 = intent4.getStringExtra("storetel3");

        System.out.println("4번째 액티 " + storetel4);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    //tts.speak("음료 사이즈를 선택하는 화면입니다.".toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });
        Button button_4th = (Button) findViewById(R.id.button_4th);

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);



        Handler handler_4 = new Handler();
        handler_4.postDelayed(new Runnable() {
            public void run() { //딜레이 후에
                mArrayList.clear();

                GetData task = new GetData();
                task.execute();

                //핸들러 시작
                Handler handler_4_1 = new Handler();
                handler_4_1.postDelayed(new Runnable() {
                    public void run() { //딜레이 후에
                        arraylistnum = mArrayList.size();
                        System.out.println("4th 액티 array list 의 원소의 개수 : " + arraylistnum);   //arraylist 에 있는 원소 갯수 구하기
                        if(arraylistnum == 1){
                            Handler handler_4_1_1 = new Handler();
                            handler_4_1_1.postDelayed(new Runnable() {
                                public void run() { //딜레이 후에
                                    Intent changeintent4 = new Intent(FourthActivity.this,FifthActivity.class); //다음 activity 생성안함

                                    changeintent4.putExtra("store4",store4.toString()); //다음페이지로 intent값 전달
                                    changeintent4.putExtra("category4",Category4.toString());
                                    changeintent4.putExtra("menu4",menu4.toString());
                                    changeintent4.putExtra("UserID4",UserID4.toString());
                                    changeintent4.putExtra("size4","ND".toString());
                                    changeintent4.putExtra("storetel4",storetel4.toString()); //다음페이지로 intent값 전달
                                    System.out.println("4th 액티 "+ size4);

                                    startActivity(changeintent4);

                                }
                            }, 100);  // 0.5초 딜레이
                        }


                    }
                }, 100);  // 0.5초 딜레이

            }
        }, 100);  // 0.5초 딜레이


        mArrayList = new ArrayList<>();





        Button telbtn4 = (Button)findViewById(R.id.telbtn4);
        telbtn4.setText(store4 + " 전화하기");

        telbtn4.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = storetel4;
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

                Intent changeintent4 = new Intent(FourthActivity.this,FifthActivity.class); //다음 activity 생성안함

                String kk = mArrayList.get(position).get(TAG_SIZE).toString();

                changeintent4.putExtra("store4",store4.toString()); //다음페이지로 intent값 전달
                changeintent4.putExtra("category4",Category4.toString());
                changeintent4.putExtra("menu4",menu4.toString());
                changeintent4.putExtra("UserID4",UserID4.toString());
                changeintent4.putExtra("size4",kk.toString());
                changeintent4.putExtra("storetel4",storetel4.toString()); //다음페이지로 intent값 전달
                startActivity(changeintent4);

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


    private class GetData extends AsyncTask<String, Void, String>{

        //ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onpreexecute ok");

            //progressDialog = ProgressDialog.show(FourthActivity.this, " ", null, true, true);
    }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onpostexecute ok");

            //progressDialog.dismiss();
            mTextViewResult_4.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult_4.setText(errorString);
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

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/sizeSearch.php";
            String postParameters = "storeName=" + store4 +"&" +"category=" + Category4+"&" +"menuName=" + menu4;

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


                String size = item.getString(TAG_SIZE);

                SizeArray[i] = item.getString(TAG_SIZE);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_SIZE, size);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    FourthActivity.this, mArrayList, R.layout.item_list_4th,
                    new String[]{TAG_SIZE},
                    new int[]{R.id.textView_list_size}
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
            tts.speak( " 선택하신 번호가 "+(ClickCount4-1)+"번 "+ SizeArray[ClickCount4-2] + (" 맞습니까? " +
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

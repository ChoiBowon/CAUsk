package com.example.geehyes2.causk_new;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
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

import static android.speech.tts.TextToSpeech.ERROR;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import android.widget.ArrayAdapter;
import android.text.TextUtils;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.protocol.HTTP;







public class SixthActivity extends AppCompatActivity {

    private TextToSpeech tts;

    int ClickCount6 = 0;

    String store6;
    String Category6;
    String menu6;
    String size6;
    String HI6;
    String UserID6; //1이 ice 1이 hot
    String Price;
    String price6;
    String storetel6;
    String sizeChange;

    String Order;
    String num;


    Toolbar toolbar;
    SoundPool sound;    //sound
    int soundId;        //sound

    Button button_6th;
    Button telbtn6;

    TextView textView6_1;
    TextView textView6_2;
    TextView textView6_3;
    TextView textView6_4;
    TextView textView6_5;
    TextView textView6_6;

    String[] PriceArray = new String[20];

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_PRICE = "price";

    private TextView mTextViewResult_6;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    String mJsonString;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> ChatAdapter;
    private ListView mListView;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("jihye");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);


        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김


        mTextViewResult_6 = (TextView)findViewById(R.id.textView_main_result_6);
        mListViewList = (ListView) findViewById(R.id.listView_main_list_6);

        final TextView textView6_1 = (TextView)findViewById(R.id.textView6_1);
        final TextView textView6_2 = (TextView)findViewById(R.id.textView6_2);
        final TextView textView6_3 = (TextView)findViewById(R.id.textView6_3);
        final TextView textView6_4 = (TextView)findViewById(R.id.textView6_4);
        final TextView textView6_5 = (TextView)findViewById(R.id.textView6_5);


        Intent intent5 = getIntent();
        store6 = intent5.getStringExtra("store5");
        textView6_1.setText(store6);

        Category6 = intent5.getStringExtra("category5");
        textView6_2.setText(Category6);

        menu6 = intent5.getStringExtra("menu5");
        textView6_3.setText(menu6);

        size6 = intent5.getStringExtra("size5");
        textView6_4.setText(size6);

        System.out.println("if statement start");

        if(size6.equals("ND")){
            sizeChange = "one size";
            System.out.println("if statement");

        }
        else{
            sizeChange = size6;
            System.out.println("if statement else");
        }



        System.out.println("if statement stop");


        HI6 = intent5.getStringExtra("HI5");
        textView6_5.setText(HI6);

        UserID6 = intent5.getStringExtra("UserID5");
        storetel6 = intent5.getStringExtra("storetel5");

        System.out.println("6th activity");
        System.out.println(HI6);
        System.out.println(store6);
        System.out.println(Category6);
        System.out.println(menu6);
        System.out.println(size6);
        System.out.println(sizeChange);
        System.out.println(UserID6);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    //tts.speak(("선택하신 주문을 확인하는 화면입니다.").toString(),TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        final Button button_6th = (Button) findViewById(R.id.button_6th);



        System.out.println(Order);

        sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundId = sound.load(this,R.raw.sound1,1);
        Button telbtn6 = (Button)findViewById(R.id.telbtn6);

        Handler handler_6 = new Handler();
        handler_6.postDelayed(new Runnable() {
            public void run() { //딜레이 후에
                mArrayList.clear();

                GetData task = new GetData();
                task.execute();

                Handler handler_6_1 = new Handler();
                handler_6_1.postDelayed(new Runnable() {
                    public void run() { //딜레이 후에

                        button_6th.setText("선택한 메뉴 : " +  HI6+" "+ menu6+" "+
                                " \n 사이즈 : "+ sizeChange + "\n 가격은 " + PriceArray[0] +" 원 입니다." +
                                " 맞으시면 이 버튼을 눌러주세요");

                        Order = "Store : " + store6 + "\nUserID : " + UserID6 +"\n선택한 메뉴 : " +  HI6+" "+ menu6+" " + Category6 +
                                " \n 사이즈 : "+ sizeChange + "\n 가격은 " + PriceArray[0] +" 원";




                    }
                }, 100);  // 0.5초 딜레이

            }
        }, 100);  // 0.5초 딜레이

        mArrayList = new ArrayList<>();
        //4500원입니다. 주문하시겠습니까?

        Handler handler_6_2 = new Handler();
        handler_6_2.postDelayed(new Runnable() {
            public void run() { //딜레이 후에

                num = UserID6.substring(9);

                System.out.println("** 6th "+ num);

                initViews();
                initFirebaseDatabase();

            }
        }, 1000);  // 0.5초 딜레이





        telbtn6.setText(store6 + " 전화하기");


        telbtn6.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {
                //텍스트 객체의 데이터 값을 가져온다.
                String receiver = storetel6;
                //인텐트 객체 생성
                //인텐트로 전화 걸기 옵션 선언
                //위에서 받은 전화번호 데이터 넣어주기
                Intent telintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ receiver));

                //실행
                startActivity(telintent);

            }
        });


        button_6th.setOnClickListener(new View.OnClickListener() { //이미지 버튼을 누르면
            @Override
            public void onClick(View view) {
                //다음페이지이동
                Intent changeintent_od = new Intent(SixthActivity.this,OrderActivity.class); //다음 activity 생성안함

                changeintent_od.putExtra("store6",store6.toString()); //다음페이지로 intent값 전달
                changeintent_od.putExtra("category6",Category6.toString());
                changeintent_od.putExtra("menu6",menu6.toString());
                changeintent_od.putExtra("size6",size6.toString());
                changeintent_od.putExtra("UserID6",UserID6.toString());
                changeintent_od.putExtra("HI6",HI6.toString());
                changeintent_od.putExtra("price6",PriceArray[0].toString());
                changeintent_od.putExtra("storetel6",storetel6.toString());


                startActivity(changeintent_od);

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

            //progressDialog = ProgressDialog.show(SixthActivity.this, " ", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("onpostexecute ok");

            //progressDialog.dismiss();
            mTextViewResult_6.setText(result);

            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult_6.setText(errorString);
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

            String serverURL = "http://ec2-13-125-242-247.ap-northeast-2.compute.amazonaws.com/CAUsk/phpfolder/priceSearch.php";
            String postParameters = "storeName=" + store6 +"&" +"category=" + Category6 +"&"+"menuName=" + menu6 +"&"+"size=" + size6+"&"+"hotIce=" + HI6;

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

                String price = item.getString(TAG_PRICE);
                PriceArray[i] = item.getString(TAG_PRICE);
                Price = TAG_PRICE;

                System.out.println("가격 :" + price);

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(TAG_PRICE, price);
                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SixthActivity.this, mArrayList, R.layout.item_list_6th,
                    new String[]{TAG_PRICE},
                    new int[]{R.id.textView_list_price}
            );
            mListViewList.setAdapter(adapter);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }




    private void initViews() {
        mListView = (ListView) findViewById(R.id.list_message);
        mAdapter = new ArrayAdapter<String>(this, R.layout.listitem_chat,R.id.textView_chat);

        System.out.println("6th mAdapter 선언");

        mListView.setAdapter(mAdapter);




        findViewById(R.id.button_6th).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = Order;
                System.out.println("6th send 클릭");


//firebase 로 주문정보 String 전송
//                if (!TextUtils.isEmpty(message)) {
//
//                    mDatabaseReference.push().setValue(message);
//                }
                getResponse("https://causk.com/CAUsk/send.php");



                Intent changeintent_od = new Intent(SixthActivity.this,OrderActivity.class); //다음 activity 생성안함

                changeintent_od.putExtra("store6",store6.toString()); //다음페이지로 intent값 전달
                changeintent_od.putExtra("category6",Category6.toString());
                changeintent_od.putExtra("menu6",menu6.toString());
                changeintent_od.putExtra("size6",size6.toString());
                changeintent_od.putExtra("UserID6",UserID6.toString());
                changeintent_od.putExtra("HI6",HI6.toString());
                changeintent_od.putExtra("price6",PriceArray[0].toString());
                changeintent_od.putExtra("storetel6",storetel6.toString());


                startActivity(changeintent_od);


            }
        });

    }

    public HttpResponse getResponse(String url) {
        try {
            AsyncTask<String, Void, HttpResponse> asyncTask = new AsyncTask<String, Void, HttpResponse>() {
                @Override
                protected HttpResponse doInBackground(String... url) {
//                    HttpGet request = new HttpGet(url[0]);

                    HttpPost request = new HttpPost(url[0]);
//                    HttpClient httpClient = new DefaultHttpClient();



                    HttpResponse response = null;
                    try {
                        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                        postParameters.add(new BasicNameValuePair("one", "CAUsk 주문알림 입니다."));
                        postParameters.add(new BasicNameValuePair("two",  num + " 고객님 주문이 들어왔습니다."));
                        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters, HTTP.UTF_8);

                        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

                        request.setEntity(formEntity);
                        response = new DefaultHttpClient().execute(request);
                        Log.i("Insert Log", "response.getStatusCode:" + response.getStatusLine().getStatusCode());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return response;
                }
            };

            System.out.println("* : 전송 전");
            HttpResponse response = asyncTask.execute(url).get();

            System.out.println("* : 전송 후");
            return response;

        }
        catch (Exception e) {
            // No error

            System.out.println("* : Catch");
        }

        System.out.println("* : return null");
        return null;

    }




    private void initFirebaseDatabase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println("6th 메세지보냄");



        mDatabaseReference = mFirebaseDatabase.getReference("message");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                mAdapter.add(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                mAdapter.remove(message);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };

        mDatabaseReference.addChildEventListener(mChildEventListener);
    }







    @Override
    protected void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

        mDatabaseReference.removeEventListener(mChildEventListener);


    }


}


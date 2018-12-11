package com.example.geehyes2.causk_new;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import static android.speech.tts.TextToSpeech.ERROR;
import android.speech.tts.TextToSpeech;

public class StoreSearchActivity extends AppCompatActivity {


    Button GPSbutton;
    Button TTSbutton;
    Toolbar toolbar;

    private TextToSpeech tts;  // TTS 변수 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);


                    //tts.speak(("카우스크 메뉴 알리미 입니다. " + "쥐피에스를 통한 음식점 검색을 원하시면 위쪽 버튼을,,,, 음성인식을 통한 음식점 검색을 원하시면 아래 버튼을 눌러주세요").toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });





        final Button GPSbutton = (Button) findViewById(R.id.GPSbutton);
        GPSbutton.setOnClickListener(new View.OnClickListener() { //GPS 버튼 누를시, gps 검색화면으로 변경
            @Override
            public void onClick(View view) {

                Intent Gpsintent = new Intent(StoreSearchActivity.this, GPSSearchActivity.class);

                startActivity(Gpsintent);


            }
        });

        final Button TTSbutton = (Button) findViewById(R.id.TTSbutton);
        TTSbutton.setOnClickListener(new View.OnClickListener() { //TTS 버튼 누를시, 음성검색 화면으로 변경
            @Override
            public void onClick(View view) {

                Intent TTSintent = new Intent(StoreSearchActivity.this, MainActivity.class);

                startActivity(TTSintent);


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


    @Override
    protected void onDestroy() {

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }



        super.onDestroy();

    }


}

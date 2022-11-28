package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainHomeActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname;
    private EditText edid,edpw,edname;
    private Button pgbtn,nextbtn;
    private String ing_mainfv,ing_subfv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickfavorite2);

        //전 액티비티 데이터 가져오기
        Intent getintent = getIntent();
        ing_id = getintent.getStringExtra("ing_id");
        ing_pw = getintent.getStringExtra("ing_pw");
        ing_nickname = getintent.getStringExtra("ing_name");
        ing_mainfv= getintent.getStringExtra("ing_mainfv");



        // 초기화
        if(queue== null){
            queue = Volley.newRequestQueue(this);
        }

        nextbtn = findViewById(R.id.pick2_nextbtn);
        pgbtn = findViewById(R.id.programingbtn);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 프로그래밍 선택시
        pgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ing_subfv = "프로그래밍";
                Toast.makeText(getApplicationContext(),"프로그래밍 선택",Toast.LENGTH_LONG).show();
            }
        });







    }












}
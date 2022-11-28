package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_Register;

import org.json.JSONException;
import org.json.JSONObject;

public class Pickfavorite2Activity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname;
    private EditText edid,edpw,edname;
    private Button pgbtn,nextbtn;
    private String ing_mainfv,ing_subfv="",ing_point="0",ing_rating="새싹";


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
                if(ing_subfv==""){
                    Toast.makeText(getApplicationContext(),"분야를 선택하세요",Toast.LENGTH_LONG).show();
                }

                else {
                    input_userdb();
                    Intent intent = new Intent(view.getContext(), MainHomeActivity.class);
                    intent.putExtra("ing_id", ing_id);
                    intent.putExtra("ing_pw", ing_pw);
                    intent.putExtra("ing_nickname", ing_nickname);
                    intent.putExtra("ing_mainfv", ing_mainfv);
                    intent.putExtra("ing_subfv", ing_subfv);
                    intent.putExtra("ing_rating", ing_rating);
                    intent.putExtra("ing_point", ing_point);
                    startActivity(intent);
                }
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

    //

    private void input_userdb() { // 식당 리스트 UI수정하는거



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"제발",Toast.LENGTH_LONG).show();

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_Register requestRegister = new Request_Register(ing_id,ing_pw,ing_nickname,ing_mainfv,
                ing_subfv,ing_point,ing_rating,responseListener);
        queue = Volley.newRequestQueue(Pickfavorite2Activity.this);
        queue.add(requestRegister);


    }

    //놑북














}
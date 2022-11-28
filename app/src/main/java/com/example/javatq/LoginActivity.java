package com.example.javatq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue queue;
    private TextView registerbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //정의
        registerbtn = findViewById(R.id.login_signupbtn);
        

        if(queue == null){
            queue = Volley.newRequestQueue(getApplicationContext());
        }

        //회원가입 버튼 클릭시 이벤트 설정
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });







    }

    //














}
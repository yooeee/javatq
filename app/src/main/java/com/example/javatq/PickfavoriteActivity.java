package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class PickfavoriteActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname;
    private EditText edid,edpw,edname;
    private Button itbtn;
    private String ing_mainfv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickfavorite);



        //전 액티비티 데이터 가져오기
        Intent getintent = getIntent();
        ing_id = getintent.getStringExtra("ing_id");
        ing_pw = getintent.getStringExtra("ing_pw");
        ing_nickname = getintent.getStringExtra("ing_name");

        itbtn = findViewById(R.id.itbtn);

        itbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ing_mainfv ="IT";
                Intent intent = new Intent(view.getContext(), PickfavoriteActivity.class);
                intent.putExtra("ing_id",user_id);
                intent.putExtra("ing_pw",user_pw);
                intent.putExtra("ing_name",user_nickname);
                intent.putExtra("ing_mainfv",ing_mainfv);
                startActivity(intent);
            }
        });




    }












}
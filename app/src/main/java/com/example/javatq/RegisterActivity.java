package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;
    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private EditText edid,edpw,edname;
    private Button nextbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_signup);
        user_id="";
        user_nickname="";
        user_pw="";
        user_point="0";
        user_rating="새싹";

        nextbtn = findViewById(R.id.signup_nextbtn);
        edid = findViewById(R.id.signup_signID);
        edpw = findViewById(R.id.signup_signPW);
        edname = findViewById(R.id.signup_signName);

        //다음버튼클릭시 이벤트
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id=edid.getText().toString();
                user_pw=edpw.getText().toString();
                user_nickname=edpw.getText().toString();
                Intent intent = new Intent(view.getContext(), PickfavoriteActivity.class);
                intent.putExtra("ing_id",user_id);
                intent.putExtra("ing_pw",user_pw);
                intent.putExtra("ing_name",user_nickname);
                startActivity(intent);
            }
        });




    }












}
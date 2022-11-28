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

public class RegisterActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;
    private String user_id,user_nickname,user_pw;
    private EditText edid,edpw,edname;
    private Button nextbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_signup);
        user_id="";
        user_nickname="";
        user_pw="";


        nextbtn = findViewById(R.id.signup_nextbtn);
        edid = findViewById(R.id.signup_signID);
        edpw = findViewById(R.id.signup_signPW);
        edname = findViewById(R.id.signup_signName);

        //다음버튼클릭시 이벤트
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_id==""||user_pw==""||user_nickname==""){
                    user_id=edid.getText().toString();
                    user_pw=edpw.getText().toString();
                    user_nickname=edpw.getText().toString();
                    Intent intent = new Intent(view.getContext(), PickfavoriteActivity.class);
                    intent.putExtra("ing_id",user_id);
                    intent.putExtra("ing_pw",user_pw);
                    intent.putExtra("ing_name",user_nickname);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"정보를 입력하세요",Toast.LENGTH_LONG).show();
                }

            }
        });




    }












}
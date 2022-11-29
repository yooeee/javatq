package com.example.javatq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_load_logingUser;
import com.example.javatq.Request.Request_loging_user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue queue;
    private TextView registerbtn,loginbtn;
    private EditText edid,edpw;
    private String id,pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //정의
        registerbtn = findViewById(R.id.login_signupbtn);
        loginbtn = findViewById(R.id.login_loginbutton);
        edid = findViewById(R.id.login_editID);
        edpw = findViewById(R.id.login_editpw);





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

        //로그인 버튼 클릭시 이벤트 설정
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = edid.getText().toString();
                pw = edpw.getText().toString();
                login_user();

            }
        });







    }

    //

    private void login_user() { // 식당 리스트 UI수정하는거



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success == true){

                        Toast.makeText(getApplicationContext(),"유저 로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainHomeActivity.class);
                        intent.putExtra("ing_id", id);
                        intent.putExtra("ing_pw", pw);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"유저 로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }





                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_loging_user requestRegister = new Request_loging_user(id,pw,responseListener);
        queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(requestRegister);


    }














}
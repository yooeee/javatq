package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainHomeActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname;
    private EditText edid,edpw,edname;
    private ImageView mybtn,homebtn,comubtn;
//    private String ing_mainfv,ing_subfv="",ing_point="0",ing_rating="새싹";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);

        //전 액티비티 데이터 가져오기
        Intent getintent = getIntent();
        ing_id = getintent.getStringExtra("ing_id");
        ing_pw = getintent.getStringExtra("ing_pw");



        // 초기화
        if(queue== null){
            queue = Volley.newRequestQueue(this);
        }

        mybtn = findViewById(R.id.mymenubtn);
        homebtn = findViewById(R.id.mainhomebtn);
        comubtn =findViewById(R.id.quenstionbtn);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
        MainHomeFragment mainhomefragment= new MainHomeFragment(); // 객체 생성
        transaction.replace(R.id.mainhome_fragment, mainhomefragment); //layout, 교체될 layout
        transaction.addToBackStack(null);
        transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음





        //클릭이벤트
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 프래그먼트매니저를 통해 사용
                MyHomeFragment fragment= new MyHomeFragment(); // 객체 생성
                transaction.replace(R.id.mainhome_fragment, fragment); //layout, 교체될 layout
                transaction.addToBackStack(null);
                transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
            }
        });


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
                MainHomeFragment mainhomefragment= new MainHomeFragment(); // 객체 생성
                transaction.replace(R.id.mainhome_fragment, mainhomefragment); //layout, 교체될 layout
                transaction.addToBackStack(null);
                transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
            }
        });

        comubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
                CommFragment fragment= new CommFragment(); // 객체 생성
                transaction.replace(R.id.mainhome_fragment, fragment); //layout, 교체될 layout
                transaction.addToBackStack(null);
                transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
            }
        });


        //





    }//


    void changeFragment(String index){
        if(index=="1"){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
            MyqaFragment fragment= new MyqaFragment(); // 객체 생성
            transaction.replace(R.id.mainhome_fragment, fragment); //layout, 교체될 layout
            transaction.addToBackStack(null);
            transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음;
        }
        else{
            Log.d("asd","에러");
        }
    }












}
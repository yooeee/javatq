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
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_Register;
import com.example.javatq.Request.Request_load_logingUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainHomeActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button registerbtn;
    private LoadingDialogBar loadingDialogBar;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname,ing_point,ing_rating,ing_mainfv,ing_subfv;
    private EditText edid,edpw,edname;
    private ImageView mybtn,homebtn,comubtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);



        //전 액티비티 데이터 가져오기
        Intent getintent = getIntent();
        ing_id = getintent.getStringExtra("ing_id");
        ing_pw = getintent.getStringExtra("ing_pw");

        //로딩창 객체 생성
        loadingDialogBar = new LoadingDialogBar(this);
        loadingDialogBar.ShowDilaog("불러오는 중.");

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


    private void load_logingUser() { // 식당 리스트 UI수정하는거



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i=0; i < jsonArray.length(); i++){
                        try{
                            ing_nickname= jsonObject.getString("user_nickname");
                            ing_point= jsonObject.getString("user_point");
                            ing_mainfv= jsonObject.getString("user_mainfv");
                            ing_subfv= jsonObject.getString("user_subfv");
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                    loadingDialogBar.HideDialog();
                    System.out.println("가져온 데이터 :"+ing_nickname+ing_point+ing_mainfv+ing_subfv);





                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_logingUser requestRegister = new Request_load_logingUser(ing_id,responseListener);
        queue = Volley.newRequestQueue(MainHomeActivity.this);
        queue.add(requestRegister);


    }












}
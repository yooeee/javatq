package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_load_hometqlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainHomeActivity extends AppCompatActivity {

    private RequestQueue queue,queue2;
    private Button registerbtn;
    private LoadingDialogBar loadingDialogBar;
    private String tt1,tt2,tt3,tt4;
    private Button tt1btn,tt2btn,tt3btn,tt4btn;

    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv;
    private int  ing_point;
    private EditText edid,edpw,edname;
    private ImageView mybtn,homebtn,comubtn;
    private String uq_qt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);



        //전 액티비티 데이터 가져오기
        Intent getintent = getIntent();
        ing_id = getintent.getStringExtra("ing_id");
        ing_pw = getintent.getStringExtra("ing_pw");
        ing_nickname = getintent.getStringExtra("ing_nickname");
        ing_rating = getintent.getStringExtra("ing_rating");
        ing_point = getintent.getIntExtra("ing_point",0);
        ing_mainfv = getintent.getStringExtra("ing_mainfv");
        ing_subfv = getintent.getStringExtra("ing_subfv");

        System.out.println(ing_id+ing_pw+ing_mainfv+ing_subfv);


//        //로딩창 객체 생성
//        loadingDialogBar = new LoadingDialogBar(this);
//        loadingDialogBar.ShowDilaog("불러오는 중.");

        // 초기화
        if(queue== null){
            queue = Volley.newRequestQueue(getApplicationContext());
        }


        //홈 글 불러오기
        if(queue2== null){
            queue2 = Volley.newRequestQueue(getApplicationContext());
        }
//        load_home_tqlist();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
        MainHomeFragment mainhomefragment= new MainHomeFragment(); // 객체 생성
        Bundle bundle = new Bundle();
        bundle.putString("ing_subfv",ing_subfv);
        mainhomefragment.setArguments(bundle);
        transaction.replace(R.id.mainhome_fragment, mainhomefragment); //layout, 교체될 layout
        transaction.addToBackStack(null);
        transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
//        loadingDialogBar.HideDialog();

        mybtn = findViewById(R.id.mymenubtn);
        homebtn = findViewById(R.id.mainhomebtn);
        comubtn =findViewById(R.id.quenstionbtn);






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
                Bundle bundle = new Bundle();
                bundle.putString("ing_subfv",ing_subfv);
                mainhomefragment.setArguments(bundle);
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

                Bundle bundle = new Bundle();
                bundle.putString("ing_id",ing_id);
                bundle.putString("ing_pw",ing_pw);
                bundle.putString("ing_nickname",ing_nickname);
                bundle.putString("ing_rating",ing_rating);
                bundle.putInt("ing_point",ing_point);
                bundle.putString("ing_mainfv",ing_mainfv);
                bundle.putString("ing_subfv",ing_subfv);

                fragment.setArguments(bundle);


                transaction.replace(R.id.mainhome_fragment, fragment); //layout, 교체될 layout
                transaction.addToBackStack(null);
                transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음
            }
        });


        //





    }//


    void changeFragment(int index){
        if(index==1){
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

    public void replaceFragmentComm(Fragment fragment,String loging_id,String uq_id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("ing_id",loging_id);
        bundle.putString("uq_id",uq_id);
        System.out.println("rfcomm 작동 로그인아이디"+ing_id);

        fragment.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.mainhome_fragment, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainhome_fragment, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }




    private void load_home_tqlist() {


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("가져온거",data);
                    int length = jsonArray.length();
                    for (int i=0; i < 4; i++){

                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            uq_qt= jsonObject.getString("uq_qt");
                            switch(i){
                                case 0 : tt1=uq_qt;
                                break;
                                case 1 : tt2=uq_qt;
                                break;
                                case 2 : tt3=uq_qt;
                                break;
                                case 3 : tt4=uq_qt;
                                break;

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }

                    System.out.println("tt1="+tt1);
                    System.out.println("tt2="+tt2);
                    System.out.println("tt3="+tt3);
                    System.out.println("tt4="+tt4);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    // 프래그먼트매니저를 통해 사용 (초기 프래그먼트 설정)
                    Bundle bd = new Bundle();
                    bd.putString("tt1",tt1);
                    bd.putString("tt2",tt2);
                    bd.putString("tt3",tt3);
                    bd.putString("tt4",tt4);
                    bd.putInt("length",length);

                    System.out.println("tt1가져왔나?"+tt1);


                    MainHomeFragment mainhomefragment= new MainHomeFragment(); // 객체 생성
                    mainhomefragment.setArguments(bd);
                    transaction.replace(R.id.mainhome_fragment, mainhomefragment); //layout, 교체될 layout
                    transaction.addToBackStack(null);
                    transaction.commit(); //commit으로 저장 하지 않으면 화면 전환이 되지 않음


                    loadingDialogBar.HideDialog();





                }
                catch (JSONException e){
                    e.printStackTrace();

                }

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_hometqlist requestRegister = new Request_load_hometqlist(ing_subfv,responseListener);
        queue2 = Volley.newRequestQueue(this);
        queue2.add(requestRegister);


    }












}
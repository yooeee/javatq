package com.example.javatq;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_load_hometq;
import com.example.javatq.Request.Request_load_hometqlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainHomeFragment extends Fragment {

    private View view;
    private Button btn_frag,tq_answerbtn,tq_viewanswerbtn;
    private String tt1,tt2,tt3,tt4;
    Button btn1,btn2,btn3,btn4;
    private TextView redtv,tq_titletv;
    private RequestQueue queue;
    private String user_id,user_nickname,user_pw,user_point,user_rating;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv,ing_tq_id;
    private String uq_qt,getTime,tq_title;
    private LoadingDialogBar loadingDialogBar;

    public static TQAnswerFragment newInstanceTQA() {
        return new TQAnswerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        view = inflater.inflate(R.layout.fragment_mainhome, container, false);



        ing_id = this.getArguments().getString("ing_id");
        ing_nickname = this.getArguments().getString("ing_nickname");
        ing_rating = this.getArguments().getString("ing_rating");
        ing_subfv = this.getArguments().getString("ing_subfv");
        ing_pw = this.getArguments().getString("ing_pw");


        //현재 날짜 가져오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        getTime = sdf.format(date);
        System.out.println("날짜는 :"+getTime);
        //
        ing_subfv = getArguments().getString("ing_subfv");
        System.out.println("잉서브가져왔어요 프래그먼트>>?"+ing_subfv);

        btn1 = view.findViewById(R.id.tt1);
        btn2 = view.findViewById(R.id.tt2);
        btn3 = view.findViewById(R.id.tt3);
        btn4 = view.findViewById(R.id.tt4);
        redtv=view.findViewById(R.id.redtext);
        tq_answerbtn = view.findViewById(R.id.gototqanswerbtn);
        tq_viewanswerbtn = view.findViewById(R.id.gototqaianswerbtn);
        tq_titletv=view.findViewById(R.id.home_todayquenstion);

        //로딩창 객체 생성
        loadingDialogBar = new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("불러오는 중.");
        if(queue== null){
            queue = Volley.newRequestQueue(getContext());
        }

        load_tq();
        load_home_tqlist();

        btn1.setText(tt1);
        btn2.setText(tt2);
        btn3.setText(tt3);
        btn4.setText(tt4);

        tq_answerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainHomeActivity)getActivity()).replaceFragmentTQAnswer(MainHomeFragment.newInstanceTQA(),ing_tq_id
                        ,tq_title,ing_nickname,ing_rating);
            }
        });


//        btn_frag2 = view.findViewById(R.id.btn_frag2);
        //fragment에서는 그냥 findViewById로 Button id를 가져올 수 없음.
        //인플레이터된 view를 사용하여 가져옴.
//        btn_frag2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                Fragment2 fragment2 = new Fragment2();
//                transaction.replace(R.id.frameLayout, fragment2);
//                transaction.commit();
//            }
//        });

        return view;
    }



    private void load_tq() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("가져온거",data);
                    for (int i=0; i < 4; i++){

                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            tq_title = jsonObject.getString("tq_quenstion");
                            ing_tq_id = jsonObject.getString("tq_id");
                            System.out.println("서버에서 tq_id가져왓나"+ing_tq_id);


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    tq_titletv.setText(tq_title);

                }
                catch (JSONException e){
                    e.printStackTrace();
                }



            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_hometq requestRegister = new Request_load_hometq(ing_subfv,getTime,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);

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


                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    System.out.println("tt1여기프래그먼트임="+tt1);
                    System.out.println("tt2="+tt2);
                    System.out.println("tt3="+tt3);
                    System.out.println("tt4="+tt4);
                    btn1.setText(tt1);
                    btn2.setText(tt2);
                    btn3.setText(tt3);
                    btn4.setText(tt4);
                    redtv.setText(length+"개의 질문이 당신의 답변을 기다리고 있어요.");
                    loadingDialogBar.HideDialog();


                }
                catch (JSONException e){
                    e.printStackTrace();

                }

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_hometqlist requestRegister = new Request_load_hometqlist(ing_subfv,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }




}
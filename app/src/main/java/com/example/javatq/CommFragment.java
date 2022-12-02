package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Item.uqItem;
import com.example.javatq.Request.Request_load_comm_uqlist;
import com.example.javatq.Request.Request_load_logingUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommFragment extends Fragment {

    private View view;
    private Button btn_frag2;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv;
    private String uq_id,uq_qt,uq_cur,uq_nickname,uq_date;
    private int  ing_point;
    private RequestQueue queue;
    private Button registerbtn;
    private LoadingDialogBar loadingDialogBar;
    private RecyclerView rv;
    private uqAdapter adpt;
    private ArrayList<uqItem> uq_items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);

        ing_id = this.getArguments().getString("ing_id");
        ing_pw = this.getArguments().getString("ing_pw");

        //로딩창 객체 생성
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("불러오는 중.");
        load_userdb();
        //load_uqlist("1"); // 따봉수 가장높은거 가져오기(1번)
//        load_uqlist("2");// 모든글 (2번)

        uq_items = new ArrayList<>(); // 게시글 담을곳
        //어댑터 세팅
        adpt = new uqAdapter();
        //레이아웃 방식 지정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = view.findViewById(R.id.comm_uqlist);
        rv.setAdapter(adpt);
        rv.setLayoutManager(manager);



        return view;
    }

    private void load_uqlist(String code) { // 게시글 가져오기(최신순으로)
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            String maxtitle;
            String maxwonder;
            int max_cur = 0;
            String maxdate;
            String maxnickname;
            String maxid;

            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("가져온거",data);


                    for (int i = 0; i < jsonArray.length(); i++){
                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            uq_id = jsonObject.getString("uq_id");
                            uq_qt = jsonObject.getString("uq_qt");
                            uq_nickname = jsonObject.getString("uq_nickname");
                            uq_cur = jsonObject.getString("uq_cur");
                            uq_date = jsonObject.getString("uq_date");


                            uq_items.add(new uqItem(uq_id,uq_qt,"나도 궁금해요 :",uq_cur,uq_date,uq_nickname));
                            adpt.notifyDataSetChanged();
                            adpt.setRes_list_item(uq_items);

                            if(Integer.parseInt(uq_cur) >max_cur){
                                max_cur = Integer.parseInt(uq_cur);
                                maxtitle = uq_qt;
                                maxid=uq_id;
                                maxwonder="나도 궁금해요 :";
                                maxdate = uq_date;
                                maxnickname=uq_nickname;

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                
                // 따봉많은거 가져오기
                TextView uq_titletv,uq_wondertv,uq_wondernumtv,uq_datetv,uq_nicknametv;
                uq_titletv = view.findViewById(R.id.uq_toptitle);
                uq_wondertv= view.findViewById(R.id.uq_topwonder);
                uq_wondernumtv = view.findViewById(R.id.uq_topnum);
                uq_datetv = view.findViewById(R.id.uq_topdate);
                uq_nicknametv = view.findViewById(R.id.uq_topname);
                System.out.println("체크"+maxid+maxnickname+maxtitle);
                uq_titletv.setText(maxtitle);
                uq_wondertv.setText(maxwonder);
                uq_wondernumtv.setText(String.valueOf(max_cur));
                uq_datetv.setText(maxdate);
                uq_nicknametv.setText(maxnickname);





                loadingDialogBar.HideDialog();

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_comm_uqlist requestRegister = new Request_load_comm_uqlist(ing_subfv,code,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }

    private void load_userdb() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    ing_nickname = null;
                    ing_rating= null;
                    ing_mainfv= null;
                    ing_subfv= null;
                    ing_point= 0;
                    for (int i = 0; i < jsonArray.length(); i++){
                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            ing_nickname = jsonObject.getString("user_nickname");
                            ing_rating = jsonObject.getString("user_rating");
                            ing_point = jsonObject.getInt("user_point");
                            ing_mainfv = jsonObject.getString("user_mainfv");
                            ing_subfv = jsonObject.getString("user_subfv");


                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }


                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                System.out.println("ingsubfv 1빠따="+ing_subfv);
                load_uqlist("2");


            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_logingUser requestRegister = new Request_load_logingUser(ing_id,ing_pw,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }






}
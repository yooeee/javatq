package com.example.javatq;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_load_uqinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class viewUQFragment extends Fragment {

    public static viewUQFragment newInstance(String dataid, String datauqid) {
        return new viewUQFragment();
    }



    private View view;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv,writter_id;
    private String uq_id,uq_rating,uq_nickname,uq_qt,uq_q,uq_img,uq_cur,uq_date,uq_time;
    private Button btn_frag2;
    private String ing_uq_id;
    private RequestQueue queue;
    private LoadingDialogBar loadingDialogBar;

    private TextView uq_title_tv,uq_nickname_tv,uq_rating_tv,uq_date_tv;
    private TextView uq_quenstion_tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_viewquenstion, container, false);
        //전  데이터 가져오기

        ing_id = getArguments().getString("ing_id");
        ing_uq_id = getArguments().getString("uq_id");

        // 뷰 초기화
        uq_title_tv = view.findViewById(R.id.viewuq_title);
        uq_nickname_tv = view.findViewById(R.id.viewuq_tqnickname);
        uq_rating_tv = view.findViewById(R.id.viewuq_uqrating);
        uq_date_tv = view.findViewById(R.id.viewuq_uqdate);
        uq_quenstion_tv = view.findViewById(R.id.viewuq_quenstion);


        //로딩창 객체 생성
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("불러오는 중.");
        load_uqinfo();





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

    private void load_uqinfo() { // 게시글 가져오기(최신순으로)
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("가져온거",data);


                    for (int i = 0; i < jsonArray.length(); i++){
                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            uq_rating = jsonObject.getString("uq_rating");
                            uq_qt = jsonObject.getString("uq_qt");
                            uq_q = jsonObject.getString("uq_q");
                            uq_nickname = jsonObject.getString("uq_nickname");
                            uq_cur = jsonObject.getString("uq_cur");
                            uq_date = jsonObject.getString("uq_date");
                            uq_time = jsonObject.getString("uq_time");
                            writter_id = jsonObject.getString("writter_nickname");


                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                uq_title_tv.setText(uq_qt);
                uq_quenstion_tv.setText(uq_q);
                uq_nickname_tv.setText("작성자: "+uq_nickname);
                uq_rating_tv.setText("등급 : "+uq_rating);
                uq_date_tv.setText(uq_date+" "+uq_time);
                if(!(writter_id.equals(ing_id))) // 본인글일경우 수정하기 활성화 아니면 비활성화
                {
                    Button revisebtn;
                    revisebtn = view.findViewById(R.id.viewuq_changeuq);
                    revisebtn.setVisibility(View.GONE);

                }
                loadingDialogBar.HideDialog();

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_uqinfo requestRegister = new Request_load_uqinfo(ing_uq_id,ing_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }





}
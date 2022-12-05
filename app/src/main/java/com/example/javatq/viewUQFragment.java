package com.example.javatq;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Adapter.uqaAdapter;
import com.example.javatq.Item.uqItem;
import com.example.javatq.Item.uqaItem;
import com.example.javatq.Listener.uqListener;
import com.example.javatq.Listener.uqaListener;
import com.example.javatq.Request.Request_delete_uqa;
import com.example.javatq.Request.Request_load_uqainfo;
import com.example.javatq.Request.Request_load_uqinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewUQFragment extends Fragment {

    public static viewUQFragment newInstance(String dataid, String datauqid) {
        return new viewUQFragment();
    }



    private View view;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv,writter_id;
    private String uq_id,uq_rating,uq_nickname,uq_qt,uq_q,uq_img,uq_cur,uq_date,uq_time;
    private String uqa_nickname,uqa_id,uqa_rating,uqa_date,uqa_answer;
    private Button btn_frag2;
    private String ing_uq_id;
    private RequestQueue queue,queue2;
    private LoadingDialogBar loadingDialogBar;
    Button revisebtn,deletebtn,uqarevisebtn,uqadeletebtn;
    private RecyclerView rv;
    private uqaAdapter adpt;
    private ArrayList<uqaItem> uqa_items;
    private String checkmy;

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
        revisebtn = view.findViewById(R.id.viewuq_changeuq);
        deletebtn = view.findViewById(R.id.viewuq_deleteuq);
        revisebtn.setVisibility(View.GONE);
        deletebtn.setVisibility(View.GONE);



        //로딩창 객체 생성
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("불러오는 중.");
        if(queue== null){
            queue = Volley.newRequestQueue(getContext());
        }
        if(queue2== null){
            queue2 = Volley.newRequestQueue(getContext());
        }
        load_uqinfo();
        load_uqainfo();

        uqa_items = new ArrayList<>();
        adpt = new uqaAdapter();
        //레이아웃 방식 지정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = view.findViewById(R.id.uqa_list);
        rv.setAdapter(adpt);
        rv.setLayoutManager(manager);

//         ============어댑터 클릭리스너 구현 ===========
        adpt.setRes_list_item(uqa_items);
        adpt.setListClickListener(new uqaListener() {
            @Override
            public void onListClick(uqaAdapter.ViewHolder holder, View view, int position) {
                uqaItem item = adpt.getItem(position);
                System.out.println(holder);
                System.out.println(item.getUqa_nickname());
            }

            @Override
            public void onReviseClick(uqaAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onDeleteClick(uqaAdapter.ViewHolder holder, View view, int position) {
                uqaItem item = adpt.getItem(position);

                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("[시스템]"); //제목
                dlg.setMessage("삭제하시겠습니까?"); // 메시지
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"삭제가 취소되었습니다..",Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteUqa(item.getUqa_id(),position);

                    }


                });


                dlg.show();


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

    private void deleteUqa(String delete_uqa_id,int delete_position) {

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) { // 등록에 성공한 경우
                        Toast.makeText(getContext(),"삭제 성공하였습니다.",Toast.LENGTH_SHORT).show();

                    } else { // 등록에 실패한 경우
                        Toast.makeText(getContext(),"삭제 실패하였습니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                uqa_items.remove(delete_position);
                adpt.notifyDataSetChanged();


            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_delete_uqa requestRegister = new Request_delete_uqa(delete_uqa_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);



    }

    private void load_uqainfo() {

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
                            uqa_id = jsonObject.getString("uqa_id");
                            String i3 = jsonObject.getString("uqa_rating");
                            uqa_rating = "["+i3+"]";
                            uqa_nickname = jsonObject.getString("uqa_nickname");
                            uqa_answer = jsonObject.getString("uqa_answer");
                            String i1 = jsonObject.getString("uqa_date");
                            String i2 = jsonObject.getString("uqa_time");
                            uqa_date = "["+i1+"] ["+i2+"]";
                            String i4 = jsonObject.getString("user_id");
                            if(ing_id.equals(i4)){
                                checkmy ="1";
                                uqa_items.add(new uqaItem(uqa_id,uqa_nickname,uqa_rating,uqa_date,uqa_answer,checkmy));
                                System.out.println(uqa_items);
                                adpt.notifyDataSetChanged();
                                adpt.setRes_list_item(uqa_items);
                            }
                            else{
                                checkmy ="0";
                                uqa_items.add(new uqaItem(uqa_id,uqa_nickname,uqa_rating,uqa_date,uqa_answer,checkmy));
                                System.out.println(uqa_items);
                                adpt.notifyDataSetChanged();
                                adpt.setRes_list_item(uqa_items);
                            }



                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                loadingDialogBar.HideDialog();

            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_uqainfo requestRegister = new Request_load_uqainfo(ing_uq_id,ing_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);



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
                if(writter_id.equals(ing_id)) // 본인글일경우 수정하기 활성화 아니면 비활성화
                {
                    System.out.println("equals작동"+writter_id+ing_id);
                    revisebtn.setVisibility(View.VISIBLE);
                    deletebtn.setVisibility(View.VISIBLE);

                }


            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_load_uqinfo requestRegister = new Request_load_uqinfo(ing_uq_id,ing_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }





}
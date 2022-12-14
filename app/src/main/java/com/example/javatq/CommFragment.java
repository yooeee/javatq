package com.example.javatq;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
import com.example.javatq.Listener.uqListener;
import com.example.javatq.Request.Request_load_comm_uqlist;
import com.example.javatq.Request.Request_load_logingUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommFragment extends Fragment {

    public static viewUQFragment newInstance() {
        return new viewUQFragment();
    }

    public static writeUQFragment newInstance2() {
        return new writeUQFragment();
    }

    private View view;
    private Button btn_frag2;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv;
    private String uq_id,uq_qt,uq_cur,uq_nickname,uq_date;
    private int  ing_point;

    private Button registerbtn;
    private RequestQueue queue, queue2;
    private LoadingDialogBar loadingDialogBar;
    private RecyclerView rv;
    private uqAdapter adpt;
    private ArrayList<uqItem> uq_items;
    private ImageView createTQbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);

        ing_id = this.getArguments().getString("ing_id");
        ing_nickname = this.getArguments().getString("ing_nickname");
        ing_rating = this.getArguments().getString("ing_rating");
        ing_subfv = this.getArguments().getString("ing_subfv");
        ing_pw = this.getArguments().getString("ing_pw");


        createTQbtn = view.findViewById(R.id.comm_createbtn);
        createTQbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bundle.putString("ing_id",ing_id);
                //                bundle.putString("ing_pw",ing_pw);
                //                bundle.putString("ing_nickname",ing_nickname);
                //                bundle.putString("ing_rating",ing_rating);
                //                bundle.putInt("ing_point",ing_point);
                //                bundle.putString("ing_mainfv",ing_mainfv);
                //                bundle.putString("ing_subfv",ing_subfv);
                ((MainHomeActivity)getActivity()).replaceFragmentWriteUQ(CommFragment.newInstance2(),ing_id,ing_nickname,ing_rating,ing_subfv
                ,ing_mainfv,ing_point,ing_pw);
            }
        });
        //????????? ?????? ??????
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("???????????? ???.");
        if(queue== null){
            queue = Volley.newRequestQueue(getContext());
        }

        load_userdb();
        //load_uqlist("1"); // ????????? ??????????????? ????????????(1???)
//        load_uqlist("2");// ????????? (2???)

        uq_items = new ArrayList<>(); // ????????? ?????????
        //????????? ??????
        adpt = new uqAdapter();
        //???????????? ?????? ??????
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = view.findViewById(R.id.comm_uqlist);
        rv.setAdapter(adpt);
        rv.setLayoutManager(manager);

        // ============????????? ??????????????? ?????? ===========
        adpt.setRes_list_item(uq_items);
        adpt.setListClickListener(new uqListener() {
            @Override
            public void onListClick(uqAdapter.ViewHolder holder, View view, int position) {
                uqItem item = adpt.getItem(position);
                System.out.println("?????? ????????? ?????????????"+item.getUqid());
                ((MainHomeActivity)getActivity()).replaceFragmentViewUQ(CommFragment.newInstance(),ing_id,item.getUqid(),ing_nickname,ing_rating
                ,ing_subfv,ing_pw);
            System.out.println("????????? ??????????????? ???????????? ??????????????????!!"+ing_nickname);

            }
        });



        return view;
    }

    private void load_uqlist(String code) { // ????????? ????????????(???????????????)
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("????????????",data);


                    for (int i = 0; i < jsonArray.length(); i++){
                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            uq_id = jsonObject.getString("uq_id");
                            uq_qt = jsonObject.getString("uq_qt");
                            uq_nickname = jsonObject.getString("uq_nickname");
                            uq_cur = jsonObject.getString("uq_cur");
                            uq_date = jsonObject.getString("uq_date");


                            uq_items.add(new uqItem(uq_id,uq_qt,"?????? ???????????? :",uq_cur,uq_date,uq_nickname));
                            adpt.notifyDataSetChanged();
                            adpt.setRes_list_item(uq_items);



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
        }; // ????????? Volley??? ???????????? ????????? ???.
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
                System.out.println("ingsubfv 1??????="+ing_subfv);
                load_uqlist("2");


            }
        }; // ????????? Volley??? ???????????? ????????? ???.
        Request_load_logingUser requestRegister = new Request_load_logingUser(ing_id,ing_pw,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);


    }






}
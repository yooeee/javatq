package com.example.javatq;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Adapter.tqaAdapter;
import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Item.tqaItem;
import com.example.javatq.Item.uqItem;
import com.example.javatq.Request.Request_load_tq;
import com.example.javatq.Request.Request_load_tqa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TQUsersanswerFragment extends Fragment {

    public static TQUsersanswerFragment newInstance() {
        return new TQUsersanswerFragment();
    }

    private View view;
    private Button btn_frag2;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv,ing_tq_id,ing_tq_title;
    private String uq_id,uq_qt,uq_cur,uq_nickname,uq_date;
    private int  ing_point;

    private Button registerbtn;
    private RequestQueue queue, queue2;
    private LoadingDialogBar loadingDialogBar;
    private RecyclerView rv;
    private tqaAdapter adpt;
    private ArrayList<tqaItem> tqa_items;
    private ImageView createTQbtn;

    private Button btn;
    private TextView title_tv;
    private EditText answer_et;
    private String tqa_date,tqa_answer,ing_tqa_answer;
    private EditText myanswer_et,aianswer_et;
    private TextView tq_titletv;
    private String tq_title,ai_answer;
    private String getTime;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tquseranswers, container, false);


        ing_tq_id = this.getArguments().getString("ing_tq_id");
        ing_tq_title = this.getArguments().getString("ing_tq_title");

        //로딩창 객체 생성
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("불러오는 중.");
        if(queue== null){
            queue = Volley.newRequestQueue(getContext());
        }

        tqa_items = new ArrayList<>(); // 게시글 담을곳
        //어댑터 세팅
        adpt = new tqaAdapter();
        //레이아웃 방식 지정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = view.findViewById(R.id.tquseranswer_list);
        rv.setAdapter(adpt);
        rv.setLayoutManager(manager);

        load_tqa();




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

    private void load_tqa() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("가져온거",data);
                    for (int i=0; i < jsonArray.length(); i++){

                        try{
                            jsonObject = jsonArray.getJSONObject(i);

                            String getName = jsonObject.getString("user_nickname");
                            String getRating = jsonObject.getString("user_rating");
                            String getDate = jsonObject.getString("tqa_date");
                            String getAnswer = jsonObject.getString("tqa_answer");

                            tqa_items.add(new tqaItem(getName,getRating,getDate,"0",getAnswer));
                            adpt.notifyDataSetChanged();
                            adpt.setRes_list_item(tqa_items);



                        }
                        catch (JSONException e) {
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
        Request_load_tqa requestRegister = new Request_load_tqa(ing_tq_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);

    }
}
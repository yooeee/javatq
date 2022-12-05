package com.example.javatq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Item.uqItem;
import com.example.javatq.Item.uqaItem;
import com.example.javatq.Request.Request_input_uq;
import com.example.javatq.Request.Request_input_uqa;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class writeUQFragment extends Fragment {

    public static CommFragment newInstance() {
        return new CommFragment();
    }



    private View view;
    private Button btn_frag2;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv;
    private String uq_id,uq_qt,uq_cur,uq_nickname,uq_date;
    private int  ing_point;
    private String ing_date,ing_time;

    private Button btn;
    private EditText titleET,quenstionET;
    private String getText_title,getText_quenstion;
    private RequestQueue queue, queue2;
    private LoadingDialogBar loadingDialogBar;
    private RecyclerView rv;
    private uqAdapter adpt;
    private ArrayList<uqItem> uq_items;
    private Button createTQbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_writequenstion, container, false);

        ing_id = this.getArguments().getString("ing_id");
        ing_pw = this.getArguments().getString("ing_pw");
        ing_nickname = this.getArguments().getString("ing_nickname");
        ing_rating = this.getArguments().getString("ing_rating");
        ing_subfv = this.getArguments().getString("ing_subfv");

        titleET = view.findViewById(R.id.writeuq_title);
        quenstionET = view.findViewById(R.id.writeuq_quenstion);
        btn = view.findViewById(R.id.writeuq_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               getText_title = titleET.getText().toString();
               getText_quenstion = quenstionET.getText().toString();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat asd = new SimpleDateFormat("hh:mm:ss");
                ing_date = sdf.format(date);
                ing_time = asd.format(date);
                writeuq();



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

    private void writeuq() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) { // 등록에 성공한 경우
                        Toast.makeText(getContext(),"질문 등록 성공하였습니다.",Toast.LENGTH_SHORT).show();

                    } else { // 등록에 실패한 경우
                        Toast.makeText(getContext(),"질문 등록 실패하였습니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                ((MainHomeActivity)getActivity()).replaceFragmentWriteUQ(writeUQFragment.newInstance(),ing_id,ing_nickname,ing_rating,ing_subfv
                        ,ing_mainfv,ing_point,ing_pw);




            }
        }; // 서버로 Volley를 이용해서 요청을 함.
        Request_input_uq requestRegister = new Request_input_uq(ing_rating,ing_nickname,getText_title,
                getText_quenstion,ing_date,ing_time,ing_subfv,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);

    }
}
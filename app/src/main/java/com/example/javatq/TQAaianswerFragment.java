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
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Item.uqItem;
import com.example.javatq.Request.Request_load_hometq;
import com.example.javatq.Request.Request_load_tq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TQAaianswerFragment extends Fragment {



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
    private uqAdapter adpt;
    private ArrayList<uqItem> uq_items;
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
        view = inflater.inflate(R.layout.fragment_tqaianswer, container, false);

        ing_tq_id = this.getArguments().getString("ing_tq_id");
        ing_tqa_answer = this.getArguments().getString("ing_tqa_answer");

        tq_titletv = view.findViewById(R.id.aianswer_title);
        myanswer_et = view.findViewById(R.id.aianswer_myanswer);
        aianswer_et = view.findViewById(R.id.aianswer_aianswer);
        btn = view.findViewById(R.id.aianswer_anotheranswerbtn);


        //????????? ?????? ??????
        loadingDialogBar=new LoadingDialogBar(getContext());
        loadingDialogBar.ShowDilaog("???????????? ???.");
        if(queue== null){
            queue = Volley.newRequestQueue(getContext());
        }

        load_tq();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainHomeActivity)getActivity()).replaceFragmentTQUserAnswer(TQAaianswerFragment.newInstance(),ing_tq_id,tq_title);
            }
        });




        System.out.println("??????????????????"+ing_tq_id);





//        btn_frag2 = view.findViewById(R.id.btn_frag2);
        //fragment????????? ?????? findViewById??? Button id??? ????????? ??? ??????.
        //?????????????????? view??? ???????????? ?????????.
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
                    Log.d("????????????",data);
                    for (int i=0; i < 4; i++){

                        try{
                            jsonObject = jsonArray.getJSONObject(i);
                            tq_title = jsonObject.getString("tq_quenstion");
                            ai_answer = jsonObject.getString("tq_answer");
                            ing_tq_id = jsonObject.getString("tq_id");
                            System.out.println("???????????? tq_id????????????"+ing_tq_id);


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }




                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                tq_titletv.setText(tq_title);
                aianswer_et.setText(ai_answer);
                myanswer_et.setText(ing_tqa_answer);
                loadingDialogBar.HideDialog();



            }
        }; // ????????? Volley??? ???????????? ????????? ???.
        Request_load_tq requestRegister = new Request_load_tq(ing_tq_id,responseListener);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(requestRegister);

    }


}
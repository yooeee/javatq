package com.example.javatq;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.javatq.Request.Request_load_logingUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommFragment extends Fragment {

    private View view;
    private Button btn_frag2;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv;
    private int  ing_point;
    private RequestQueue queue;
    private Button registerbtn;
    private LoadingDialogBar loadingDialogBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);

        ing_id = this.getArguments().getString("ing_id");
        ing_pw = this.getArguments().getString("ing_pw");
        ing_rating = this.getArguments().getString("ing_rating");
        ing_nickname = this.getArguments().getString("ing_nickname");
        ing_point = this.getArguments().getInt("ing_point");
        ing_mainfv = this.getArguments().getString("ing_mainfv");
        ing_subfv = this.getArguments().getString("ing_subfv");

        Log.d("값가져왔나",ing_mainfv);

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






}
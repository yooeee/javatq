package com.example.javatq;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainHomeFragment extends Fragment {

    private View view;
    private Button btn_frag2;
    private String tt1,tt2,tt3,tt4;
    Button btn1,btn2,btn3,btn4;
    private TextView redtv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainhome, container, false);

        tt1 = this.getArguments().getString("tt1");
        tt2 = this.getArguments().getString("tt2");
        tt3 = this.getArguments().getString("tt3");
        tt4 = this.getArguments().getString("tt4");
        int length = this.getArguments().getInt("length");

        System.out.println("tt1가져왔어요 프래그먼트>>?"+tt1);

        btn1 = view.findViewById(R.id.tt1);
        btn2 = view.findViewById(R.id.tt2);
        btn3 = view.findViewById(R.id.tt3);
        btn4 = view.findViewById(R.id.tt4);
        redtv=view.findViewById(R.id.redtext);

        btn1.setText(tt1);
        btn2.setText(tt2);
        btn3.setText(tt3);
        btn4.setText(tt4);
        redtv.setText(length+"개의 질문이 당신의 답변을 기다리고 있어요.");

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
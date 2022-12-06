package com.example.javatq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyHomeFragment extends Fragment {

    public static MyqaFragment newInstance() {
        return new MyqaFragment();
    }

    private View view;
    private Button myqabtn,mygiftbtn,pickfv,transpointbtn;
    private MainHomeActivity mActivity;
    private String ing_id,ing_pw,ing_nickname,ing_rating,ing_mainfv,ing_subfv,ing_tq_id;
    private TextView tv_nickname,tv_main,tv_rating;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myhome, container, false);

        ing_id = this.getArguments().getString("ing_id");
        ing_nickname = this.getArguments().getString("ing_nickname");
        ing_rating = this.getArguments().getString("ing_rating");
        ing_subfv = this.getArguments().getString("ing_subfv");
        ing_mainfv = this.getArguments().getString("ing_mainfv");
        ing_pw = this.getArguments().getString("ing_pw");

        tv_nickname = view.findViewById(R.id.tv_usernickname);
        tv_main = view.findViewById(R.id.tv_userfavorite);
        tv_rating = view.findViewById(R.id.tv_userrating);

        tv_nickname.setText(ing_nickname);
        tv_main.setText("["+ing_mainfv+"]"+" - ["+ing_subfv+"]");
        tv_rating.setText("등급: "+ing_rating);


        myqabtn = view.findViewById(R.id.myqabtn);
//        mygiftbtn = view.findViewById(R.id.mygiftbtn);
//        pickfv = view.findViewById(R.id.myfavor_re_btn);
//        transpointbtn = view.findViewById(R.id.mytopointbtn);

        //클릭이벤트
        myqabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainHomeActivity)getActivity()).replaceFragment(MyHomeFragment.newInstance());
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
}
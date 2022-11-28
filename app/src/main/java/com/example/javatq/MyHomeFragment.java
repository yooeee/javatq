package com.example.javatq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyHomeFragment extends Fragment {

    private View view;
    private Button myqabtn,mygiftbtn,pickfv,transpointbtn;
    private MainHomeActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myhome, container, false);

        myqabtn = view.findViewById(R.id.myqabtn);
//        mygiftbtn = view.findViewById(R.id.mygiftbtn);
//        pickfv = view.findViewById(R.id.myfavor_re_btn);
//        transpointbtn = view.findViewById(R.id.mytopointbtn);

        //클릭이벤트
        myqabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.changeFragment("1");
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
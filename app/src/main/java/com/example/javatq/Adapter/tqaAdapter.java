package com.example.javatq.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatq.Item.tqaItem;
import com.example.javatq.Item.uqaItem;
import com.example.javatq.Listener.tqaListener;
import com.example.javatq.Listener.uqaListener;
import com.example.javatq.R;

import java.util.ArrayList;

public class tqaAdapter extends RecyclerView.Adapter<tqaAdapter.ViewHolder> implements tqaListener {

    ArrayList<tqaItem> res_list_item = new ArrayList<>();



    //========클릭 이벤트 구현===========
    tqaListener listClickListener;

    public void setListClickListener (tqaListener listener){
        this.listClickListener = listener;

    }


    @NonNull
    @Override
    public tqaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//원하는 layout띄우기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_tqa,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull tqaAdapter.ViewHolder holder, int position) {
//ItemViewHolder가 생성되고 넣어야할 코드들을 넣어줍다.
// 보통 onBind 함수 안에 모두 넣어줍니다.
        holder.onBind(res_list_item.get(position));


    }
    public void setRes_list_item(ArrayList<tqaItem> list){
        this.res_list_item = list;
        notifyDataSetChanged();
    }

    //클릭 이벤트 필요 요소 해당 포지션에 있는 아이템들 가져오기
    public tqaItem getItem(int position){
        return res_list_item.get(position);
    }


    public void onListClick(ViewHolder holder, View view, int position) {
        if(listClickListener != null){
            listClickListener.onListClick(holder,view,position);
        }
    }



    @Override
    public int getItemCount() {
        return res_list_item.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nickname_tv,rating_tv,date_tv,answer_tv,good_tv;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_tv=itemView.findViewById(R.id.tqauser_nickname);
            rating_tv =itemView.findViewById(R.id.tqauser_rating);
            date_tv = itemView.findViewById(R.id.tqauser_date);
            answer_tv = itemView.findViewById(R.id.tqauser_answer);
            good_tv = itemView.findViewById(R.id.tqauser_good);





        }
        void onBind(tqaItem item){

            nickname_tv.setText(item.getTqa_nickname());
            rating_tv.setText(item.getTqa_rating());
            date_tv.setText(item.getTqa_date());
            answer_tv.setText(item.getTqa_answer());
            good_tv.setText(item.getTqa_good());
            answer_tv.setFocusableInTouchMode(false);





        }
    }


}
package com.example.javatq.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatq.Item.uqItem;
import com.example.javatq.Item.uqaItem;
import com.example.javatq.Listener.uqListener;
import com.example.javatq.Listener.uqaListener;
import com.example.javatq.R;

import java.util.ArrayList;

public class uqaAdapter extends RecyclerView.Adapter<uqaAdapter.ViewHolder> implements uqaListener{

    ArrayList<uqaItem> res_list_item = new ArrayList<>();



    //========클릭 이벤트 구현===========
    uqaListener listClickListener;

    public void setListClickListener (uqaListener listener){
        this.listClickListener = listener;

    }


    @NonNull
    @Override
    public uqaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//원하는 layout띄우기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_uqa,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull uqaAdapter.ViewHolder holder, int position) {
//ItemViewHolder가 생성되고 넣어야할 코드들을 넣어줍다.
// 보통 onBind 함수 안에 모두 넣어줍니다.
        holder.onBind(res_list_item.get(position));


    }
    public void setRes_list_item(ArrayList<uqaItem> list){
        this.res_list_item = list;
        notifyDataSetChanged();
    }

    //클릭 이벤트 필요 요소 해당 포지션에 있는 아이템들 가져오기
    public uqaItem getItem(int position){
        return res_list_item.get(position);
    }


    public void onListClick(ViewHolder holder, View view, int position) {
        if(listClickListener != null){
            listClickListener.onListClick(holder,view,position);
        }
    }

    @Override
    public void onReviseClick(ViewHolder holder, View view, int position) {
        if(listClickListener != null){
            listClickListener.onListClick(holder,view,position);
        }
    }

    @Override
    public void onDeleteClick(ViewHolder holder, View view, int position) {
        if(listClickListener != null){
            listClickListener.onListClick(holder,view,position);
        }
    }


    @Override
    public int getItemCount() {
        return res_list_item.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nickname_tv,rating_tv,date_tv,answer_tv;
        private Button revisebtn,deletebtn;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname_tv=itemView.findViewById(R.id.uqa_nickname);
            rating_tv =itemView.findViewById(R.id.uqA_rating);
            date_tv = itemView.findViewById(R.id.uqa_date);
            answer_tv = itemView.findViewById(R.id.uqa_answer);
            deletebtn = itemView.findViewById(R.id.uqa_deleteuqa);


            //아이템 클릭 이벤트 구현
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    System.out.println(position);
                    if(listClickListener != null){
                        listClickListener.onListClick(ViewHolder.this,view,position);
                    }
                }
            });

            //아이템 클릭 이벤트 구현
            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    System.out.println(position);
                    if(listClickListener != null){
                        listClickListener.onDeleteClick(ViewHolder.this,view,position);
                    }
                }
            });


        }
        void onBind(uqaItem item){

            nickname_tv.setText(item.getUqa_nickname());
            rating_tv.setText(item.getUqa_rating());
            date_tv.setText(item.getUqa_date());
            answer_tv.setText(item.getUqa_answer());
            deletebtn.setVisibility(View.GONE);
            answer_tv.setFocusableInTouchMode(false);

            if(item.getCheckmy().equals("1")){ // 1이면 내가 내꺼 수정

                deletebtn.setVisibility(View.VISIBLE);
            }



        }
    }


}
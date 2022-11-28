package com.example.javatq.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatq.Item.uqItem;
import com.example.javatq.Listener.uqListener;
import com.example.javatq.R;

import java.util.ArrayList;

public class uqAdapter extends RecyclerView.Adapter<uqAdapter.ViewHolder> implements uqListener{

    ArrayList<uqItem> res_list_item = new ArrayList<>();



    //========클릭 이벤트 구현===========
    uqListener listClickListener;

    public void setListClickListener (uqListener listener){
        this.listClickListener = listener;

    }


    @NonNull
    @Override
    public uqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//원하는 layout띄우기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_user_quenstion,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull uqAdapter.ViewHolder holder, int position) {
//ItemViewHolder가 생성되고 넣어야할 코드들을 넣어줍다.
// 보통 onBind 함수 안에 모두 넣어줍니다.
        holder.onBind(res_list_item.get(position));


    }
    public void setRes_list_item(ArrayList<uqItem> list){
        this.res_list_item = list;
        notifyDataSetChanged();
    }

    //클릭 이벤트 필요 요소 해당 포지션에 있는 아이템들 가져오기
    public uqItem getItem(int position){
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
        TextView title_tv,wonderint_tv,date_tv,writter_tv,wonderText_tv;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById((R.id.uq_quenstion_title));
            wonderint_tv = itemView.findViewById((R.id.uq_wondernum));
            date_tv = itemView.findViewById(R.id.uq_qtdate);
            writter_tv = itemView.findViewById(R.id.uq_qtwriter);
            wonderText_tv = itemView.findViewById(R.id.uq_wonderText);


            //아이템 클릭 이벤트 구현
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    if(listClickListener != null){
                        listClickListener.onListClick(ViewHolder.this,view,position);
                    }
                }
            });


        }
        void onBind(uqItem item){
            title_tv.setText(item.getTitle());
            wonderint_tv.setText(item.getWonderInt());
            date_tv.setText(item.getDate());
            writter_tv.setText(item.getWritter());
            wonderText_tv.setText(item.getWonderText());


        }
    }


}
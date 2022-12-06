package com.example.javatq.Listener;

import android.view.View;

import com.example.javatq.Adapter.tqaAdapter;
import com.example.javatq.Adapter.uqAdapter;

public  interface tqaListener {
    public void onListClick(tqaAdapter.ViewHolder holder, View view, int position);
}

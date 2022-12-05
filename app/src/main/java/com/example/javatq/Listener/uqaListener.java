package com.example.javatq.Listener;

import android.view.View;

import com.example.javatq.Adapter.uqAdapter;
import com.example.javatq.Adapter.uqaAdapter;

public  interface uqaListener {
    public void onListClick(uqaAdapter.ViewHolder holder, View view, int position);
    public void onReviseClick(uqaAdapter.ViewHolder holder, View view, int position);
    public void onDeleteClick(uqaAdapter.ViewHolder holder, View view, int position);
}

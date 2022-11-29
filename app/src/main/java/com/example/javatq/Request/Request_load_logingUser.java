package com.example.javatq.Request;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Request_load_logingUser extends StringRequest {
    //서버 url 설정(php파일 연동)
    //final static  private String URL="http://192.168.75.151:9090/load_user_reservation.php";
    final static  private String URL="http://192.168.75.106:9090/tq_load_user.php";

    private Map<String,String> map;

    public Request_load_logingUser(String id,String pw,Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("user_id",id);
        map.put("user_pw",pw);



        Log.v("작동하냐여기","리퀘스트");


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
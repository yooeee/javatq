package com.example.javatq.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Request_delete_uqa extends StringRequest {
    //서버 url 설정(php파일 연동)
    //final static  private String URL="http://192.168.75.151:9090/load_user_reservation.php";
    final static  private String URL="http://192.168.75.106:9090/tq_delete_uqa.php";

    private Map<String,String> map;

    public Request_delete_uqa(String id, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("uqa_id",id);

        System.out.println("여기는 uqainfo"+id);






    }

    @Override
    protected Map<String, String> getParams()  {
        return map;
    }
}
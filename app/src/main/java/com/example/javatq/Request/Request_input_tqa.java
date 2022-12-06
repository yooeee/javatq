package com.example.javatq.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Request_input_tqa extends StringRequest {
    //서버 url 설정(php파일 연동)
    //final static  private String URL="http://192.168.75.151:9090/load_user_reservation.php";
    final static  private String URL="http://192.168.75.106:9090/tq_input_tqaa.php";

    private Map<String,String> map;

    public Request_input_tqa(String tq_id,String name,String rating,String date,String answer, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);
        map=new HashMap<>();
        map.put("tq_id",tq_id);
        map.put("user_nickname",name);
        map.put("user_rating",rating);
        map.put("tqa_date",date);
        map.put("tqa_answer",answer);






    }

    @Override
    protected Map<String, String> getParams()  {
        return map;
    }
}
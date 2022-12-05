package com.example.javatq.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Request_input_uqa extends StringRequest {
    //서버 url 설정(php파일 연동)
    //final static  private String URL="http://192.168.75.151:9090/load_user_reservation.php";
    final static  private String URL="http://192.168.75.106:9090/tq_input_uqa.php";

    private Map<String,String> map;

    public Request_input_uqa(String user_id,String uq_id,String nickname,String rating,String text,String date,String time, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("user_id",user_id);
        map.put("uq_id",uq_id);
        map.put("user_nickname",nickname);
        map.put("user_rating",rating);
        map.put("uqa_answer",text);
        map.put("uqa_date",date);
        map.put("uqa_time",time);







    }

    @Override
    protected Map<String, String> getParams()  {
        return map;
    }
}
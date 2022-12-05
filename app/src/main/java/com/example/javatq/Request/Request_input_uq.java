package com.example.javatq.Request;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Request_input_uq extends StringRequest {
    //서버 url 설정(php파일 연동)
    //final static  private String URL="http://192.168.75.151:9090/load_user_reservation.php";
    final static  private String URL="http://192.168.75.106:9090/tq_input_uq.php";

    private Map<String,String> map;

    public Request_input_uq(String rating, String nickname, String qt, String q, String date, String time,
                            String ing_subfv, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("uq_qt","Q."+qt);
        map.put("uq_q",q);
        map.put("user_nickname",nickname);
        map.put("user_rating",rating);
        map.put("uq_date",date);
        map.put("uq_time",time);
        map.put("ing_subfv",ing_subfv);







    }

    @Override
    protected Map<String, String> getParams()  {
        return map;
    }
}
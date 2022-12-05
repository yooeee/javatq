package com.example.javatq.Item;

public class uqaItem {
    String uqa_id,uqa_nickname,uqa_rating,uqa_date,uqa_answer,checkmy;

    public uqaItem(String uqa_id, String uqa_nickname, String uqa_rating, String uqa_date, String uqa_answer,String checkmy) {
        this.uqa_id = uqa_id;
        this.uqa_nickname = uqa_nickname;
        this.uqa_rating = uqa_rating;
        this.uqa_date = uqa_date;
        this.uqa_answer = uqa_answer;
        this.checkmy = checkmy;
    }

    public String getCheckmy() {
        return checkmy;
    }

    public void setCheckmy(String checkmy) {
        this.checkmy = checkmy;
    }

    public String getUqa_id() {
        return uqa_id;
    }

    public void setUqa_id(String uqa_id) {
        this.uqa_id = uqa_id;
    }

    public String getUqa_nickname() {
        return uqa_nickname;
    }

    public void setUqa_nickname(String uqa_nickname) {
        this.uqa_nickname = uqa_nickname;
    }

    public String getUqa_rating() {
        return uqa_rating;
    }

    public void setUqa_rating(String uqa_rating) {
        this.uqa_rating = uqa_rating;
    }

    public String getUqa_date() {
        return uqa_date;
    }

    public void setUqa_date(String uqa_date) {
        this.uqa_date = uqa_date;
    }

    public String getUqa_answer() {
        return uqa_answer;
    }

    public void setUqa_answer(String uqa_answer) {
        this.uqa_answer = uqa_answer;
    }
}

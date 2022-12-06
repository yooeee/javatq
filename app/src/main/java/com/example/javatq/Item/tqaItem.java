package com.example.javatq.Item;

public class tqaItem {
    String tqa_nickname , tqa_rating , tqa_date, tqa_good,tqa_answer;

    public String getTqa_nickname() {
        return tqa_nickname;
    }

    public void setTqa_nickname(String tqa_nickname) {
        this.tqa_nickname = tqa_nickname;
    }

    public String getTqa_rating() {
        return tqa_rating;
    }

    public void setTqa_rating(String tqa_rating) {
        this.tqa_rating = tqa_rating;
    }

    public String getTqa_date() {
        return tqa_date;
    }

    public void setTqa_date(String tqa_date) {
        this.tqa_date = tqa_date;
    }

    public String getTqa_good() {
        return tqa_good;
    }

    public void setTqa_good(String tqa_good) {
        this.tqa_good = tqa_good;
    }

    public String getTqa_answer() {
        return tqa_answer;
    }

    public void setTqa_answer(String tqa_answer) {
        this.tqa_answer = tqa_answer;
    }

    public tqaItem(String tqa_nickname, String tqa_rating, String tqa_date, String tqa_good, String tqa_answer) {
        this.tqa_nickname = tqa_nickname;
        this.tqa_rating = tqa_rating;
        this.tqa_date = tqa_date;
        this.tqa_good = tqa_good;
        this.tqa_answer = tqa_answer;
    }
}

package com.example.javatq.Item;

public class uqItem {
    String title,wonderText,wonderInt,date,writter;




    public uqItem(String title,String wonderText,String wonderInt,String date,String writter){
        this.title = title;
        this.wonderInt =wonderInt;
        this.wonderText = wonderText;
        this.date=date;
        this.writter=writter;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWonderText() {
        return wonderText;
    }

    public void setWonderText(String wonderText) {
        this.wonderText = wonderText;
    }

    public String getWonderInt() {
        return wonderInt;
    }

    public void setWonderInt(String wonderInt) {
        this.wonderInt = wonderInt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }
}

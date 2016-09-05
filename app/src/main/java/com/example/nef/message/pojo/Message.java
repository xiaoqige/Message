package com.example.nef.message.pojo;

import com.example.nef.message.R;

/**
 * Created by nef on 2016/9/3.
 */
public class Message {
    private int img;
    private String content;
    private  int collect;

    public Message(String content) {
        img= R.mipmap.a;
        this.content = content;
        collect=R.mipmap.recommend;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }
}

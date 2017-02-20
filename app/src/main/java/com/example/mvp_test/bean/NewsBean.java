package com.example.mvp_test.bean;

/**
 * @author 曾晖 17.02.15
 * News Bean
 */
public class NewsBean {
    private int id;
    private String title;
    private String result;
    public int getId() {
        return id;
    }

    public NewsBean(int id, String title, String result) {
        this.id = id;
        this.title = title;
        this.result = result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

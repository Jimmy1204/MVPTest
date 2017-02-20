package com.example.mvp_test.model;

import com.example.mvp_test.bean.NewsBean;

/**
 * Created by 曾晖 on 2017/2/15.
 */
public interface INewsModel {
    public int getID();
    public String getTitle();
    public String getResult();
    public NewsBean loadNews(int id);
    public NewsBean loadNews(String Title);
}

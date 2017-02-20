package com.example.mvp_test.model;

import com.example.mvp_test.Util.GetNewsUtil;
import com.example.mvp_test.bean.NewsBean;

/**
 * Created by 曾晖 on 2017/2/15.
 */
public class NewsModel implements INewsModel{
    private int id;
    private String title;
    private String result;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public NewsBean loadNews(int id) {
        if(GetNewsUtil.getNews(id)!=null)
            return GetNewsUtil.getNews(id);
        return null;
    }

    @Override
    public NewsBean loadNews(String title) {
        if(GetNewsUtil.getNews(title)!=null)
            return GetNewsUtil.getNews(title);
        return null;
    }
}

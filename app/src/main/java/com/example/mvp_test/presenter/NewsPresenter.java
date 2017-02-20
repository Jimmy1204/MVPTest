package com.example.mvp_test.presenter;

import android.util.Log;

import com.example.mvp_test.bean.NewsBean;
import com.example.mvp_test.model.INewsModel;
import com.example.mvp_test.model.NewsModel;
import com.example.mvp_test.view.UI.INewsView;

/**
 * Created by 曾晖 on 2017/2/15.
 */
public class NewsPresenter {
    private INewsModel newsModel;
    private INewsView newsView;

    public NewsPresenter(INewsView newsView) {
        this.newsModel = new NewsModel();
        this.newsView = newsView;
    }

    public void initData(String result){
        newsView.setResult(result);
    }

    public void loadNews(int id){
        NewsBean news = newsModel.loadNews(id);
        Log.i("#####",news.getResult());
        newsView.setID(news.getId());
        newsView.setResult(news.getResult());
        newsView.setNewsTitle(news.getTitle());
    }

    public void loadNews(String title){
        NewsBean news = newsModel.loadNews(title);
        newsView.setID(news.getId());
        newsView.setResult(news.getResult());
        newsView.setNewsTitle(news.getTitle());
    }

}

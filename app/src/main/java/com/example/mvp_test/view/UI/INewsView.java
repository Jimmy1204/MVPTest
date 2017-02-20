package com.example.mvp_test.view.UI;

/**
 * Created by 曾晖 on 2017/2/15.
 */
public interface INewsView {
    public int getID();
    public String getNewsTitle();
    public String getResult();
    public void setResult(String result);
    public void setID(int id);
    public void setNewsTitle(String title);

}

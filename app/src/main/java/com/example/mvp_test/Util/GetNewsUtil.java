package com.example.mvp_test.Util;

import android.util.Log;
import com.example.mvp_test.bean.NewsBean;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;


public class GetNewsUtil {
    /**
     * 解析newsModel的json
     */
    public static NewsBean getNews(int id) {
        String title = "";
        String res = "";
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        JSONObject jsonObject = JsonRequestUtil.asyncPost(JsonRequestUtil.getServerRequestUrl("/news/mnews_getNewsByPage"), map);

        try {
            if (jsonObject.getInt("hasData") == 0) {
                Log.i("hasData", "no data");
                return null;
            }
            JSONArray newsArray = jsonObject.getJSONArray("newsList");
            Log.i("newsModel", newsArray.toString());
            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject newsObject = (JSONObject) newsArray.get(i);
                int newsId = newsObject.getInt("newsId");
                if (newsId == id) {
                    if (newsObject.getString("title") != null)
                        title = newsObject.getString("title");
                    else
                        title = "未命名新闻";
                    if (newsObject.getString("content") != null)
                        res = newsObject.getString("content") + "    " + newsObject.getInt("clickNum");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(title.equals(""))
            return null;
        return new NewsBean(id,title,res);
    }

    /**
     * 解析newsModel的json
     */
    public static NewsBean getNews(String title) {
        int id = 0;
        String res = "";
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        JSONObject jsonObject = JsonRequestUtil.asyncPost(JsonRequestUtil.getServerRequestUrl("/news/mnews_getNewsByPage"), map);

        try {
            if (jsonObject.getInt("hasData") == 0) {
                Log.i("hasData", "no data");
                return null;
            }
            JSONArray newsArray = jsonObject.getJSONArray("newsList");
            Log.i("newsModel", newsArray.toString());
            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject newsObject = (JSONObject) newsArray.get(i);
                String newsTitle = newsObject.getString("title");
                if (newsTitle.equals(title)) {
                        id = newsObject.getInt("newsId");
                    if (newsObject.getString("content") != null)
                        res = newsObject.getString("content") + "    "+ newsObject.getInt("clickNum");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(id == 0)
            return null;
        return new NewsBean(id,title,res);
    }
}

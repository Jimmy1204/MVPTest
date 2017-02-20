package com.example.mvp_test.view.UI;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mvp_test.R;
import com.example.mvp_test.Util.JsonRequestUtil;
import com.example.mvp_test.Util.ToastUtil;
import com.example.mvp_test.presenter.NewsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements INewsView {

    public static String TAG = "MainActivity";

    @BindView(R.id.id)
    EditText mID;
    @BindView(R.id.title1)
    EditText mTitle;

    @BindView(R.id.result)
    TextView mRes;
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.get)
    Button mGet;
    private NewsPresenter newsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButterKnife.bind(this);
        getFirstContent();

    }

    public void initView() {
        newsPresenter = new NewsPresenter(this);

    }

    @Override
    public int getID() {
        try {
            return Integer.parseInt(mID.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String getNewsTitle() {
        return mTitle.getText().toString();
    }

    @Override
    public String getResult() {
        return mRes.getText().toString();
    }

    @Override
    public void setResult(String result) {
        mRes.setText(result);
    }

    @Override
    public void setID(int id) {
        mID.setText(id + "");
    }

    @Override
    public void setNewsTitle(String title) {
        mTitle.setText(title);
    }


    @OnClick(R.id.get)
    public void onClick() {
        if (mID.getText().toString().equals("") && mTitle.getText().toString().equals("")) {
            Snackbar.make(mLinear, "请输入ID或Title", Snackbar.LENGTH_SHORT).show();
        } else if (mID.getText() != null) {
            newsPresenter.loadNews(getID());
        } else {
            newsPresenter.loadNews(getNewsTitle());
        }
    }

    public void getFirstContent() {

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

//        // Instantiate the cache
//        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
//
//        // Set up the network to use HttpURLConnection as the HTTP client.
//        Network network = new BasicNetwork(new HurlStack());
//
//        // Instantiate the RequestQueue with the cache and network.
//        mRequestQueue = new RequestQueue(cache, network);

//        // Start the queue
//        mRequestQueue.start();
        /**
         * 字符串请求
         */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JsonRequestUtil.IP + "/news/mnews_getNewsByPage?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                newsPresenter.initData(s);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showMessage(getApplicationContext(), "请求出错！" + volleyError.getMessage());
            }
        });
        mRequestQueue.add(stringRequest);

//        ValueAnimator valueAnimator = ValueAnimator.ofInt(1,10);
//        valueAnimator.setDuration(1000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                Log.i(TAG, "onAnimationUpdate: "+valueAnimator.getAnimatedValue());
//            }
//        });
////        valueAnimator.setRepeatCount(10);
////        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        valueAnimator.start();
        ObjectAnimator anim = ObjectAnimator.ofFloat(mRes,"rotation",0,360);
        anim.setDuration(3000);
        anim.start();
        ValueAnimator colorAnim = ObjectAnimator.ofInt(mRes,"backgroundColor",0xFFFF8080,0xFF8080FF);
        colorAnim.setDuration(2000);
        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_my);
        mGet.startAnimation(animation);
        ObjectAnimator myAnim = ObjectAnimator.ofInt(mTitle,"backgroundColor",0xffffffff,0xaaaaaaaa);
        myAnim.setDuration(2000);
        myAnim.setEvaluator(new ArgbEvaluator());
        myAnim.start();


//        /**
//         * JSON 请求
//         */
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JsonRequestUtil.IP + "/news/mnews_getNewsByPage?page=1", new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                newsPresenter.initData(response.toString());
//            }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                ToastUtil.showMessage(getApplicationContext(), "请求出错！" + volleyError.getMessage());
//            }
//        });
//        mRequestQueue.add(jsonArrayRequest);
    }
}

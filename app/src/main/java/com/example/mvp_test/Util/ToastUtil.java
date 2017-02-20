package com.example.mvp_test.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 曾晖 on 2017/2/19.
 */

public class ToastUtil {
    public static void showMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}

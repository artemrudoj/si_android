package com.wintersportcoaches.common.utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by artem on 23.05.16.
 */
public class UIUtils {
    public static void requestFocus(View view, Activity appCompatActivity) {
        if (view.requestFocus()) {
            appCompatActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static String getPhoneNumberFromFormattedNumber(String number) {
        if (number == null || number.equals("")) {
            return "";
        }
        return number.replaceAll("\\D+","").substring(1);
    }

    public static String formatingPhoneNumber(String number) {
        if (( number == null ) || number.equals("") )
            return "";
        return "+7 (" + number.substring(0,3) + ")" + " " + number.substring(3,6) + " " +
                number.substring(6,8) + " " + number.substring(8,10);
    }
}

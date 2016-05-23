package com.wintersportcoaches.common.utils;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by artem on 23.05.16.
 */
public class ValidationUtils {
    public static boolean validateForPhoneNumber(EditText editText, TextInputLayout phoneTextInputLayout, int cannot_be_empty, Activity activity) {
        if(activity == null) {
            return false;
        }
        String number = UIUtils.getPhoneNumberFromFormattedNumber(editText.getText().toString());
        if (number.length() != 10) {
            phoneTextInputLayout.setError(activity.getString(cannot_be_empty));
            UIUtils.requestFocus(editText, activity);
            return false;
        } else {
            phoneTextInputLayout.setError(null);
            return true;
        }
    }
}

package com.wintersportcoaches.common.utils;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.artem.common.R;

/**
 * Created by artem on 23.05.16.
 */
public class ValidationUtils {

    final static int MIN_PASSWORD_LENGTH = 5;
    public static boolean validateForPhoneNumber(EditText editText, TextInputLayout phoneTextInputLayout, int cannot_be_empty, Activity activity) {
        if(activity == null) {
            return false;
        }
        String number = Utils.getPhoneNumberFromFormattedNumber(editText.getText().toString());
        if (number.length() != 10) {
            phoneTextInputLayout.setError(activity.getString(cannot_be_empty));
            Utils.requestFocus(editText, activity);
            return false;
        } else {
            phoneTextInputLayout.setError(null);
            return true;
        }
    }

    public static boolean validateForEmptytyEditText(EditText editText, TextInputLayout textInputLayout, int id,
                                                     Activity appCompatActivity) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(appCompatActivity.getString(id));
            Utils.requestFocus(editText, appCompatActivity);
            return false;
        } else {
            textInputLayout.setError(null);
        }

        return true;
    }

    public static boolean validateEmailFormat(EditText editText, TextInputLayout textInputLayout, int id,
                                              Activity appCompatActivity) {
        if(validateEmail(editText.getText().toString())) {
            textInputLayout.setError(null);
            return true;
        } else {
            textInputLayout.setError(appCompatActivity.getString(id));
            Utils.requestFocus(editText, appCompatActivity);
            return false;
        }
    }

    static boolean  validateEmail(String string) {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches()) {
            int dotePosition = string.lastIndexOf(".");
            String dottedSubString = string.substring(dotePosition);
            if(dottedSubString.length() > 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateForPasswords(EditText passwordEditText,
                                               EditText repeatePasswordEditText,
                                               TextInputLayout passwordTextInputLayout,
                                               TextInputLayout repeatePasswordTextInputLayout,
                                               int textId, Activity appCompatActivity) {
        if(passwordEditText.getText().length() < MIN_PASSWORD_LENGTH) {
            passwordTextInputLayout.setError(appCompatActivity.getString(R.string.password_requirements));
            return false;
        }
        if(passwordEditText.getText().toString().equals(repeatePasswordEditText.getText().toString())) {
            passwordTextInputLayout.setError(null);
            repeatePasswordTextInputLayout.setError(null);
            return true;
        } else {
            passwordTextInputLayout.setError(appCompatActivity.getString(textId));
            repeatePasswordTextInputLayout.setError(appCompatActivity.getString(textId));
            Utils.requestFocus(repeatePasswordEditText, appCompatActivity);
            Utils.requestFocus(passwordEditText, appCompatActivity);

            return false;
        }
    }


}

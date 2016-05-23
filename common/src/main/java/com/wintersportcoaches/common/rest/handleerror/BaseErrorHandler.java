package com.wintersportcoaches.common.rest.handleerror;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by artem on 23.02.16.
 */
public class BaseErrorHandler {
    public static void noInternetConnectionHandler(Context context) {
        Toast.makeText(context, "Проверьте ваше соединение с интернетом", Toast.LENGTH_LONG).show();
    }

    public static void incorrectPasswordOrPhone(Context context){
        Toast.makeText(context, "Неправильный логин или пароль", Toast.LENGTH_LONG).show();
    }
}

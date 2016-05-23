package com.wintersportcoaches.common.rest.handleerror;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by artem on 23.02.16.
 */
public abstract class RetrofitBaseCallback<T> implements Callback<T> {


    @Override
    public abstract void onResponse(Call<T> call, Response<T> response);

    @Override
    public  void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        if (!(t instanceof IOException))
            throw new RuntimeException(t);
    }


}

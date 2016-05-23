package com.wintersportcoaches.common.rest.handleerror;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by artem on 15.04.16.
 */
public abstract class RetrofitErrorHandlerWithStubs<T> extends BaseRetrofitErrorHandler<T> {



    public RetrofitErrorHandlerWithStubs() {
        super();
    }

    @Override
    protected void success(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected void handeExitFromCarWithActiveOrders(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleItIsNotYourCard(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleHttpWrongRequestFormat(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleDoesNotExist(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected   void handleMaxCarsLimit(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected   void handleMaxOrdersLimit(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleShouldHaveStatusRegistred(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleHaveNoCar(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleItIsNotYourObject(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected   void handleWrongSession(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleWrongSmsCode(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected void handleHttpLate(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleServerInternalError(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleItIsCurrentlyPerformed(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleOrderStatusError(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected   void handleNotPaid(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleShouldHaveDriver(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleHttpError(Call<T> call, Response<T> response) {
        stub(response);
    }

    @Override
    protected  void handleHttpYourTypeOfUserCannotDoThis(Call<T> call, Response<T> response) {
        stub(response);
    }

    String getErrorString(Response response) {
        return Integer.toString(response.code()) + " " + " - this code was not handled in " + response.raw().request().url().toString();
    }

    void stub(Response response){
        if(200 <= response.code() && response.code() <= 300) {
            Log.i(this.getClass().getName(), "stub: " + getErrorString(response));
        } else if (response.code() >= 500) {
            Log.e(this.getClass().getName(), "stub: problems with server");
        } else
            throw new UnsupportedOperationException(getErrorString(response));
    }
}

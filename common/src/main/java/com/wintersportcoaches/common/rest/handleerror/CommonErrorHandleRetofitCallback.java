package com.wintersportcoaches.common.rest.handleerror;


import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by artem on 15.04.16.
 */
public abstract class CommonErrorHandleRetofitCallback<T> extends RetrofitErrorHandlerWithStubs<T> {

    //DownloadingAnimationHandler animationHandler;

    @Override
    public void onFailure(Call<T> call, Throwable t) {
//        if(animationHandler != null)
//            animationHandler.onEndAnimation();
        super.onFailure(call, t);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
//        if(animationHandler != null)
//            animationHandler.onEndAnimation();
        super.onResponse(call, response);
    }

//    public CommonErrorHandleRetofitCallback(Context context, DownloadingAnimationHandler animationHandler) {
//        super(context);
//        this.animationHandler = animationHandler;
//        if(animationHandler != null)
//            animationHandler.onStartAnimation();
//    }

    public CommonErrorHandleRetofitCallback() {
        super();
  //      animationHandler = null;
    }



    @Override
    protected void handleWrongSession(Call<T> call, Response<T> response) {
        super.handleWrongSession(call, response);
    }

    @Override
    protected void handleHttpYourTypeOfUserCannotDoThis(Call<T> call, Response<T> response) {
        super.handleHttpYourTypeOfUserCannotDoThis(call, response);
    }

    @Override
    protected void handleMaxOrdersLimit(Call<T> call, Response<T> response) {
        super.handleMaxOrdersLimit(call, response);
    }

    @Override
    protected void handleShouldHaveStatusRegistred(Call<T> call, Response<T> response) {
        super.handleShouldHaveStatusRegistred(call, response);
    }

    @Override
    protected void handleHaveNoCar(Call<T> call, Response<T> response) {
        super.handleHaveNoCar(call, response);
    }

    @Override
    protected void handleHttpWrongRequestFormat(Call<T> call, Response<T> response) {
        super.handleHttpWrongRequestFormat(call, response);
    }
}

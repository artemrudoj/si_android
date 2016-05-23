package com.wintersportcoaches.common.rest.handleerror;


import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by artem on 15.04.16.
 */
public abstract class BaseRetrofitErrorHandler<T> extends RetrofitBaseCallback<T> {


    public BaseRetrofitErrorHandler() {
        super();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        switch (response.code()) {
            case HttpStatuses.SUCCESS:
                success(call, response);
                break;
            case HttpStatuses.HTTP_DOES_NOT_EXIST:
                handleDoesNotExist(call, response);
                break;
            case HttpStatuses.HTTP_MAX_CARS_LIMIT:
                handleMaxCarsLimit(call, response);
                break;
            case HttpStatuses.HTTP_MAX_ORDERS_LIMIT:
                handleMaxOrdersLimit(call, response);
                break;
            case HttpStatuses.HTTP_SHOULD_HAVE_STATUS_REGISTERED:
                handleShouldHaveStatusRegistred(call, response);
                break;
            case HttpStatuses.HTTP_HAVE_NO_CAR:
                handleHaveNoCar(call, response);
                break;
            case HttpStatuses.HTTP_IT_IS_NOT_YOUR_OBJECT:
                handleItIsNotYourObject(call, response);
                break;
            case HttpStatuses.HTTP_WRONG_SESSION:
                handleWrongSession(call, response);
                break;
            case HttpStatuses.HTTP_WRONG_SMS_CODE:
                handleWrongSmsCode(call, response);
                break;
            case HttpStatuses.HTTP_LATE:
                handleHttpLate(call, response);
                break;
            case HttpStatuses.SERVER_INTERNAL_ERROR:
                handleServerInternalError(call, response);
                break;
            case HttpStatuses.HTTP_IT_IS_CURRENTLY_PERFORMED:
                handleItIsCurrentlyPerformed(call, response);
                break;
            case HttpStatuses.HTTP_ORDER_STATUS_ERROR:
                handleOrderStatusError(call, response);
                break;
            case HttpStatuses.HTTP_NOT_PAID:
                handleNotPaid(call, response);
                break;
            case HttpStatuses.HTTP_ERROR:
                handleHttpError(call, response);
                break;
            case HttpStatuses.HTTP_YOUR_TYPE_OF_USER_CANNOT_DO_THIS:
                handleHttpYourTypeOfUserCannotDoThis(call, response);
                break;
            case HttpStatuses.HTTP_WRONG_REQUEST_FORMAT:
                handleHttpWrongRequestFormat(call, response);
                break;
            case HttpStatuses.HTTP_SHOULD_HAVE_DRIVER:
                handleShouldHaveDriver(call, response);
                break;
            case HttpStatuses.HTTP_IT_IS_NOT_YOUR_CARD:
                handleItIsNotYourCard(call, response);
                break;
            case HttpStatuses.HTTP_EXIT_FROM_CAR_WITH_ACTIVE_ORDERS:
                handeExitFromCarWithActiveOrders(call, response);
                break;

        }
    }



    abstract void success(Call<T> call, Response<T> response);
    abstract void handeExitFromCarWithActiveOrders(Call<T> call, Response<T> response);
    abstract void handleItIsNotYourCard(Call<T> call, Response<T> response);
    abstract void handleHttpWrongRequestFormat(Call<T> call, Response<T> response);
    abstract void handleDoesNotExist(Call<T> call, Response<T> response);
    abstract void handleMaxCarsLimit(Call<T> call, Response<T> response);
    abstract void handleMaxOrdersLimit(Call<T> call, Response<T> response);
    abstract void handleShouldHaveStatusRegistred(Call<T> call, Response<T> response);
    abstract void handleHaveNoCar(Call<T> call, Response<T> response);
    abstract void handleItIsNotYourObject(Call<T> call, Response<T> response);
    abstract void handleWrongSession(Call<T> call, Response<T> response);
    abstract void handleWrongSmsCode(Call<T> call, Response<T> response);
    abstract void handleHttpLate(Call<T> call, Response<T> response);
    abstract void handleServerInternalError(Call<T> call, Response<T> response);
    abstract void handleItIsCurrentlyPerformed(Call<T> call, Response<T> response);
    abstract void handleOrderStatusError(Call<T> call, Response<T> response);
    abstract void handleNotPaid(Call<T> call, Response<T> response);
    abstract void handleShouldHaveDriver(Call<T> call, Response<T> response);
    abstract void handleHttpError(Call<T> call, Response<T> response);
    abstract void handleHttpYourTypeOfUserCannotDoThis(Call<T> call, Response<T> response);

}

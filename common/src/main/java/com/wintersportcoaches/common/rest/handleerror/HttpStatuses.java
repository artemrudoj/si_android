package com.wintersportcoaches.common.rest.handleerror;

public class HttpStatuses {
    static public final  int ERROR = -1;
    static public final  int NO_INTERNET_CONNECTION = -2;
    static public final  int SUCCESS = 200;
    static public final  int HTTP_IT_IS_CURRENTLY_PERFORMED = 208;
    static public final  int HTTP_ERROR = 400;
    static public final  int HTTP_WRONG_SESSION = 401;
    static public final  int HTTP_DOES_NOT_EXIST = 404;
    static public final  int HTTP_YOUR_TYPE_OF_USER_CANNOT_DO_THIS = 405;
    static public final  int HTTP_LATE = 409;
    static public final  int HTTP_MAX_CARS_LIMIT = 461;
    static public final  int  HTTP_MAX_ORDERS_LIMIT = 462;
    static public final  int HTTP_SHOULD_HAVE_STATUS_REGISTERED = 463;
    static public final  int  HTTP_HAVE_NO_CAR = 465;
    static public final  int HTTP_ORDER_STATUS_ERROR = 467;
    static public final  int HTTP_IT_IS_NOT_YOUR_OBJECT = 468;
    static public final  int HTTP_WRONG_SMS_CODE = 469;
    static public final  int HTTP_WRONG_REQUEST_FORMAT  = 470;
    static public final  int HTTP_IT_IS_NOT_YOUR_CARD = 471;
    static public final  int HTTP_NOT_PAID = 472;
    static public final  int HTTP_EXIT_FROM_CAR_WITH_ACTIVE_ORDERS = 473;
    static public final  int HTTP_SHOULD_HAVE_DRIVER = 474;
    static public final  int SERVER_INTERNAL_ERROR = 502;
}

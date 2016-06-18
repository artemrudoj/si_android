package com.wintersportcoaches.common.login;



/**
 * Created by artem on 23.05.16.
 */
public interface LoginView {
    boolean validatePhoneNumber();
    boolean validatePassword();
    String getPassword();
    String getPhoneNumber();
    void goToRegistrationActivity();
    void goToForgotPasswordActivity();
    void showLoading();

    void successLogin();

}

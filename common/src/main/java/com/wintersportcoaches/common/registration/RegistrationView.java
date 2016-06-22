package com.wintersportcoaches.common.registration;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wintersportcoaches.common.base.BaseFragment;

import java.net.URI;

/**
 * Created by artem on 18.06.16.
 */
public interface RegistrationView  {

    Boolean isCoach();

    String getEmail();

    String getLastName();

    String getFirstName();

    String getPassword();

    String getPhoneNumber();

    boolean isValid();

    void showLoading();

    void successRegistration();

    Uri getPhotoImageURI();
}

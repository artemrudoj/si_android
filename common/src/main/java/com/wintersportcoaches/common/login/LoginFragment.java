package com.wintersportcoaches.common.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.artem.common.R;
import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.registration.RegistrationActivity;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.service.SocketListenerService;
import com.wintersportcoaches.common.ui.tools.PhoneTextWatcher;
import com.wintersportcoaches.common.utils.Utils;
import com.wintersportcoaches.common.utils.ValidationUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends PresenteredFragment<LoginPresenter> implements LoginView {
    static public final  int FINISH = 1234;

    public LoginFragment() {
        // Required empty public constructor
    }

    EditText phoneEditText;
    EditText passwordEditText;

    TextInputLayout phoneTextInputLayout;
    TextInputLayout passwordTextInputLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        if (savedInstanceState == null) {
            presenter = new LoginPresenter(NetworkServiceFactory.getNetworkService(),
                    WinterSportCoachesApplication.get(getActivity()).getRepository());
            presenter.setModel(((UserActivity)getActivity()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Button loginButton = (Button) view.findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonPressed();
            }
        });
        view.findViewById(R.id.registrate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistrationActivity();
            }
        });
        phoneTextInputLayout = (TextInputLayout)view.findViewById(R.id.phone_et_container);
        passwordTextInputLayout = (TextInputLayout)view.findViewById(R.id.password_et_container);

        phoneEditText = (EditText)view.findViewById(R.id.phone_et);
        phoneEditText.addTextChangedListener(new PhoneTextWatcher(phoneEditText){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                validatePhoneNumber();
            }
        });
        phoneEditText.setRawInputType(InputType.TYPE_CLASS_PHONE);
        passwordEditText = (EditText)view.findViewById(R.id.password_et);
    }

    @Override
    public boolean validatePhoneNumber() {
        return ValidationUtils.validateForPhoneNumber(phoneEditText, phoneTextInputLayout,
                R.string.incorrect_phone_number,  getActivity());
    }

    @Override
    public boolean validatePassword() {
        if (passwordEditText.getText().toString().trim().isEmpty()) {
            passwordTextInputLayout.setError(getString(R.string.incorrect_password));
            Utils.requestFocus(passwordEditText, getActivity());
            return false;
        } else {
            passwordTextInputLayout.setError(null);
            return true;
        }
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return phoneEditText.getText().toString();
    }


    @Override
    public void goToRegistrationActivity() {
        Intent intent = new Intent(getActivity(), RegistrationActivity.class);
        startActivityForResult(intent, FINISH);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode) {
            case FINISH:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void goToForgotPasswordActivity() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void successLogin() {
        SocketListenerService.start(getActivity());
        getActivity().finish();
    }
}

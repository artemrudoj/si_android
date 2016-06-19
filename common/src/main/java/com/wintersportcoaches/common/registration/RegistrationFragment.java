package com.wintersportcoaches.common.registration;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.artem.common.R;
import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.UserActivity;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.tools.PhoneTextWatcher;
import com.wintersportcoaches.common.ui.tools.ValidationTextWatcher;
import com.wintersportcoaches.common.utils.ValidationUtils;

/**
 * Created by artem on 18.06.16.
 */
public class RegistrationFragment extends PresenteredFragment<RegistrationPresenter> implements RegistrationView{
    EditText name_et;
    EditText last_name_et;
    EditText email_et;
    EditText phone_number_et;
    EditText password_et;
    EditText repeate_password_et;


    TextInputLayout nameTextInputLayout;
    TextInputLayout lastNameTextInputLayout;
    TextInputLayout emailTextInputLayout;
    TextInputLayout phoneTextInputLayout;
    TextInputLayout passwordTextInputLayout;
    TextInputLayout repeatPasswordTextInputLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_registraion, container, false);
        if (savedInstanceState == null) {
            presenter = new RegistrationPresenter(NetworkServiceFactory.getNetworkService(),
                    WinterSportCoachesApplication.get(getActivity()).getRepository());
            presenter.setModel(((WinterSportCoachesApplication)getActivity().getApplication()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        nameTextInputLayout = (TextInputLayout)view.findViewById(R.id.name_til);
        lastNameTextInputLayout = (TextInputLayout)view.findViewById(R.id.last_name_til);
        emailTextInputLayout = (TextInputLayout)view.findViewById(R.id.email_til);
        phoneTextInputLayout = (TextInputLayout)view.findViewById(R.id.phone_til);
        passwordTextInputLayout = (TextInputLayout)view.findViewById(R.id.password_til);
        repeatPasswordTextInputLayout = (TextInputLayout)view.findViewById(R.id.repeat_password_til);


        name_et = (EditText)view.findViewById(R.id.name_et);
        last_name_et = (EditText)view.findViewById(R.id.last_name_et);
        email_et = (EditText)view.findViewById(R.id.email_et);
        phone_number_et = (EditText)view.findViewById(R.id.phone_number_et);
        password_et = (EditText)view.findViewById(R.id.password_et);
        repeate_password_et = (EditText)view.findViewById(R.id.repeate_password_et);

        name_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validateName();
            }
        });
        last_name_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validateLastName();
            }
        });
        email_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validateEmail();
            }
        });

        phone_number_et.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        phone_number_et.addTextChangedListener(new PhoneTextWatcher(phone_number_et) {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                validatePhone();
            }
        });

        password_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validatePassword();
            }
        });
        repeate_password_et.addTextChangedListener(new ValidationTextWatcher() {
            @Override
            public void validate() {
                validateRepeatePassword();
            }
        });

        view.findViewById(R.id.registrate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registratinButtonPressed();
            }
        });
    }


    @Override
    public Boolean isCoach() {
        return false;
    }

    @Override
    public String getEmail() {
        return email_et.getText().toString();
    }

    @Override
    public String getLastName() {
        return last_name_et.getText().toString();
    }

    @Override
    public String getFirstName() {
        return name_et.getText().toString();
    }

    @Override
    public String getPassword() {
        return password_et.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return phone_number_et.getText().toString();
    }

    public boolean isValid() {
        boolean isNameValid = validateName();
        boolean isLastNameValid = validateLastName();
        boolean isEmailValid = validateEmail();
        boolean isPhoneValid = validatePhone();
        boolean isPasswordValid = validatePassword();
        boolean isRepeatePasswordValid = validateRepeatePassword();
        return isNameValid &&
                isEmailValid &&
                isLastNameValid &&
                isPhoneValid &&
                isPasswordValid &&
                isRepeatePasswordValid;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void successRegistration() {
        Activity activity = getActivity();
        if(activity != null) {
            activity.finish();
        }
    }

    boolean validateName() {
        return ValidationUtils.validateForEmptytyEditText(name_et, nameTextInputLayout,
                R.string.cannot_be_empty, getActivity());
    }

    boolean validateLastName() {
        return ValidationUtils.validateForEmptytyEditText(last_name_et, lastNameTextInputLayout,
                R.string.cannot_be_empty, getActivity());
    }

    boolean validateEmail() {
        return ValidationUtils.validateEmailFormat(email_et, emailTextInputLayout, R.string.incorrect_email,
                getActivity());

    }

    boolean validatePhone() {
        return ValidationUtils.validateForPhoneNumber(phone_number_et, phoneTextInputLayout,
                R.string.incorrect_phone_number, getActivity());
    }


    boolean validatePassword() {
        return ValidationUtils.validateForPasswords(password_et, repeate_password_et, passwordTextInputLayout,
                repeatPasswordTextInputLayout, R.string.incorrect_password_repeat, getActivity());

    }

    boolean validateRepeatePassword() {
        return ValidationUtils.validateForPasswords(repeate_password_et, password_et, repeatPasswordTextInputLayout,
                passwordTextInputLayout, R.string.incorrect_password_repeat, getActivity());
    }

}

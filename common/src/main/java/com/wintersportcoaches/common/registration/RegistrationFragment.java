package com.wintersportcoaches.common.registration;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.artem.common.R;
import com.soundcloud.android.crop.Crop;
import com.wintersportcoaches.common.WinterSportCoachesApplication;
import com.wintersportcoaches.common.base.presenter.PresenterManager;
import com.wintersportcoaches.common.base.presenter.PresenteredFragment;
import com.wintersportcoaches.common.login.LoginFragment;
import com.wintersportcoaches.common.rest.service.NetworkServiceFactory;
import com.wintersportcoaches.common.ui.tools.PhoneTextWatcher;
import com.wintersportcoaches.common.ui.tools.ValidationTextWatcher;
import com.wintersportcoaches.common.user.BaseUser;
import com.wintersportcoaches.common.utils.CommonUtils;
import com.wintersportcoaches.common.utils.FileUtils;
import com.wintersportcoaches.common.utils.NetworkUtils;
import com.wintersportcoaches.common.utils.ValidationUtils;

import java.io.File;

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
    protected BaseUser user;

    public static final int REQUEST_CAMERA = 17948;
    public static final int SELECT_FILE = 17949;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 147;


    TextInputLayout nameTextInputLayout;
    TextInputLayout lastNameTextInputLayout;
    TextInputLayout emailTextInputLayout;
    TextInputLayout phoneTextInputLayout;
    TextInputLayout passwordTextInputLayout;
    TextInputLayout repeatPasswordTextInputLayout;


    protected ImageView profileImage;
    protected ImageView photoIcon;
    protected ProgressBar photoAnimation;

    protected Uri newProfileImageUri;
    protected Uri tempPhotoUri;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_registraion, container, false);
        if (savedInstanceState == null) {
            presenter = new RegistrationPresenter(NetworkServiceFactory.getNetworkService(),
                    WinterSportCoachesApplication.get(getActivity()).getRepository(), ((WinterSportCoachesApplication)getActivity().getApplication()).getUser());
            presenter.setModel(((WinterSportCoachesApplication)getActivity().getApplication()).getUser());
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        user = ((WinterSportCoachesApplication) getActivity().getApplicationContext()).getUser();
        initViews(view);
        initProfileImage(view);
        return view;
    }

    protected void initProfileImage(View view) {
        photoAnimation = (ProgressBar) view.findViewById(R.id.progressbar);
        profileImage = (ImageView) view.findViewById(R.id.profile_image);
        ViewGroup layout = (ViewGroup) profileImage.getParent();
        photoIcon = (ImageView) layout.findViewById(R.id.photo_icon_iv);
        if (user.isLogin()) {
            if (user.getPhotoLocalUrl() != null && !user.getPhotoLocalUrl().equals("")) {
                profileImage.setImageURI(Uri.parse(user.getPhotoLocalUrl()));
                hidePhotoIcon();
            } else if (user.getPhotoUrl() != null && !user.getPhotoUrl().equals("")) {
                NetworkUtils.loadProfileImage(user.getPhotoUrl(),
                        profileImage, photoIcon, photoAnimation, getActivity());
            }
        }
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
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



    protected void selectImage() {
        //// TODO: 16/02/16 views presentation
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    if (ContextCompat.checkSelfPermission(
                            getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        FragmentCompat.requestPermissions(RegistrationFragment.this,
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION);
                    } else {
                        handleCameraRequest();
                    }
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void handleCameraRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = FileUtils.createEmptyProfileImageFile(getActivity());
        tempPhotoUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempPhotoUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                beginCrop(tempPhotoUri);
            } else if (requestCode == SELECT_FILE) {
                beginCrop(data.getData());
            } else if (requestCode == Crop.REQUEST_CROP) {
                handleCrop(data);
            }
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(),
                "cropped" + System.currentTimeMillis() + ".jpeg"));
        try {
            Crop.of(source, destination).asSquare().start(getActivity(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handleCameraRequest();
                }
                break;
        }
    }

    private void handleCrop(Intent result) {
        Uri uri = Crop.getOutput(result);
        Uri scaledDownImageUri = CommonUtils.normalizeImageForUri(getActivity(), uri);
        profileImage.setImageURI(scaledDownImageUri);
        newProfileImageUri = uri;
        hidePhotoIcon();
    }


    protected void hidePhotoIcon() {
        ViewGroup layout = (ViewGroup) profileImage.getParent();
        layout.findViewById(R.id.photo_icon_iv).setVisibility(View.GONE);
        profileImage.setBackground(null);
    }

    protected void showPhotoIcon() {
        ViewGroup layout = (ViewGroup) profileImage.getParent();
        layout.findViewById(R.id.photo_icon_iv).setVisibility(View.VISIBLE);
    }





    public void setNewProfileImageUri(Bitmap bitmap, String filename) {
        newProfileImageUri = FileUtils.saveToFile(getActivity(), bitmap, filename);
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
            Intent intent = new Intent();
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }

    @Override
    public Uri getPhotoImageURI() {
        return newProfileImageUri;
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

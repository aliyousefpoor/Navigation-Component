package com.example.bottomnavigation.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.utils.ApiBuilder;
import com.example.bottomnavigation.utils.AppConstants;

import retrofit2.Retrofit;


public class LoginStepOneDialogFragment extends DialogFragment {
    private static final String TAG = "FirstDialogFragment";

    TextView title;
    EditText number;
    Button submit;
    private LoginStepTwoDialogFragment loginStepTwoDialogFragment;
    private LoginStepTwoListener loginStepTwoListener;
    private LoginStepOneViewModel loginStepOneViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource = LoginModule.provideLoginRemoteDataSource(apiService);
    private LoginStepOneViewModelFactory loginStepOneViewModelFactory = LoginModule.provideLoginViewModelFactory(loginStepOneRemoteDataSource);
    @SuppressLint("HardwareIds")
    private String androidId;
    private String deviceModel = AppConstants.getDeviceName();
    private String deviceOs = AppConstants.getAndroidVersion();
    private ProgressDialog dialog;
    private ResendCodeListener resendCodeListener;


    public LoginStepOneDialogFragment(LoginStepTwoListener loginStepTwoListener) {
        this.loginStepTwoListener = loginStepTwoListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_step_one_dialog_fragment, container, false);
        androidId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        return view;
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loginStepOneViewModel = new ViewModelProvider(this, loginStepOneViewModelFactory).get(LoginStepOneViewModel.class);

        title = view.findViewById(R.id.title);
        number = view.findViewById(R.id.edit_txt);
        submit = view.findViewById(R.id.submit);
        postRequest();
        resendCode();
        final LoginStepOne loginStepOne = new LoginStepOne();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStepOne.setNumber(number.getText().toString());
                loginStepOne.setAndroidId(androidId);
                loginStepOne.setDeviceModel(deviceModel);
                loginStepOne.setDeviceOs(deviceOs);

                loginStepOneViewModel.loginStepOne(loginStepOne);

                dialog = new ProgressDialog(getContext());
                dialog.setTitle(R.string.progressDialogTitle);
                dialog.setMessage(getString(R.string.loadingProgress));
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

    }

    public void postRequest() {
        loginStepOneViewModel.loginStepOneLiveData.observe(this, new Observer<LoginResponseBody>() {
            @Override
            public void onChanged(LoginResponseBody loginBody) {
                loginStepTwoDialogFragment = new LoginStepTwoDialogFragment(number.getText().toString(),androidId, loginStepTwoListener,resendCodeListener);
                loginStepTwoDialogFragment.show(getParentFragmentManager(), "SecondDialogFragment");
                Log.d(TAG, "onChanged: " + loginBody);
                dismiss();
                dialog.dismiss();

            }
        });
    }
    public void resendCode(){
        final LoginStepOne loginStepOne= new LoginStepOne();
        resendCodeListener=new ResendCodeListener() {
            @Override
            public void onResend(String number) {
                loginStepOne.setNumber(number);
                loginStepOne.setAndroidId(androidId);
                loginStepOne.setDeviceModel(deviceModel);
                loginStepOne.setDeviceOs(deviceOs);
                loginStepOneViewModel.loginStepOne(loginStepOne);
            }
        };
    }

}

package com.example.bottomnavigation.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.data.local.database.LoginAsyncTask;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class LoginStepTwoDialogFragment extends DialogFragment {
    private static final String TAG = "SecondDialogFragment";
    EditText code;
    Button submit, changeNumber;
    TextView resendCode;
    String number;
    private LoginStepTwoViewModel loginStepTwoViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource = LoginModule.provideVerificationRemoteDataSource(apiService);
    private LoginStepTwoViewModelFactory loginStepTwoViewModelFactory = LoginModule.provideVerificationViewModelFactory(loginStepTwoRemoteDataSource);
    private String androidId;
    private LoginStepTwoCodeListener loginStepTwoCodeListener;
    private ProgressDialog dialog;
    private ResendCodeListener resendCodeListener;
    private UserEntity userEntity = new UserEntity();


    public LoginStepTwoDialogFragment(String number, String androidId, LoginStepTwoCodeListener loginStepTwoCodeListener, ResendCodeListener resendCodeListener) {
        this.number = number;
        this.androidId=androidId;
        this.loginStepTwoCodeListener = loginStepTwoCodeListener;
        this.resendCodeListener = resendCodeListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_step_two_dialog_fragment, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginStepTwoViewModel = new ViewModelProvider(this, loginStepTwoViewModelFactory).get(LoginStepTwoViewModel.class);
        code = view.findViewById(R.id.verificationCode);
        submit = view.findViewById(R.id.secondDialogSubmit);
        changeNumber = view.findViewById(R.id.changeNumber);
        resendCode = view.findViewById(R.id.resendCode);
        postVerificationCodeRequest();
        final LoginStepTwo loginStepTwo = new LoginStepTwo();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginStepTwo.setNumber(number);
                loginStepTwo.setAndroidId(androidId);
                loginStepTwo.setCode(code.getText().toString());
                loginStepTwoViewModel.loginStepTwo(loginStepTwo);
                Log.d(TAG, "onClick: " + number);

                dialog = new ProgressDialog(getContext());
                dialog.setTitle(R.string.progressDialogTitle);
                dialog.setMessage(getString(R.string.loadingProgress));
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                LoginStepOneDialogFragment loginStepOneDialogFragment = new LoginStepOneDialogFragment(loginStepTwoCodeListener);
                loginStepOneDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");

            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resendCodeListener.onResend(number);
            }
        });
    }


    public void postVerificationCodeRequest() {
        loginStepTwoViewModel.verificationLiveData.observe(this, new Observer<LoginStepTwoResponseBody>() {

            @Override
            public void onChanged(final LoginStepTwoResponseBody loginStepTwoResponseBody) {


                if (loginStepTwoResponseBody != null) {

                    userEntity.setUserId(loginStepTwoResponseBody.getUserId());
                    userEntity.setToken(loginStepTwoResponseBody.getToken());
                    Log.d(TAG, "onChanged: " + userEntity.getUserId());
                    loginStepTwoCodeListener.onResponse(userEntity);

                    dismiss();
                    dialog.dismiss();

                    loginStepTwoViewModel.userLogin(loginStepTwoResponseBody,getContext());
//                    LoginAsyncTask loginAsyncTask = new LoginAsyncTask(loginStepTwoResponseBody, getContext());
//                    loginAsyncTask.execute();


                } else {
                    Toast.makeText(getContext(), "enter valid code", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

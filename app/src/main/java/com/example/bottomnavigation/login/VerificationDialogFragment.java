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
import com.example.bottomnavigation.data.remote.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.moretab.profilefragment.LoginAsyncTask;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class VerificationDialogFragment extends DialogFragment {
    private static final String TAG = "SecondDialogFragment";
    EditText code;
    Button submit, changeNumber;
    TextView resendCode;
    String number;
    private VerificationViewModel verificationViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private VerificationRemoteDataSource verificationRemoteDataSource = LoginModule.provideVerificationRemoteDataSource(apiService);
    private LoginRepository loginRepository = LoginModule.provideVerificationSource(verificationRemoteDataSource);
    private VerificationViewModelFactory verificationViewModelFactory = LoginModule.provideVerificationViewModelFactory(loginRepository);
    private String androidId;
    private VerificationCodeListener verificationCodeListener;
    private ProgressDialog dialog;
    private ResendCodeListener resendCodeListener;


    public VerificationDialogFragment(String number,String androidId, VerificationCodeListener verificationCodeListener,ResendCodeListener resendCodeListener) {
        this.number = number;
        this.androidId=androidId;
        this.verificationCodeListener = verificationCodeListener;
        this.resendCodeListener = resendCodeListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_dialog, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verificationViewModel = new ViewModelProvider(this, verificationViewModelFactory).get(VerificationViewModel.class);
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
                verificationViewModel.loginStepTwo(loginStepTwo);
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
                LoginDialogFragment loginDialogFragment = new LoginDialogFragment(verificationCodeListener);
                loginDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");

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
        verificationViewModel.verificationLiveData.observe(this, new Observer<VerificationResponseBody>() {

            @Override
            public void onChanged(final VerificationResponseBody verificationResponseBody) {


                if (verificationResponseBody != null) {

                    UserEntity user = new UserEntity();
                    user.setUserId(verificationResponseBody.getUserId());
                    user.setToken(verificationResponseBody.getToken());
                    Log.d(TAG, "onChanged: " + user.getUserId());
                    verificationCodeListener.onResponse(user);

                    dismiss();
                    dialog.dismiss();

                    LoginAsyncTask loginAsyncTask = new LoginAsyncTask(verificationResponseBody, getContext());
                    loginAsyncTask.execute();


                } else {
                    Toast.makeText(getContext(), "enter valid code", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

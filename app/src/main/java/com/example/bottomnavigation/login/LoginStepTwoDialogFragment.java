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
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class LoginStepTwoDialogFragment extends DialogFragment {
    private static final String TAG = "SecondDialogFragment";
    EditText code;
    Button submit, changeNumber;
    TextView resendCode;
    private String number;
    private String androidId;
    private String deviceModel;
    private String deviceOs;
    private LoginSharedViewModel loginSharedViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource = LoginModule.provideLoginStepOneRemoteDataSource(apiService);
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource = LoginModule.provideLoginStepTwoRemoteDataSource(apiService);
    private LoginSharedViewModelFactory loginSharedViewModelFactory = LoginModule.provideShareViewModelFactory(loginStepOneRemoteDataSource, loginStepTwoRemoteDataSource);
    private LoginStepTwoListener loginStepTwoListener;
    private ProgressDialog dialog;
    private User user = new User();
    private UserDatabase database = LoginModule.provideUserDatabase();
    private LoginStepOne loginStepOne = new LoginStepOne();


    public LoginStepTwoDialogFragment(LoginStepTwoListener loginStepTwoListener) {
        this.loginStepTwoListener = loginStepTwoListener;
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

        loginSharedViewModel = new ViewModelProvider(requireActivity(), loginSharedViewModelFactory).get(LoginSharedViewModel.class);
        code = view.findViewById(R.id.verificationCode);
        submit = view.findViewById(R.id.secondDialogSubmit);
        changeNumber = view.findViewById(R.id.changeNumber);
        resendCode = view.findViewById(R.id.resendCode);
        loginStepTwoResponse();
        final LoginStepTwo loginStepTwo = new LoginStepTwo();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = loginSharedViewModel.loginStepOneBody.getNumber();
                androidId = loginSharedViewModel.loginStepOneBody.getAndroidId();
                loginStepTwo.setNumber(number);
                loginStepTwo.setAndroidId(androidId);
                loginStepTwo.setCode(code.getText().toString());
                loginSharedViewModel.loginStepTwo(loginStepTwo);
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

                LoginStepOneDialogFragment loginStepOneDialogFragment = new LoginStepOneDialogFragment(loginStepTwoListener);
                loginStepOneDialogFragment.show(getParentFragmentManager(), "LoginStepOneDialogFragment");

            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deviceModel = loginSharedViewModel.loginStepOneBody.getDeviceModel();
                deviceOs = loginSharedViewModel.loginStepOneBody.getDeviceOs();

                loginStepOne.setNumber(number);
                loginStepOne.setAndroidId(androidId);
                loginStepOne.setDeviceModel(deviceModel);
                loginStepOne.setDeviceOs(deviceOs);
                loginSharedViewModel.loginStepOne(loginStepOne);
            }
        });
    }


    public void loginStepTwoResponse() {
        loginSharedViewModel.loginStepTwoLiveData.observe(this, new Observer<LoginStepTwoResponseBody>() {

            @Override
            public void onChanged(final LoginStepTwoResponseBody loginStepTwoResponseBody) {

                if (loginStepTwoResponseBody != null) {

                    user.setUserId(loginStepTwoResponseBody.getUserId());
                    user.setToken(loginStepTwoResponseBody.getToken());
                    Log.d(TAG, "onChanged: " + user.getUserId());
                    loginStepTwoListener.onResponse(user);

                    dismiss();
                    dialog.dismiss();

                    loginSharedViewModel.loginUser(loginStepTwoResponseBody, database);
                } else {
                    Toast.makeText(getContext(), "enter valid code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

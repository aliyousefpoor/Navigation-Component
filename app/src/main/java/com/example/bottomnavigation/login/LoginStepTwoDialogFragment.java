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
import com.example.bottomnavigation.data.model.LoginStepOneRequest;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoRequest;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.LoginRepository;
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
    private UserDatabase database = LoginModule.provideUserDatabase();
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginRepository loginRepository = LoginModule.provideLoginRepository(apiService,database.userDao());
    private LoginSharedViewModelFactory loginSharedViewModelFactory = LoginModule.provideShareViewModelFactory(loginRepository);
    private LoginStepTwoListener loginStepTwoListener;
    private ProgressDialog dialog;
    private User user = new User();
    private LoginStepOneRequest loginStepOneRequest = new LoginStepOneRequest();



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
        final LoginStepTwoRequest loginStepTwoRequest = new LoginStepTwoRequest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number =loginSharedViewModel.loginStepOneBody.getMobile();
                androidId = loginSharedViewModel.loginStepOneBody.getDevice_id();
                loginStepTwoRequest.setMobile(number);
                loginStepTwoRequest.setDevice_id(androidId);
                loginStepTwoRequest.setVerification_code(code.getText().toString());
                loginSharedViewModel.loginStepTwo(loginStepTwoRequest);
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
                loginStepOneDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");

            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceModel =loginSharedViewModel.loginStepOneBody.getDevice_model();
                deviceOs = loginSharedViewModel.loginStepOneBody.getDevice_os();

                loginStepOneRequest.setMobile(number);
                loginStepOneRequest.setDevice_id(androidId);
                loginStepOneRequest.setDevice_model(deviceModel);
                loginStepOneRequest.setDevice_os(deviceOs);

                loginSharedViewModel.loginStepOne(loginStepOneRequest);
            }
        });
    }


    public void loginStepTwoResponse() {
        loginSharedViewModel.loginStepTwoLiveData.observe(this, new Observer<LoginStepTwoResponse>() {

            @Override
            public void onChanged(final LoginStepTwoResponse loginStepTwoResponse) {


                if (loginStepTwoResponse != null) {

                    user.setUserId(loginStepTwoResponse.getUserId());
                    user.setToken(loginStepTwoResponse.getToken());
                    Log.d(TAG, "onChanged: " + user.getUserId());
                    loginStepTwoListener.onResponse(user);

                    dismiss();
                    dialog.dismiss();

                } else {
                    Toast.makeText(getContext(), "enter valid code", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

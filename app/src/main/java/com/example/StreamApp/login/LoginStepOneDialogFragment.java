package com.example.StreamApp.login;

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

import com.example.StreamApp.ApiService;
import com.example.StreamApp.CustomApp;
import com.example.StreamApp.R;
import com.example.StreamApp.data.datasource.local.database.UserDatabase;
import com.example.StreamApp.data.model.LoginStepOneRequest;
import com.example.StreamApp.data.model.LoginStepOneResponse;
import com.example.StreamApp.data.repository.LoginRepository;
import com.example.StreamApp.di.ApiBuilderModule;
import com.example.StreamApp.login.di.LoginModule;
import com.example.StreamApp.utils.ApiBuilder;
import com.example.StreamApp.utils.AppConstants;


import retrofit2.Retrofit;


public class LoginStepOneDialogFragment extends DialogFragment {
    private static final String TAG = "FirstDialogFragment";

    TextView title;
    EditText number;
    Button submit;
    private LoginStepTwoDialogFragment loginStepTwoDialogFragment;
    private LoginStepTwoListener loginStepTwoListener;
    private LoginSharedViewModel loginSharedViewModel;
    private UserDatabase database = LoginModule.provideUserDatabase();
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginRepository loginRepository = LoginModule.provideLoginRepository(apiService,database.userDao());
    private LoginSharedViewModelFactory loginSharedViewModelFactory = LoginModule.provideShareViewModelFactory(loginRepository);
    @SuppressLint("HardwareIds")
    private String androidId;
    private String deviceModel = AppConstants.getDeviceName();
    private String deviceOs = AppConstants.getAndroidVersion();
    private ProgressDialog dialog;

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


        loginSharedViewModel = new ViewModelProvider(requireActivity(), loginSharedViewModelFactory).get(LoginSharedViewModel.class);

        title = view.findViewById(R.id.title);
        number = view.findViewById(R.id.edit_txt);
        submit = view.findViewById(R.id.submit);

        loginStepOneResponse();

        final LoginStepOneRequest loginStepOneRequest = new LoginStepOneRequest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStepOneRequest.setMobile(number.getText().toString());
                loginStepOneRequest.setDevice_id(androidId);
                loginStepOneRequest.setDevice_model(deviceModel);
                loginStepOneRequest.setDevice_os(deviceOs);

                loginSharedViewModel.loginStepOne(loginStepOneRequest);

                dialog = new ProgressDialog(getContext());
                dialog.setTitle(R.string.progressDialogTitle);
                dialog.setMessage(getString(R.string.loadingProgress));
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

    }

    public void loginStepOneResponse() {
        loginSharedViewModel.loginStepOneLiveData.observe(getViewLifecycleOwner(), new Observer<LoginStepOneResponse>() {
            @Override
            public void onChanged(LoginStepOneResponse loginBody) {
                if (loginBody != null) {
                    loginStepTwoDialogFragment = new LoginStepTwoDialogFragment(loginStepTwoListener);
                    loginStepTwoDialogFragment.show(getParentFragmentManager(), "LoginStepTwoDialogFragment");
                    Log.d(TAG, "onChanged: " + loginBody);
                    dismiss();
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    Toast.makeText(getContext(),"Error Occurred",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
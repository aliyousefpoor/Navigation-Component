package com.example.bottomnavigation.moretab;

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
import com.example.bottomnavigation.data.datasource.LoginRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;
import com.example.bottomnavigation.utils.AppConstants;

import retrofit2.Retrofit;


public class FirstDialogFragment extends DialogFragment {
    private static final String TAG = "FirstDialogFragment";

    TextView title;
    EditText number;
    Button submit;
    private SecondDialogFragment secondDialogFragment;
    private VerificationCodeListener verificationCodeListener;
    private LoginViewModel loginViewModel;
    private LoginViewModelFactory loginViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginRemoteDataSource loginRemoteDataSource = MoreModule.provideUserSource(apiService);
    private LoginRepository loginRepository =MoreModule.provideLoginSource(loginRemoteDataSource);
    @SuppressLint("HardwareIds")
    private String androidId;
    private String deviceModel = AppConstants.getDeviceName();
    private String deviceOs = AppConstants.getAndroidVersion();
    private ProgressDialog dialog;


    public FirstDialogFragment(VerificationCodeListener verificationCodeListener) {
        this.verificationCodeListener = verificationCodeListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        androidId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        return view;
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModelFactory = new LoginViewModelFactory(loginRepository);
        loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);

        title = view.findViewById(R.id.title);
        number = view.findViewById(R.id.edit_txt);
        submit = view.findViewById(R.id.submit);
        postRequest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginViewModel.postUserNumber(number.getText().toString(), androidId, deviceModel, deviceOs);

                dialog = new ProgressDialog(getContext());
                dialog.setTitle(R.string.progressDialogTitle);
                dialog.setMessage(getString(R.string.loadingProgress));
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

    }

    public void postRequest() {
        loginViewModel.userLiveData.observe(this, new Observer<LoginResponseBody>() {
            @Override
            public void onChanged(LoginResponseBody loginBody) {
                secondDialogFragment = new SecondDialogFragment(number.getText().toString(), verificationCodeListener);
                secondDialogFragment.show(getParentFragmentManager(), "SecondDialogFragment");
                Log.d(TAG, "onChanged: " + loginBody);
                dismiss();
                dialog.dismiss();

            }
        });
    }

}

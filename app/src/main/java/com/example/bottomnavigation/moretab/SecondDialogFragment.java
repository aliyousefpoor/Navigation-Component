package com.example.bottomnavigation.moretab;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.example.bottomnavigation.data.datasource.VerificationSource;
import com.example.bottomnavigation.data.model.ResponseVerificationBody;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class SecondDialogFragment extends DialogFragment {
    private static final String TAG = "SecondDialogFragment";
    EditText code;
    Button submit, changeNumber;
    TextView resendCode;
    String number;
    private UserVerificationViewModel verificationViewModel;
    private UserVerificationViewModelFactory verificationViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private VerificationSource verificationSource = MoreModule.provideUserVerificationSource(apiService);
    private String androidId;
    private VerificationCodeListener verificationCodeListener;


    public SecondDialogFragment(String number,VerificationCodeListener verificationCodeListener) {
        this.number = number;
        this.verificationCodeListener=verificationCodeListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_dialog, container, false);
        androidId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        setCancelable(false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verificationViewModelFactory = new UserVerificationViewModelFactory(verificationSource);
        verificationViewModel = new ViewModelProvider(this, verificationViewModelFactory).get(UserVerificationViewModel.class);
        code = view.findViewById(R.id.verificationCode);
        submit = view.findViewById(R.id.secondDialogSubmit);
        changeNumber = view.findViewById(R.id.changeNumber);
        resendCode = view.findViewById(R.id.resendCode);
        postVerificationCodeRequest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationViewModel.postVerificationCode(number, androidId, code.getText().toString());
                Log.d(TAG, "onClick: " + number);


            }
        });

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstDialogFragment firstDialogFragment = new FirstDialogFragment(verificationCodeListener);
                firstDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");

            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void postVerificationCodeRequest() {
        verificationViewModel.verificationLiveData.observe(this, new Observer<ResponseVerificationBody>() {

            @Override
            public void onChanged(ResponseVerificationBody responseVerificationBody) {
                Log.d(TAG, "onChanged: "+responseVerificationBody);

                if (responseVerificationBody != null) {
                    verificationCodeListener.onResponse(responseVerificationBody);
                    dismiss();

                } else {
                    Toast.makeText(getContext(), "enter valid code", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}

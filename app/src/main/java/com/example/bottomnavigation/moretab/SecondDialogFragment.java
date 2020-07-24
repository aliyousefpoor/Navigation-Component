package com.example.bottomnavigation.moretab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.datasource.UserVerificationSource;
import com.example.bottomnavigation.data.model.UserVerification;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class SecondDialogFragment extends DialogFragment {
    EditText code;
    Button submit,changeNumber;
    String number;
    private UserVerificationViewModel verificationViewModel;
    private UserVerificationViewModelFactory verificationViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private UserVerificationSource verificationSource = MoreModule.provideUserVerificationSource(apiService);
    private String androidId;


    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_dialog,container,false);
        androidId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verificationViewModelFactory = new UserVerificationViewModelFactory(verificationSource);
        verificationViewModel = new ViewModelProvider(this,verificationViewModelFactory).get(UserVerificationViewModel.class);
        code = view.findViewById(R.id.verificationCode);
        submit = view.findViewById(R.id.secondDialogSubmit);
        changeNumber = view.findViewById(R.id.changeNumber);

        FirstDialogFragment firstDialogFragment = new FirstDialogFragment();
        number = firstDialogFragment.number.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationViewModel.postVerificationCode(number,androidId , code.getText().toString());

                postVerificationCodeRequest();
            }
        });
    }

    public void postVerificationCodeRequest(){
        verificationViewModel.verificationLiveData.observe(this, new Observer<UserVerification>() {
            @Override
            public void onChanged(UserVerification verification) {


            }
        });
    }
}

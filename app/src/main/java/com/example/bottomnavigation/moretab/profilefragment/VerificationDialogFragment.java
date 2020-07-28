package com.example.bottomnavigation.moretab.profilefragment;

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
import com.example.bottomnavigation.data.datasource.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class VerificationDialogFragment extends DialogFragment {
    private static final String TAG = "SecondDialogFragment";
    EditText code;
    Button submit, changeNumber;
    TextView resendCode;
    String number;
    private VerificationViewModel verificationViewModel;
    private VerificationViewModelFactory verificationViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private VerificationRemoteDataSource verificationRemoteDataSource = MoreModule.provideVerificationRemoteDataSource(apiService);
    private LoginRepository loginRepository = MoreModule.provideVerificationSource(verificationRemoteDataSource);
    private String androidId;
    private VerificationCodeListener verificationCodeListener;
    private ProgressDialog dialog;


    public VerificationDialogFragment(String number, VerificationCodeListener verificationCodeListener) {
        this.number = number;
        this.verificationCodeListener = verificationCodeListener;
    }

    @SuppressLint("HardwareIds")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_dialog, container, false);
        androidId = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verificationViewModelFactory = new VerificationViewModelFactory(loginRepository);
        verificationViewModel = new ViewModelProvider(this, verificationViewModelFactory).get(VerificationViewModel.class);
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

                LoginDialogFragment loginDialogFragment = new LoginDialogFragment(verificationCodeListener);
                loginDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");

            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void postVerificationCodeRequest() {
        verificationViewModel.verificationLiveData.observe(this, new Observer<VerificationResponseBody>() {

            @Override
            public void onChanged(final VerificationResponseBody verificationResponseBody) {


                if (verificationResponseBody != null) {
                    Log.d(TAG, "onChanged: " + verificationResponseBody.getUserId());

                    verificationCodeListener.onResponse(verificationResponseBody);

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

package com.example.bottomnavigation.moretab;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
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
import com.example.bottomnavigation.data.datasource.LoginSource;
import com.example.bottomnavigation.data.model.ResponseLoginBody;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;


public class FirstDialogFragment extends DialogFragment {
    private static final String TAG = "FirstDialogFragment";

    TextView title;
    EditText number;
    Button submit;
    private SecondDialogFragment secondDialogFragment;
    private VerificationCodeListener verificationCodeListener;
    private UserViewModel userViewModel;
    private UserViewModelFactory userViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private LoginSource loginSource = MoreModule.provideUserSource(apiService);
    @SuppressLint("HardwareIds")
    private String androidId;
    private String deviceModel = getDeviceName();
    private String deviceOs = getAndroidVersion();
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

        userViewModelFactory = new UserViewModelFactory(loginSource);
        userViewModel = new ViewModelProvider(this, userViewModelFactory).get(UserViewModel.class);

        title = view.findViewById(R.id.title);
        number = view.findViewById(R.id.edit_txt);
        submit = view.findViewById(R.id.submit);
        postRequest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userViewModel.postUserNumber(number.getText().toString(), androidId, deviceModel, deviceOs);
//
//                 dialog = new ProgressDialog(getActivity(), getTheme());
//                dialog.setTitle(getString(R.string.progressDialog));
//                dialog.setMessage(getString(R.string.loadingProgress));
//                dialog.setIndeterminate(true);
//                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            }
        });

    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android" + sdkVersion + "(" + release + ")";
    }

    public void postRequest() {
        userViewModel.userLiveData.observe(this, new Observer<ResponseLoginBody>() {
            @Override
            public void onChanged(ResponseLoginBody loginBody) {
                secondDialogFragment = new SecondDialogFragment(number.getText().toString(), verificationCodeListener);
                secondDialogFragment.show(getParentFragmentManager(), "SecondDialogFragment");
                Log.d(TAG, "onChanged: " + loginBody);
                dismiss();



            }
        });
    }

}

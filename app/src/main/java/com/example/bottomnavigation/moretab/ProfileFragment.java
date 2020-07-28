package com.example.bottomnavigation.moretab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.datasource.UserLocaleDataSource;
import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.moretab.di.MoreModule;


public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private EditText name, date;
    private ProfileViewModel profileViewModel;
    private ProfileViewModelFactory profileViewModelFactory;
    private UserLocaleDataSource userLocaleDataSource = MoreModule.provideUserLocaleDataSource();
    private LoginRepository loginRepository =MoreModule.provideUserLocaleDataSource(userLocaleDataSource);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileViewModelFactory = new ProfileViewModelFactory(loginRepository);
        profileViewModel = new ViewModelProvider( this,profileViewModelFactory).get(ProfileViewModel.class);

        assert getArguments() != null;
        final VerificationResponseBody verificationResponseBody = getArguments().getParcelable("body");

        radioSexGroup = view.findViewById(R.id.radio_group);

        name = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        Button change = view.findViewById(R.id.change);
        Button cancel = view.findViewById(R.id.cancle);
        assert verificationResponseBody != null;
        final int id = verificationResponseBody.getUserId();
        final String token = verificationResponseBody.getToken();
        addListenerOnButton(view);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.userInformation(id,token,name.getText().toString(),date.getText().toString(),radioSexButton.getText().toString(),getContext());

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask(getContext());
                getDataAsyncTask.execute();
            }
        });


    }

    public void addListenerOnButton(final View view) {

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioSexButton = view.findViewById(checkedId);
                Toast.makeText(getContext(), radioSexButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package com.example.bottomnavigation.moretab.profilefragment;

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
import com.example.bottomnavigation.data.local.UserDataSource;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.moretab.di.MoreModule;

import java.sql.ResultSet;


public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton,male,female;
    private EditText name, date;
    private ProfileViewModel profileViewModel;
    private UserDataSource userLocalDataSource = LoginModule.provideUserDataSource();
    private IsLoginRepository isLoginRepository = LoginModule.provideIsLoginRepository(userLocalDataSource);
    private ProfileViewModelFactory profileViewModelFactory = MoreModule.provideProfileViewModelFactory(isLoginRepository);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = new ViewModelProvider(this, profileViewModelFactory).get(ProfileViewModel.class);

        assert getArguments() != null;
        final UserEntity userEntity = getArguments().getParcelable("body");

        radioSexGroup = view.findViewById(R.id.radio_group);

        name = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        Button change = view.findViewById(R.id.change);
        Button cancel = view.findViewById(R.id.cancle);
        male=view.findViewById(R.id.male);
        female=view.findViewById(R.id.female);

        assert userEntity != null;

        addListenerOnButton(view);
        final User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setToken(userEntity.getToken());

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(name.getText().toString());
                user.setDate(date.getText().toString());
                user.setGender(radioSexButton.getText().toString());

                profileViewModel.saveUser(user, getContext());

                Log.d(TAG, "onClick: ");

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CancelAsyncTask cancelAsyncTask = new CancelAsyncTask(getContext());
                cancelAsyncTask.execute();
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

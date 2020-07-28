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

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

import java.util.List;


public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private EditText name, date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        final VerificationResponseBody verificationResponseBody = getArguments().getParcelable("body");

        radioSexGroup = view.findViewById(R.id.radio_group);

        name = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        Button change = view.findViewById(R.id.change);
        Button cancel = view.findViewById(R.id.cancle);
        addListenerOnButton(view);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(verificationResponseBody, getContext(),
                        name.getText().toString(), date.getText().toString(),
                        radioSexButton.getText().toString());
                updateAsyncTask.execute();

                //                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        UserDataBase dataBase = UserDataBase.getInstance(getContext());
//                        User user = new User();
//                        assert verificationResponseBody != null;
//                        user.setUser_id(verificationResponseBody.getUser_id());
//                        user.setToken(verificationResponseBody.getToken());
//                        user.setName(name.getText().toString());
//                        user.setDate(date.getText().toString());
//                        user.setGender(radioSexButton.getText().toString());
//                        dataBase.userDao().updateUser(user);
//                        Log.d(TAG, "run: ");
//
//                    }
//                }).start();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask(getContext());
                getDataAsyncTask.execute();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String info = " ";
//                        UserDataBase dataBase = UserDataBase.getInstance(getContext());
//                        List<User> user = dataBase.userDao().getAll();
//                        for (User usr : user) {
//                            int id = usr.getUserId();
//                            String token = usr.getToken();
//                            String name = usr.getName();
//                            String date = usr.getDate();
//                            String gender = usr.getGender();

//                            info = info + "\n\n" + "Id :" + id + "\n" + "Token : " + token + "\n" + "Name :" + name + "\n"
//                                    + "Date :" + date + "\n" + "Gender :" + gender;
//                            Log.d(TAG, "run: " + info);
//
//                        }
//                    }
//                }).start();

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

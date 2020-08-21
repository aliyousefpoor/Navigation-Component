package com.example.bottomnavigation.moretab.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.CancelAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.DateListener;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.datasource.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.repository.ProfileRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;
import com.example.bottomnavigation.utils.CalendarUtils;
import com.example.bottomnavigation.utils.FileUtils;

import java.io.File;

import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;


@RequiresApi(api = Build.VERSION_CODES.N)
public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private static final int IMAGE_PICK_CODE = 100;
    private static final int GALLEY_PERMISSION_CODE = 101;
    private static final int IMAGE_CAPTURE_CODE = 200;
    private static final int CAMERA_PERMISSION_CODE = 201;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton, male, female;
    private EditText name, date;
    private ImageView avatar;
    private Uri imageUri;

    private ProfileViewModel profileViewModel;
    private UserDatabase database = LoginModule.provideUserDatabase();
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl = LoginModule.provideUserLocaleDataSource(database.userDao());
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private UserRemoteDataSourceImpl userRemoteDataSource = LoginModule.provideUserRemoteDataSource(apiService);
    private ProfileRepository profileRepository = LoginModule.provideProfileRepository(userLocaleDataSourceImpl, userRemoteDataSource);
    private ProfileViewModelFactory profileViewModelFactory = MoreModule.provideProfileViewModelFactory(profileRepository);
    private User user;

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


        radioSexGroup = view.findViewById(R.id.radio_group);

        name = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        Button change = view.findViewById(R.id.change);
        Button cancel = view.findViewById(R.id.cancle);
        final View progressBar = view.findViewById(R.id.progressBarLayout);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        avatar = view.findViewById(R.id.avatar);


        addListenerOnButton(view);


        progressBar.setVisibility(View.VISIBLE);


        profileViewModel.getUser.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                ProfileFragment.this.user = user;
                progressBar.setVisibility(View.GONE);
                name.setText(user.getName());
                date.setText(user.getDate());
                ProfileFragment.this.user.setToken(user.getToken());
                Glide.with(getContext()).load(user.getAvatar()).into(avatar);
                String checkGender = user.getGender();
                if (checkGender != null) {
                    if (checkGender.equals("Male")) {
                        male.setChecked(true);
                    } else {
                        female.setChecked(true);
                    }
                } else {
                    male.setChecked(false);
                    female.setChecked(false);
                }
            }

        });


        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });

        profileViewModel.getUser();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setName(name.getText().toString());
                user.setDate(date.getText().toString());
                if (radioSexButton.getText().equals("مرد")) {
                    user.setGender("Male");
                } else {
                    user.setGender("Female");
                }

                profileViewModel.updateProfile(user);
                Log.d(TAG, "onClick: ");

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CancelAsyncTask cancelAsyncTask = new CancelAsyncTask(database);
                cancelAsyncTask.execute();

            }
        });


    }

    public void addListenerOnButton(final View view) {

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioSexButton = view.findViewById(checkedId);
//                Toast.makeText(getContext(), radioSexButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    public void showCalendar() {
        CalendarUtils calendarUtils = new CalendarUtils(getContext(), new DateListener() {
            @Override
            public void onDateChange(String persianDate) {
                date.setText(persianDate);
            }
        });
        calendarUtils.showCalendar();

    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Choose Profile Image");
        builder.setCancelable(true);

        builder.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    pickImageFromGallery();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                            || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, CAMERA_PERMISSION_CODE);
                    } else {
                        openCamera();
                    }
                } else {

                }
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        if (galleryIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(galleryIntent, IMAGE_PICK_CODE);
        }
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case CAMERA_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(getContext(), "Permission Denied... !", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data.getData();
            avatar.setImageURI(imageUri);
            updateProfileImage(imageUri);
        } else if (resultCode == RESULT_OK && requestCode == IMAGE_CAPTURE_CODE) {
            avatar.setImageURI(imageUri);
            updateProfileImage(imageUri);
        }


    }

    public void updateProfileImage(Uri imageUri) {
        File file = FileUtils.getFile(getContext(), imageUri);
        Log.d(TAG, "updateProfileImage: " + user.getToken());
        profileViewModel.updateImage(file);

    }
}

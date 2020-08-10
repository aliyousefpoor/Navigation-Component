package com.example.bottomnavigation.moretab.profilefragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
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
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.local.database.CancelAsyncTask;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import java.io.File;
import java.util.Objects;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private Uri imageUri, image;
    private ProfileViewModel profileViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private UserRemoteDataSourceImpl userRemoteDataSource = LoginModule.provideUserRemoteDataSource(apiService);
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl = LoginModule.provideUserLocaleDataSource();
    private IsLoginRepository isLoginRepository = LoginModule.provideIsLoginRepository(userLocaleDataSourceImpl, userRemoteDataSource);
    private ProfileViewModelFactory profileViewModelFactory = MoreModule.provideProfileViewModelFactory(isLoginRepository);
    private PersianDatePickerDialog picker;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
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
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        avatar = view.findViewById(R.id.avatar);

        final ProfileUpdate profileUpdate = new ProfileUpdate();

        addListenerOnButton(view);

        profileUpdate.setToken(userEntity.getToken());


        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle(R.string.progressDialogTitle);
        dialog.setMessage(getString(R.string.getData));
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        Log.d(TAG, "onViewCreated: " + userEntity.getGender() + userEntity.getName());

        profileViewModel.getUserProfile.observe(getViewLifecycleOwner(), new Observer<RemoteUser>() {
            @Override
            public void onChanged(RemoteUser remoteUser) {
                dialog.dismiss();
                name.setText(remoteUser.getNickName());
                date.setText(remoteUser.getBirthdayDate());
                Glide.with(getContext()).load(remoteUser.getAvatar()).into(avatar);

                String checkGender = remoteUser.getGender();
                if (checkGender.equals("Male")) {
                    male.setChecked(true);
                } else {
                    female.setChecked(true);
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

        profileViewModel.getProfile(profileUpdate.getToken(), getContext());

//        File file = null;
//        final MultipartBody.Part requestImage;
//        if (file == null) {
//            file = new File(String.valueOf(image));
//        }
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        requestImage = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
//
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileUpdate.setNickname(name.getText().toString());
                profileUpdate.setDate_of_birth(date.getText().toString());
                if (radioSexButton.getText().equals("مرد")) {
                    profileUpdate.setGender("Male");
                } else {
                    profileUpdate.setGender("Female");
                }

                profileViewModel.updateProfile(profileUpdate, getContext());
//                profileViewModel.updateImage(requestImage);
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

    @SuppressLint("ResourceAsColor")
    public void showCalendar() {

        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 3, 13);
        picker = new PersianDatePickerDialog(getContext())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(1420)
                .setActionTextColor(R.color.white)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getGregorianChange());//Fri Oct 15 03:25:44 GMT+04:30 1582
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getTimeInMillis());//1583253636577
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getTime());//Tue Mar 03 20:10:36 GMT+03:30 2020
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getDelimiter());//  /
                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianLongDate());// سه‌شنبه  13  اسفند  1398
//                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianLongDateAndTime()); //سه‌شنبه  13  اسفند  1398 ساعت 20:10:36
//                        Log.d(TAG, "onDateSelected: " + persianCalendar.getPersianMonthName()); //اسفند
                        Log.d(TAG, "onDateSelected: " + persianCalendar.isPersianLeapYear());//false
                        String persianDate = persianCalendar.getPersianShortDate().replaceAll("/", "-");
                        date.setText(persianDate);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.avatar_dialog_fragment);
        dialog.setCanceledOnTouchOutside(true);

        ImageView gallery, camera, remove;
        gallery = dialog.findViewById(R.id.gallery);
        camera = dialog.findViewById(R.id.camera);
        remove = dialog.findViewById(R.id.remove);

        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, GALLEY_PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();

                    }
                } else {

                }
                dialog.dismiss();
            }


        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
            case GALLEY_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(getContext(), "Permission Denied... !", Toast.LENGTH_SHORT).show();
                }
            }
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
            updateProfileImage();
            avatar.setImageURI(data.getData());
            image = data.getData();
        } else if (resultCode == RESULT_OK && requestCode == IMAGE_CAPTURE_CODE) {
            updateProfileImage();
            avatar.setImageURI(imageUri);
            image = imageUri;
        }


    }

    public void updateProfileImage() {
        File file = null;
        if (file == null) {
            file = new File(Objects.requireNonNull(image.getPath()));
        }
        profileViewModel.updateImage(file);
    }
}

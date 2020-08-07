package com.example.bottomnavigation.moretab;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.MoreModel;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.moretab.di.MoreModule;
import com.example.bottomnavigation.login.LoginDialogFragment;
import com.example.bottomnavigation.login.VerificationCodeListener;


import java.util.ArrayList;
import java.util.List;


public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";

    NavController navController;
    RecyclerView recyclerView;
    View view;
    Button button;
    private MoreItemListener moreItemListener;
    private VerificationCodeListener verificationCodeListener;
    private MoreViewModel moreViewModel;
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl = LoginModule.provideUserLocaleDataSource();
    private IsLoginRepository isLoginRepository = LoginModule.provideIsLoginRepository(userLocaleDataSourceImpl);
    private MoreViewModelFactory moreViewModelFactory = MoreModule.provideMoreViewModelFactory(isLoginRepository);

    private Bundle bundle = new Bundle();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_fragment, container, false);
        getItemType();
        setUpLogin();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moreViewModel = new ViewModelProvider(this, moreViewModelFactory).get(MoreViewModel.class);


        recyclerView = view.findViewById(R.id.recycler_view);
        button = view.findViewById(R.id.btn);
        navController = Navigation.findNavController(view);

        List<MoreModel> moreList = fill_with_Data();

        Log.d(TAG, "onViewCreated: " + moreList.toString());
        final MoreAdapter moreAdapter = new MoreAdapter(moreList, getContext(), moreItemListener);
        recyclerView.setAdapter(moreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("FragmentLiveDataObserve")
            @Override
            public void onClick(View v) {


            }
        });


        moreViewModel.isLoginUser.observeSingleEvent(getViewLifecycleOwner(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if (userEntity != null) {
                    bundle.putParcelable("body", userEntity);
                    navController.navigate(R.id.action_moreFragment_to_profileFragment, bundle);
                    Log.d(TAG, "onChanged: userEntity");
                } else {
                    LoginDialogFragment loginDialogFragment = new LoginDialogFragment(verificationCodeListener);
                    loginDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");
                }
            }
        });

    }


    public List<MoreModel> fill_with_Data() {

        List<MoreModel> moreLists = new ArrayList<>();
        moreLists.add(new MoreModel("پروفایل", MoreModel.Type.Profile));
        moreLists.add(new MoreModel("درباره ما", MoreModel.Type.About));
        moreLists.add(new MoreModel("تماس با ما", MoreModel.Type.Contact));

        return moreLists;
    }

    public void getItemType() {
        moreItemListener = new MoreItemListener() {
            @SuppressLint("FragmentLiveDataObserve")
            @Override
            public void onClick(MoreModel item) {

                switch (item.type) {

                    case Profile:
                        moreViewModel.isUserLogin(getContext());
                        break;

                    case About:
                        navController.navigate(R.id.action_moreFragment_to_aboutUsFragment);
                        break;

                    case Contact:
                        navController.navigate(R.id.action_moreFragment_to_contactFragment);
                        break;
                }
            }
        };
    }

    public void setUpLogin() {

        verificationCodeListener = new VerificationCodeListener() {
            @Override
            public void onResponse(UserEntity user) {
                Log.d(TAG, "onResponse: listener");

                bundle.putParcelable("body", user);

                navController.navigate(R.id.action_moreFragment_to_profileFragment, bundle);
            }
        };
    }
}





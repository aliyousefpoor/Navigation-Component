package com.example.StreamApp.moretab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.CustomApp;
import com.example.StreamApp.R;
import com.example.StreamApp.data.datasource.local.database.UserDatabase;
import com.example.StreamApp.data.model.MoreModel;
import com.example.StreamApp.data.model.User;
import com.example.StreamApp.data.repository.LoginRepository;
import com.example.StreamApp.di.ApiBuilderModule;
import com.example.StreamApp.login.LoginSharedViewModel;
import com.example.StreamApp.login.LoginSharedViewModelFactory;
import com.example.StreamApp.login.di.LoginModule;
import com.example.StreamApp.login.LoginStepOneDialogFragment;
import com.example.StreamApp.login.LoginStepTwoListener;
import com.example.StreamApp.utils.ApiBuilder;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;


public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";

    NavController navController;
    RecyclerView recyclerView;
    View view;
    private MoreItemListener moreItemListener;
    private LoginStepTwoListener loginStepTwoListener;
    private LoginSharedViewModel sharedViewModel;
    private UserDatabase database = LoginModule.provideUserDatabase();
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private LoginRepository loginRepository = LoginModule.provideLoginRepository(apiService,database.userDao());
    private LoginSharedViewModelFactory sharedViewModelFactory = LoginModule.provideShareViewModelFactory(loginRepository);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_fragment, container, false);
        getItemType();
        setUpLogin();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(this, sharedViewModelFactory).get(LoginSharedViewModel.class);


        recyclerView = view.findViewById(R.id.recycler_view);
        navController = Navigation.findNavController(view);

        List<MoreModel> moreList = fill_with_Data();

        Log.d(TAG, "onViewCreated: " + moreList.toString());
        final MoreAdapter moreAdapter = new MoreAdapter(moreList, getContext(), moreItemListener);
        recyclerView.setAdapter(moreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        sharedViewModel.isLogin.observeSingleEvent(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLogin) {
                if (isLogin){
                    navController.navigate(R.id.action_moreFragment_to_profileFragment);

                }
                else{
                    LoginStepOneDialogFragment loginStepOneDialogFragment = new LoginStepOneDialogFragment(loginStepTwoListener);
                    loginStepOneDialogFragment.show(getParentFragmentManager(), "LoginStepOneDialogFragment");

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

                        sharedViewModel.isLogin();
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

        loginStepTwoListener = new LoginStepTwoListener() {
            @Override
            public void onResponse(User user) {
                Log.d(TAG, "onResponse: listener");
                navController.navigate(R.id.action_moreFragment_to_profileFragment);
            }
        };
    }
}





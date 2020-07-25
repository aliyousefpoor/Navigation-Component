package com.example.bottomnavigation.moretab;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.MoreModel;
import com.example.bottomnavigation.data.model.ResponseVerificationBody;

import java.util.ArrayList;
import java.util.List;


public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";

    NavController navController;
    RecyclerView recyclerView;
    View view;
    Button button;
    private MoreItemListener moreItemListener;
    private FirstDialogFragment firstDialogFragment;
    private VerificationCodeListener verificationCodeListener;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_fragment, container, false);
        getItemId();
        setUpLogin();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycler_view);
        button = view.findViewById(R.id.btn);
        navController = Navigation.findNavController(view);

        List<MoreModel> moreList = fill_with_Data();

        Log.d(TAG, "onViewCreated: " + moreList.toString());
        MoreAdapter moreAdapter = new MoreAdapter(moreList, getContext(), moreItemListener);
        recyclerView.setAdapter(moreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstDialogFragment = new FirstDialogFragment(verificationCodeListener);
                firstDialogFragment.show(getParentFragmentManager(), "FirstDialogFragment");
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

    public void getItemId() {
        moreItemListener = new MoreItemListener() {
            @Override
            public void onClick(MoreModel item) {

                switch (item.type) {

                    case Profile:
                        navController.navigate(R.id.action_moreFragment_to_profileFragment);
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
            public void onResponse(ResponseVerificationBody responseVerificationBody) {
                Log.d(TAG, "onResponse: listener");

                navController.navigate(R.id.action_moreFragment_to_profileFragment);
            }
        };

    }
}

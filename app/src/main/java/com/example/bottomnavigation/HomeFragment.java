package com.example.bottomnavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    Button button;
    TextView textView;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.home_btn);
        textView = view.findViewById(R.id.txt);
        navController = Navigation.findNavController(view);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("key", textView.getText().toString());
                navController.navigate(R.id.action_homeFragment_to_secondFragment,bundle);

                Log.d(TAG, "onClick: ");

                Toast.makeText(getContext(), "Text is :" + textView.getText(), Toast.LENGTH_SHORT).show();


            }
        });

//        OnBackPressedCallback callback =new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                NavHostFragment.findNavController(HomeFragment.this).navigateUp();
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

    }




}

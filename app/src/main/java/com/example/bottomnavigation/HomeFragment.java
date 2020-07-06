package com.example.bottomnavigation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

public class HomeFragment extends Fragment {
    Button button;
    TextView textView;
    NavController navController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.btn);
        textView = view.findViewById(R.id.txt);
        navController = Navigation.findNavController(view);

        final Bundle bundle = new Bundle();
        final SecondFragment secondFragment = new SecondFragment();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("key",textView.getText().toString());
                navController.navigate(R.id.action_homeFragment_to_secondFragment,bundle);



                Toast.makeText(getContext(), "Text is :" + textView.getText(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}

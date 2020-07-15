package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {
    private static final String TAG = "SecondFragment";
    TextView textView;
    NavController navController;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.txt);
        button = view.findViewById(R.id.second_btn);

        navController = Navigation.findNavController(view);

        textView.setText("Second " + getArguments().getString("key"));

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Key", textView.getText().toString());
                navController.navigate(R.id.action_secondFragment_to_thirdFragment, bundle);
                Toast.makeText(getContext(), textView.getText() + " In Third Fragment", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

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

public class CategoryFragment extends Fragment {
    Button button;
    TextView textView;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.cat_btn);
        textView = view.findViewById(R.id.txt);
        navController = Navigation.findNavController(view);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("key", textView.getText().toString());
                navController.navigate(R.id.action_categoryFragment_to_secondFragment,bundle);

                Toast.makeText(getContext(), "Text is :" + textView.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

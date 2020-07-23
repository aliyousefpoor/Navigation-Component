package com.example.bottomnavigation.moretab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.bottomnavigation.R;

public class FirstDialogFragment extends DialogFragment {

    TextView title;
    EditText number;
    Button submit;
    private SecondDialogFragment secondDialogFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title= view.findViewById(R.id.title);
        number = view.findViewById(R.id.edit_txt);
        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secondDialogFragment = new SecondDialogFragment();
                secondDialogFragment.show(getParentFragmentManager(),"SecondDialogFragment");

            }
        });



    }
}

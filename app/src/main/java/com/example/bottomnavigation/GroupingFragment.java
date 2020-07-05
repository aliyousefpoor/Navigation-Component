package com.example.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class GroupingFragment extends Fragment {
    Button button;
    TextView textView;

    public void setText(String param) {
        textView.setText(param);
    }

    private void sendBroadCast(String text) {
        Intent intent = new Intent("GroupingFragment");
        intent.putExtra("message", text);
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.groupfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.btn);
        textView = view.findViewById(R.id.txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadCast(textView.toString());

            }
        });
    }
}

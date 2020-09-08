package com.example.bottomnavigation.productdetail;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.CommentPostResponse;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.products.di.ProductModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class CommentDialogFragment extends DialogFragment {
    private Button submit;
    private EditText comment;
    private CommentViewModel commentViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private CommentViewModelFactory commentViewModelFactory = ProductModule.provideCommentViewModelFactory(apiService);
    private int id;
    private String title;
    private int score = 4;
    private ProgressDialog dialog;


    public CommentDialogFragment(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_dialog_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commentViewModel = new ViewModelProvider(this, commentViewModelFactory).get(CommentViewModel.class);
        submit = view.findViewById(R.id.submitComment);
        comment = view.findViewById(R.id.commentEditText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentViewModel.sendComment(title, score, comment.getText().toString(), id);
                dialog = new ProgressDialog(getContext());
                dialog.setTitle(R.string.progressDialogTitle);
                dialog.setMessage(getString(R.string.loadingProgress));
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

        commentViewModel.commentResponse.observe(getViewLifecycleOwner(), new Observer<CommentPostResponse>() {
            @Override
            public void onChanged(CommentPostResponse commentPostResponse) {
                dismiss();
                dialog.dismiss();
                Toast.makeText(getContext(), commentPostResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

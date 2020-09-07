package com.example.bottomnavigation.productdetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.remote.SendCommentRemoteDataSource;

public class CommentViewModelFactory implements ViewModelProvider.Factory {
    private SendCommentRemoteDataSource sendCommentRemoteDataSource;

    public CommentViewModelFactory(SendCommentRemoteDataSource sendCommentRemoteDataSource){
        this.sendCommentRemoteDataSource=sendCommentRemoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CommentViewModel.class)){
            return (T) new CommentViewModel(sendCommentRemoteDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}

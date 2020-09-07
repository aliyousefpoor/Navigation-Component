package com.example.bottomnavigation.productdetail;

import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.remote.SendCommentRemoteDataSource;

public class CommentViewModel extends ViewModel {
    private SendCommentRemoteDataSource sendCommentRemoteDataSource;

    public CommentViewModel(SendCommentRemoteDataSource sendCommentRemoteDataSource){
        this.sendCommentRemoteDataSource=sendCommentRemoteDataSource;
    }
}

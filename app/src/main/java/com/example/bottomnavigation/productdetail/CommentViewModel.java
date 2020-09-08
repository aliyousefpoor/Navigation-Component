package com.example.bottomnavigation.productdetail;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.SendCommentRemoteDataSource;
import com.example.bottomnavigation.data.model.CommentPostResponse;

public class CommentViewModel extends ViewModel {
    private static final String TAG = "CommentViewModel";
    private SendCommentRemoteDataSource sendCommentRemoteDataSource;

    public CommentViewModel(SendCommentRemoteDataSource sendCommentRemoteDataSource) {
        this.sendCommentRemoteDataSource = sendCommentRemoteDataSource;
    }

    private MutableLiveData<CommentPostResponse> _commentResponse = new MutableLiveData<>();
    public LiveData<CommentPostResponse> commentResponse = _commentResponse;

    public void sendComment(String title, int score, String commentText, int id) {
        sendCommentRemoteDataSource.sendComment(title, score, commentText, id, new DataSourceListener<CommentPostResponse>() {
            @Override
            public void onResponse(CommentPostResponse response) {
                _commentResponse.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}

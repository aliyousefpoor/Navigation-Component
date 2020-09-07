package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.CommentPostResponse;


import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendCommentRemoteDataSource {
    private ApiService apiService;

    public SendCommentRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }


    public void sendComment(String title, int score, String commentText, int id, final DataSourceListener<CommentPostResponse> dataSourceListener) {
        apiService.sendComment(title, score, commentText, id).enqueue(new Callback<CommentPostResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommentPostResponse> call, @NotNull Response<CommentPostResponse> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<CommentPostResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}

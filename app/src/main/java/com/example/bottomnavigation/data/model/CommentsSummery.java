package com.example.bottomnavigation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsSummery {
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

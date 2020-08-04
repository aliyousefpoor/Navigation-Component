package com.example.bottomnavigation.moretab.profilefragment;

import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

public interface VerificationCodeListener {
    void onResponse(UserEntity user);
}

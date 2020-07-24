package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.UserVerificationSource;

public class UserVerificationViewModelFactory implements ViewModelProvider.Factory {
    private UserVerificationSource verificationSource;

    public UserVerificationViewModelFactory(UserVerificationSource verificationSource){
        this.verificationSource=verificationSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)){
            return (T) new UserVerificationViewModel(verificationSource);
        }
    throw new IllegalArgumentException("UnKnown Class");
    }
}

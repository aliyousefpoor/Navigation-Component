package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.VerificationSource;

public class UserVerificationViewModelFactory implements ViewModelProvider.Factory {
    private VerificationSource verificationSource;

    public UserVerificationViewModelFactory(VerificationSource verificationSource){
        this.verificationSource=verificationSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserVerificationViewModel.class)){
            return (T) new UserVerificationViewModel(verificationSource);
        }
    throw new IllegalArgumentException("UnKnown Class");
    }
}

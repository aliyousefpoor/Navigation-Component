package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.VerificationSource;

public class VerificationViewModelFactory implements ViewModelProvider.Factory {
    private VerificationSource verificationSource;

    public VerificationViewModelFactory(VerificationSource verificationSource){
        this.verificationSource=verificationSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VerificationViewModel.class)){
            return (T) new VerificationViewModel(verificationSource);
        }
    throw new IllegalArgumentException("UnKnown Class");
    }
}

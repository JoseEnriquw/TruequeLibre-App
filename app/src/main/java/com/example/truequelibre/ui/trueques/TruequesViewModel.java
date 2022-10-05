package com.example.truequelibre.ui.trueques;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TruequesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TruequesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
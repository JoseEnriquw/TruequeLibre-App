package com.example.truequelibre.ui.publicaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PublicacionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PublicacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
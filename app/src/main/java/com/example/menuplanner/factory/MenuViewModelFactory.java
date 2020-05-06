package com.example.menuplanner.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.menuplanner.viewmodel.MenuViewModel;

public class MenuViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private int menuId;

    public MenuViewModelFactory(Application application, int menuId) {
        this.application = application;
        this.menuId = menuId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MenuViewModel(this.application, this.menuId);
    }
}

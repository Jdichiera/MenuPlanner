package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.menuplanner.entity.User;
import com.example.menuplanner.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }
}

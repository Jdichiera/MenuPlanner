package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.repository.MainDishRepository;

import java.util.List;

public class MainDishViewModel extends AndroidViewModel {
    private MainDishRepository repository;

    public MainDishViewModel(@NonNull Application application) {
        super(application);
        repository = new MainDishRepository(application);
    }

    public void insert(MainDish mainDish) {
        repository.insert(mainDish);
    }

    public void update(MainDish mainDish) {
        repository.update(mainDish);
    }

    public void delete(MainDish mainDish) {
        repository.delete(mainDish);
    }

    public void deleteAllMainDishes() { repository.deleteAllMainDishes();}

    public LiveData<MainDish> getMainDish(int mainDishId) {
        return repository.getMainDish(mainDishId);
    }

    public LiveData<List<MainDish>> getAllMainDishes() {
        return repository.getAllMainDishes();
    }
}

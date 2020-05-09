package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.menuplanner.entity.SideDish;
import com.example.menuplanner.repository.SideDishRepository;

import java.util.List;

public class SideDishViewModel extends AndroidViewModel {
    private SideDishRepository repository;

    public SideDishViewModel(@NonNull Application application) {
        super(application);
        repository = new SideDishRepository(application);
    }

    public void insert(SideDish sideDish) {
        repository.insert(sideDish);
    }

    public void update(SideDish sideDish) {
        repository.update(sideDish);
    }

    public void delete(SideDish sideDish) {
        repository.delete(sideDish);
    }

    public void deleteAllSideDishes() { repository.deleteAllSideDishes();}

    public LiveData<SideDish> getSideDish(int sideDishId) {
        return repository.getSideDish(sideDishId);
    }

    public LiveData<List<SideDish>> getAllSideDishes() {
        return repository.getAllSideDishes();
    }
}

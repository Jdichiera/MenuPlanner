package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.menuplanner.entity.Day;
import com.example.menuplanner.repository.DayRepository;

import java.util.List;

public class DayViewModel extends AndroidViewModel {
    private DayRepository repository;
    private LiveData<List<Day>> days;

    public DayViewModel(@NonNull Application application) {
        super(application);
        repository = new DayRepository(application);
        days = repository.getDays();
    }

    public void insert(Day day) {
        repository.insert(day);
    }

    public void update(Day day) {
        repository.update(day);
    }

    public LiveData<List<Day>> getDays() {
        return this.days;
    }
}

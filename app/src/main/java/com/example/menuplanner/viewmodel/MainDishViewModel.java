package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.repository.MainDishRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
//        final MainDish mainDish = new MainDish();
//        final ArrayList<MainDish> mainDishes = new ArrayList<>();
//        LiveData<List<MainDish>> mainDishesLiveData = repository.getAllMainDishes();
//        mainDishesLiveData.observe(lifecycleOwner, new Observer<List<MainDish>>() {
//            @Override
//            public void onChanged(List<MainDish> mainDishesList) {
//                if (mainDishesList != null) {
//                    for (MainDish dish : mainDishesList) {
//                        mainDish.setMainDishId(dish.getMainDishId());
//                        mainDish.setMainDishTitle(dish.getMainDishTitle());
//                        mainDishes.add(mainDish);
//                    }
//                }
//            }
//        });
//        return mainDishes;
    }
}

//package com.example.menuplanner.viewmodel;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//
//import com.example.menuplanner.entity.DishWithIngredientsJoin;
//import com.example.menuplanner.repository.DishWithIngredientsRepository;
//
//import java.util.List;
//
//public class DishWithIngredientsViewModel extends AndroidViewModel {
//    private DishWithIngredientsRepository repository;
//
//    public DishWithIngredientsViewModel(@NonNull Application application) {
//        super(application);
//        repository = new DishWithIngredientsRepository(application);
//    }
//
//    public List<DishWithIngredientsJoin> getDishWithIngredients() {
//        return repository.getDishWithIngredients();
//    }
//}

package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.DishWithIngredients;
import com.example.menuplanner.entity.DishWithIngredientsJoin;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.repository.DishRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DishViewModel extends AndroidViewModel {
    private DishRepository repository;

    public DishViewModel(@NonNull Application application) {
        super(application);
        repository = new DishRepository(application);
    }

    public void insert(Dish dish) {
        repository.insert(dish);
    }

    public void update(Dish dish) {
        repository.update(dish);
    }

    public void delete(Dish dish) {
        repository.delete(dish);
    }

    public void deleteAllDishes() { repository.deleteAllDishes();}

    public List<DishWithIngredients> getDishWithIngredients(int dishId) {
        return repository.getDishWithIngredients(dishId);
    }

    public Set<Ingredient> getDishIngredients(int dishId) {
        Set<Ingredient> ingredients = new HashSet<>();
        for (DishWithIngredients dish : repository.getDishWithIngredients(dishId)) {
            ingredients.addAll(dish.ingredients);
        }
        return ingredients;
    }

    public LiveData<Dish> getDish(int dishId) {
        return repository.getDish(dishId);
    }

    public LiveData<List<Dish>> getAllDishes() {
        return repository.getAllDishes();
    }

    public LiveData<List<Dish>> getAllMainDishes() {
        return repository.getAllMainDishes();
    }

    public LiveData<List<Dish>> getAllSideDishes() {
        return repository.getAllSideDishes();
    }

}

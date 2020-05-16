package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.repository.IngredientRepository;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {
    private IngredientRepository repository;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
        repository = new IngredientRepository(application);
    }

    public void insert(Ingredient ingredient) {
        repository.insert(ingredient);
    }

    public void update(Ingredient ingredient) {
        repository.update(ingredient);
    }

    public void delete(Ingredient ingredient) {
        repository.delete(ingredient);
    }

    public void deleteAllIngredients() { repository.deleteAllIngredients();}

    public LiveData<List<Ingredient>>  getNeededDishIngredients(List<Integer> ingredientIds) {
        return repository.getNeededDishIngredients(ingredientIds);
    }

    public List<Integer>  getNeededDishIngredientIds() {
        return repository.getNeededDishIngredientIds();
    }

    public LiveData<Ingredient> getIngredient(int ingredientId) {
        return repository.getIngredient(ingredientId);
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return repository.getAllIngredients();
    }

    public void deleteAllDishIngredients(int ingredientId) {
        repository.deleteAllDishIngredients(ingredientId);
    }
}

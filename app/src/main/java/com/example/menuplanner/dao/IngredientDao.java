package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {
    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("DELETE FROM " + MenuPlanner.INGREDIENTS_TABLE)
    public void deleteAllIngredients();

    @Query("SELECT * FROM " + MenuPlanner.INGREDIENTS_TABLE + " WHERE ingredientId = :ingredientId")
    LiveData<Ingredient> getIngredient(int ingredientId);

    @Query("SELECT * FROM " + MenuPlanner.INGREDIENTS_TABLE)
    LiveData<List<Ingredient>> getAllIngredients();
}

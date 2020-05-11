package com.example.menuplanner.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.DishWithIngredients;

import java.util.List;

@Dao
public interface DishWithIngredientsDao {
    @Transaction
    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE)
    public List<DishWithIngredients> getDishWithIngredients();
}

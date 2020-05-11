package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.DishWithIngredients;

import java.util.List;

@Dao
public interface DishDao {
    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

    @Query("DELETE FROM " + MenuPlanner.DISH_TABLE)
    public void deleteAllDishes();

    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE + " WHERE dishId = :dishId")
    LiveData<Dish> getDish(int dishId);

    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE)
    LiveData<List<Dish>> getAllDishes();

    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE + " WHERE isMainDish = 1")
    LiveData<List<Dish>> getAllMainDishes();

    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE + " WHERE isMainDish = 0")
    LiveData<List<Dish>> getAllSideDishes();

    @Transaction
    @Query("SELECT * FROM " + MenuPlanner.DISH_TABLE)
    public List<DishWithIngredients> getDishWithIngredients();
}

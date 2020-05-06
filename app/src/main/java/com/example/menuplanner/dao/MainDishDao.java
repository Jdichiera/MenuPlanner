package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.MainDish;

@Dao
public interface MainDishDao {
    @Insert
    void insert(MainDish mainDish);

    @Update
    void update(MainDish mainDish);

    @Delete
    void delete(MainDish mainDish);

    @Query("DELETE FROM " + MenuPlanner.MAINS_TABLE)
    public void deleteAllMainDishes();

    @Query("SELECT * FROM " + MenuPlanner.MAINS_TABLE + " WHERE mainDishId = :mainDishId")
    LiveData<MainDish> getMainDish(int mainDishId);
}

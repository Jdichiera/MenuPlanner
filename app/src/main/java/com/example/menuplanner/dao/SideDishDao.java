package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.entity.SideDish;

import java.util.List;

@Dao
public interface SideDishDao {
    @Insert
    void insert(SideDish sideDish);

    @Update
    void update(SideDish sideDish);

    @Delete
    void delete(SideDish sideDish);

    @Query("DELETE FROM " + MenuPlanner.SIDES_TABLE)
    public void deleteAllSideDishes();

    @Query("SELECT * FROM " + MenuPlanner.SIDES_TABLE + " WHERE sideDishId = :sideDishId")
    LiveData<SideDish> getSideDish(int sideDishId);

    @Query("SELECT * FROM " + MenuPlanner.SIDES_TABLE)
    LiveData<List<SideDish>> getAllSideDishes();
}

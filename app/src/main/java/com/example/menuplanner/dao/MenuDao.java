package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.Menu;

@Dao
public interface MenuDao {
    @Insert
    void insert(Menu menu);

    @Update
    void update(Menu menu);

    @Delete
    void delete (Menu menu);

    @Query("SELECT * FROM " + MenuPlanner.MENUS_TABLE + " WHERE menuId = :menuId")
    public LiveData<Menu> getMenu(int menuId);
}

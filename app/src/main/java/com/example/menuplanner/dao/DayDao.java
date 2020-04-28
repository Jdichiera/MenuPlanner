package com.example.menuplanner.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.entity.Day;

import java.util.List;

@Dao
public interface DayDao {
    @Insert
    void insert(Day day);

    @Update
    void update(Day day);

    @Delete
    void delete(Day day);

    @Query("SELECT * FROM days_table")
    LiveData<List<Day>> getAllDays();

    @Query("SELECT * FROM days_table WHERE dayId = :dayId")
    LiveData<Day> getDay(int dayId);
}

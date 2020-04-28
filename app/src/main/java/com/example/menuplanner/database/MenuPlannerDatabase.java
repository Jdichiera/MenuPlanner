package com.example.menuplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.menuplanner.dao.DayDao;
import com.example.menuplanner.entity.Day;

@Database(
        entities = {Day.class},
        version = 1,
        exportSchema = false)
public abstract class MenuPlannerDatabase extends RoomDatabase {
    private static MenuPlannerDatabase instance;
    public abstract DayDao dayDao();

    public static synchronized MenuPlannerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MenuPlannerDatabase.class, "menu_planner_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}

package com.example.menuplanner.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.dao.DayDao;
import com.example.menuplanner.dao.DishDao;
import com.example.menuplanner.dao.MainDishDao;
import com.example.menuplanner.dao.MenuDao;
import com.example.menuplanner.dao.SideDishDao;
import com.example.menuplanner.dao.UserDao;
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.entity.SideDish;
import com.example.menuplanner.entity.User;

@Database(
        entities = {User.class, Day.class, Menu.class, MainDish.class, SideDish.class, Dish.class},
        version = MenuPlanner.DATABASE_VERSION,
        exportSchema = false)
public abstract class MenuPlannerDatabase extends RoomDatabase {
    private static MenuPlannerDatabase instance;
    public abstract DayDao dayDao();
    public abstract UserDao userDao();
    public abstract MenuDao menuDao();
    public abstract MainDishDao mainDishDao();
    public abstract SideDishDao sideDishDao();
    public abstract DishDao dishDao();

    public static synchronized MenuPlannerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MenuPlannerDatabase.class, MenuPlanner.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}

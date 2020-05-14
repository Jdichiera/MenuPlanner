package com.example.menuplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.dao.DayDao;
import com.example.menuplanner.dao.DishDao;
import com.example.menuplanner.dao.IngredientDao;
import com.example.menuplanner.dao.MenuDao;
import com.example.menuplanner.dao.UserDao;
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.DishWithIngredientsJoin;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.entity.User;

@Database(
        entities = {User.class, Day.class, Menu.class, Dish.class, Ingredient.class,
        DishWithIngredientsJoin.class},
        version = MenuPlanner.DATABASE_VERSION,
        exportSchema = false)
public abstract class MenuPlannerDatabase extends RoomDatabase {
    private static MenuPlannerDatabase instance;
    public abstract DayDao dayDao();
    public abstract UserDao userDao();
    public abstract MenuDao menuDao();
    public abstract DishDao dishDao();
    public abstract IngredientDao ingredientDao();

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

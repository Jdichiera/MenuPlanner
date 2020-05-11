package com.example.menuplanner.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.DishWithIngredientsDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.DishWithIngredients;

import java.util.List;

public class DishWithIngredientsRepository {
    private DishWithIngredientsDao dishWithIngredientsDao;

    public DishWithIngredientsRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.dishWithIngredientsDao = database.dishWithIngredientsDao();
    }

    public List<DishWithIngredients> getDishWithIngredients() {
        return dishWithIngredientsDao.getDishWithIngredients();
    }
}

package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.DishDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Dish;

import java.util.List;

public class DishRepository {
    private DishDao dishDao;

    public DishRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.dishDao = database.dishDao();
    }


    public void insert(Dish dish) {
        new DishRepository.InsertDishAsyncTask(dishDao).execute(dish);
    }

    public void update(Dish dish) {
        new DishRepository.UpdateDishAsyncTask(dishDao).execute(dish);
    }

    public void delete(Dish dish) {
        new DishRepository.DeleteDishAsyncTask(dishDao).execute(dish);
    }

    public LiveData<Dish> getDish(int dishId) {
        return dishDao.getDish(dishId);
    }

    public LiveData<List<Dish>> getAllDishes() {
        return dishDao.getAllDishes();
    }

    public LiveData<List<Dish>> getAllMainDishes() {
        return dishDao.getAllMainDishes();
    }

    public LiveData<List<Dish>> getAllSideDishes() {
        return dishDao.getAllSideDishes();
    }

    public void deleteAllDishes() {
        new DishRepository.DeleteAllDishesAsyncTask(dishDao).execute();
    }

    private static class InsertDishAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;

        private InsertDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.insert(dishes[0]);
            return null;
        }
    }

    private static class UpdateDishAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;

        private UpdateDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.update(dishes[0]);
            return null;
        }
    }

    private static class DeleteDishAsyncTask extends AsyncTask<Dish, Void, Void> {
        private DishDao dishDao;

        private DeleteDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.delete(dishes[0]);
            return null;
        }
    }

    private static class DeleteAllDishesAsyncTask extends AsyncTask<Void, Void, Void> {
        private DishDao dishDao;

        private DeleteAllDishesAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dishDao.deleteAllDishes();
            return null;
        }
    }
}

package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.dao.DayDao;
import com.example.menuplanner.dao.MainDishDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.MainDish;

import java.util.ArrayList;
import java.util.List;

public class MainDishRepository {
    private MainDishDao mainDishDao;

    public MainDishRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.mainDishDao = database.mainDishDao();
    }

    public void insert(MainDish mainDish) {
        new InsertMainDishAsyncTask(mainDishDao).execute(mainDish);
    }

    public void update(MainDish mainDish) {
        new UpdateMainDishAsyncTask(mainDishDao).execute(mainDish);
    }

    public void delete(MainDish mainDish) {
        new DeleteMainDishAsyncTask(mainDishDao).execute(mainDish);
    }

    public LiveData<MainDish> getMainDish(int mainDishId) {
        return mainDishDao.getMainDish(mainDishId);
    }

    public LiveData<List<MainDish>> getAllMainDishes() {
        return mainDishDao.getAllMainDishes();
    }

    public void deleteAllMainDishes() {
        new MainDishRepository.DeleteAllMainDishesAsyncTask(mainDishDao).execute();
    }

    private static class InsertMainDishAsyncTask extends AsyncTask<MainDish, Void, Void> {
        private MainDishDao mainDishDao;

        private InsertMainDishAsyncTask(MainDishDao mainDishDao) {
            this.mainDishDao = mainDishDao;
        }

        @Override
        protected Void doInBackground(MainDish... mainDishes) {
            mainDishDao.insert(mainDishes[0]);
            return null;
        }
    }

    private static class UpdateMainDishAsyncTask extends AsyncTask<MainDish, Void, Void> {
        private MainDishDao mainDishDao;

        private UpdateMainDishAsyncTask(MainDishDao mainDishDao) {
            this.mainDishDao = mainDishDao;
        }

        @Override
        protected Void doInBackground(MainDish... mainDishes) {
            mainDishDao.update(mainDishes[0]);
            return null;
        }
    }

    private static class DeleteMainDishAsyncTask extends AsyncTask<MainDish, Void, Void> {
        private MainDishDao mainDishDao;

        private DeleteMainDishAsyncTask(MainDishDao mainDishDao) {
            this.mainDishDao = mainDishDao;
        }

        @Override
        protected Void doInBackground(MainDish... mainDishes) {
            mainDishDao.delete(mainDishes[0]);
            return null;
        }
    }

    private static class DeleteAllMainDishesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainDishDao mainDishDao;

        private DeleteAllMainDishesAsyncTask(MainDishDao mainDishDao) {
            this.mainDishDao = mainDishDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mainDishDao.deleteAllMainDishes();
            return null;
        }
    }
}

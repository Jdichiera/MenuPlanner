package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.SideDishDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.SideDish;

import java.util.List;

public class SideDishRepository {
    private SideDishDao sideDishDao;

    public SideDishRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.sideDishDao = database.sideDishDao();
    }


    public void insert(SideDish sideDish) {
        new SideDishRepository.InsertSideDishAsyncTask(sideDishDao).execute(sideDish);
    }

    public void update(SideDish sideDish) {
        new SideDishRepository.UpdateSideDishAsyncTask(sideDishDao).execute(sideDish);
    }

    public void delete(SideDish sideDish) {
        new SideDishRepository.DeleteSideDishAsyncTask(sideDishDao).execute(sideDish);
    }

    public LiveData<SideDish> getSideDish(int sideDishId) {
        return sideDishDao.getSideDish(sideDishId);
    }

    public LiveData<List<SideDish>> getAllSideDishes() {
        return sideDishDao.getAllSideDishes();
    }

    public void deleteAllSideDishes() {
        new SideDishRepository.DeleteAllSideDishesAsyncTask(sideDishDao).execute();
    }

    private static class InsertSideDishAsyncTask extends AsyncTask<SideDish, Void, Void> {
        private SideDishDao sideDishDao;

        private InsertSideDishAsyncTask(SideDishDao sideDishDao) {
            this.sideDishDao = sideDishDao;
        }

        @Override
        protected Void doInBackground(SideDish... sideDishes) {
            sideDishDao.insert(sideDishes[0]);
            return null;
        }
    }

    private static class UpdateSideDishAsyncTask extends AsyncTask<SideDish, Void, Void> {
        private SideDishDao sideDishDao;

        private UpdateSideDishAsyncTask(SideDishDao sideDishDao) {
            this.sideDishDao = sideDishDao;
        }

        @Override
        protected Void doInBackground(SideDish... sideDishes) {
            sideDishDao.update(sideDishes[0]);
            return null;
        }
    }

    private static class DeleteSideDishAsyncTask extends AsyncTask<SideDish, Void, Void> {
        private SideDishDao sideDishDao;

        private DeleteSideDishAsyncTask(SideDishDao sideDishDao) {
            this.sideDishDao = sideDishDao;
        }

        @Override
        protected Void doInBackground(SideDish... sideDishes) {
            sideDishDao.delete(sideDishes[0]);
            return null;
        }
    }

    private static class DeleteAllSideDishesAsyncTask extends AsyncTask<Void, Void, Void> {
        private SideDishDao sideDishDao;

        private DeleteAllSideDishesAsyncTask(SideDishDao sideDishDao) {
            this.sideDishDao = sideDishDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sideDishDao.deleteAllSideDishes();
            return null;
        }
    }
}

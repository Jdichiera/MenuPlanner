package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.DayDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Day;

import java.util.List;

public class DayRepository {
    private DayDao dayDao;
    private LiveData<List<Day>> days;

    public DayRepository(Application applciation) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(applciation);
        dayDao = database.dayDao();
        days = dayDao.getAllDays();
    }

    public void insert(Day day) {
        new InsertAsyncTask(dayDao).execute(day);
    }

    public void update(Day day) {
        new UpdateAsyncTask(dayDao).execute(day);
    }

    public void deleteAlLDays() {
        new DeleteAllDaysAsyncTask(dayDao).execute();
    }

    public LiveData<Integer> getDayCount() {
        return dayDao.getDayCount();
    }

    public LiveData<List<Day>> getDays() {
        return this.days;
    }

    private static class InsertAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        private InsertAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... days) {
            dayDao.insert(days[0]);
            return null;
        }
    }

    private static class DeleteAllDaysAsyncTask extends AsyncTask<Void, Void, Void> {
        private DayDao dayDao;

        private DeleteAllDaysAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dayDao.deleteAllDays();
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        private UpdateAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... days) {
            dayDao.update(days[0]);
            return null;
        }
    }
}

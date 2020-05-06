package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.MenuDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Menu;

public class MenuRepository {
    private MenuDao menuDao;
    private LiveData<Menu> menu;

    public MenuRepository(Application application, int menuId) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.menuDao = database.menuDao();
        menu = menuDao.getMenu(menuId);
    }

    public void insert(Menu menu) {
        new InsertMenuAsyncTask(menuDao).execute(menu);
    }

    public void update(Menu menu) {
        new InsertMenuAsyncTask(menuDao).execute(menu);
    }

    public void delete(Menu menu) {
        new DeleteMenuAsyncTask(menuDao).execute(menu);
    }

    public LiveData<Menu> getMenu() {
        return menu;
    }

    private static class InsertMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private InsertMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.insert(menus[0]);
            return null;
        }
    }

    private static class UpdateMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private UpdateMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.insert(menus[0]);
            return null;
        }
    }

    private static class DeleteMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private DeleteMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.insert(menus[0]);
            return null;
        }
    }
}

package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.MenuDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Menu;

import java.util.List;

public class MenuRepository {
    private MenuDao menuDao;
    private LiveData<Menu> menu;

    public MenuRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.menuDao = database.menuDao();
    }

    public MenuRepository(Application application, int menuId) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.menuDao = database.menuDao();
        menu = menuDao.getMenu(menuId);
    }

    public void insert(Menu menu) {
        new InsertMenuAsyncTask(menuDao).execute(menu);
    }

    public void update(Menu menu) {
        new UpdateMenuAsyncTask(menuDao).execute(menu);
    }

    public void delete(Menu menu) {
        new DeleteMenuAsyncTask(menuDao).execute(menu);
    }

    public LiveData<Menu> getMenu() {
        return menu;
    }

    public Menu getSingleMenu(int menuId) {
        return menuDao.getSingleMenu(menuId);
    }

    public LiveData<List<Menu>> getAllMenus() {
        return menuDao.getAllMenus();
    }

    public LiveData<Menu> getMenu(int menuId) {
        return menuDao.getMenu(menuId);
    }

    public void deleteAllMenus() {
        new MenuRepository.DeleteAllMenusAsyncTask(menuDao).execute();
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
            menuDao.update(menus[0]);
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
            menuDao.delete(menus[0]);
            return null;
        }
    }

    private static class DeleteAllMenusAsyncTask extends AsyncTask<Void, Void, Void> {
        private MenuDao menuDao;

        private DeleteAllMenusAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            menuDao.deleteAllMenus();
            return null;
        }
    }
}

package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.menuplanner.dao.UserDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.User;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.userDao = database.userDao();
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    private static class InsertUserAsyncTask extends android.os.AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}

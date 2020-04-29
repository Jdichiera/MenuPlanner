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
        userDao = database.userDao();
    }

    public void insert(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    public boolean validateUser(String userName, String userPassword) {
        return userDao.getUser(userName, userPassword) != null;
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }

//        @Override
//        protected void onPostExecute(Long aLong) {
//            super.onPostExecute(aLong);
//            insertedId = aLong;
//        }
    }
}

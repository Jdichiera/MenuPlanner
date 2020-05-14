package com.example.menuplanner.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.menuplanner.dao.IngredientDao;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Ingredient;

import java.util.List;

public class IngredientRepository {

    private IngredientDao ingredientDao;

    public IngredientRepository(Application application) {
        MenuPlannerDatabase database = MenuPlannerDatabase.getInstance(application);
        this.ingredientDao = database.ingredientDao();
    }


    public void insert(Ingredient ingredient) {
        new IngredientRepository.InsertIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public void update(Ingredient ingredient) {
        new IngredientRepository.UpdateIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public void delete(Ingredient ingredient) {
        new IngredientRepository.DeleteIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public LiveData<Ingredient> getIngredient(int ingredientId) {
        return ingredientDao.getIngredient(ingredientId);
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }

    public void deleteAllIngredients() {
        new IngredientRepository.DeleteAllIngredientsAsyncTask(ingredientDao).execute();
    }

    public void deleteAllDishIngredients(int ingredientId) {
        ingredientDao.deleteAllDishIngredients(ingredientId);
    }

    public LiveData<List<Ingredient>>  getNeededDishIngredients(List<Integer> ingredientIds) {
        return ingredientDao.getNeededDishIngredients(ingredientIds);
    }

    public List<Integer>  getNeededDishIngredientIds() {
        return ingredientDao.getNeededDishIngredientIds();
    }

    private static class InsertIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void> {
        private IngredientDao ingredientDao;

        private InsertIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredientes) {
            ingredientDao.insert(ingredientes[0]);
            return null;
        }
    }

    private static class UpdateIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void> {
        private IngredientDao ingredientDao;

        private UpdateIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredientes) {
            ingredientDao.update(ingredientes[0]);
            return null;
        }
    }

    private static class DeleteIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void> {
        private IngredientDao ingredientDao;

        private DeleteIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredientes) {
            ingredientDao.delete(ingredientes[0]);
            return null;
        }
    }

    private static class DeleteAllIngredientsAsyncTask extends AsyncTask<Void, Void, Void> {
        private IngredientDao ingredientDao;

        private DeleteAllIngredientsAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ingredientDao.deleteAllIngredients();
            return null;
        }
    }
}

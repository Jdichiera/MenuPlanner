package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.repository.MenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    private MenuRepository repository;

    public MenuViewModel(Application application, int menuId) {
        super(application);
        repository = new MenuRepository(application, menuId);
    }

    public MenuViewModel(Application application) {
        super(application);
        repository = new MenuRepository(application);
    }

    public void insert(Menu menu) {
        repository.insert(menu);
    }

    public void update(Menu menu) {
        repository.update(menu);
    }

    public void delete(Menu menu) {
        repository.delete(menu);
    }

    public void deleteAllMenus() { repository.deleteAllMenus();}

    public Menu getSingleMenu(int menuId) {
        return repository.getSingleMenu(menuId);
    }

    public LiveData<Menu> getMenu() {
        return repository.getMenu();
    }

    public LiveData<Menu> getMenu(int menuId) throws Exception{
        LiveData<Menu> dayMenu = repository.getMenu(menuId);
        if (repository.getSingleMenu(menuId) == null) {
            throw new Exception();
        } else {
            return dayMenu;
        }
    }

    public LiveData<List<Menu>> getAllMenus() {
        return repository.getAllMenus();
    }

}

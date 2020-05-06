package com.example.menuplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.repository.MenuRepository;

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

    public LiveData<Menu> getMenu() {
        return repository.getMenu();
    }

    public LiveData<Menu> getMenu(int menuId) {
        return repository.getMenu(menuId);
    }
}

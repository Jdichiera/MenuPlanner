package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.ReportIngredientAdapter;
import com.example.menuplanner.adapter.ReportMenuAdapter;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.example.menuplanner.viewmodel.IngredientViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportViewMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view_menu);

        RecyclerView recyclerView = findViewById(R.id.report_menu_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ReportMenuAdapter adapter = new ReportMenuAdapter();
        recyclerView.setAdapter(adapter);

        MenuViewModel menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        DishViewModel dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                adapter.setDishes(dishes);
            }
        });
        menuViewModel.getAllMenus().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                adapter.setMenus(menus);
            }
        });
//        menuViewModel.getAllMenus().observe(this, new Observer<List<Menu>>() {
//            @Override
//            public void onChanged(List<Menu> menus) {
//                Set<Integer> dishIds = new HashSet<>();
//                for (Menu menu : menus) {
//                    dishIds.add(menu.getMainDishId());
//                    dishIds.add(menu.getSideDish1Id());
//                    dishIds.add(menu.getSideDish2Id());
//                    dishIds.add(menu.getSideDish3Id());
//                }
//                adapter.setMenus(menus);
//                adapter.setDishes(dishViewModel.getDishesWithIds(new ArrayList<>(dishIds)));
//            }
//        });
//        List<Menu> menus2 = menuViewModel.getAllMenus().getValue().;
//        Set<Integer> dishIds = new HashSet<>();
//        for (Menu menu : menus2) {
//            dishIds.add(menu.getMainDishId());
//            dishIds.add(menu.getSideDish1Id());
//            dishIds.add(menu.getSideDish2Id());
//            dishIds.add(menu.getSideDish3Id());
//        }
//        List<Dish> dishes = dishViewModel.getDishesWithIds(new ArrayList<>(dishIds));
//        adapter.setMenus(menus);
//        adapter.setDishes(dishes);
//        menuViewModel.getNeededDishIngredients(ingredientIds).observe(this, new Observer<List<Ingredient>>() {
//            @Override
//            public void onChanged(List<Ingredient> ingredients) {
//                adapter.setIngredients(ingredients);
//            }
//        });
    }
}

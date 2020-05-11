package com.example.menuplanner.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.DishSelectionAdapter;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DishSelectActivity extends AppCompatActivity {
    public static final String DISH_ID = "com.example.menuplanner.DISH_ID";
    public static final String DISH_NAME = "com.example.menuplanner.DISH_NAME";
    public static final int ADD_DISH_REQUEST = 1;
    public static final int EDIT_DISH_REQUEST = 2;
    public static final int ADD_INGREDIENT_REQUEST = 3;
    //    public static final int ADD_MAIN_DISH_REQUEST = 1;
//    public static final int EDIT_MAIN_DISH_REQUEST = 2;
//    public static final int ADD_SIDE_DISH_REQUEST = 3;
//    public static final int EDIT_SIDE_DISH_REQUEST = 4;
    public DishSelectionAdapter adapter;
    private DishViewModel viewModel;
    private MenuItem searchMenu;
    private Boolean isMainDishRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_select);
        setDishType();

        FloatingActionButton buttonAddDish = findViewById(R.id.button_list_add_dish);
        buttonAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.dish_and_ingredient_select_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new DishSelectionAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DishSelectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Dish dish) {
                selectDish(dish);
            }

            @Override
            public void onEditClicked(Dish dish) {
                editDish(dish);
            }
        });

        viewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        if (isMainDishRequest != null) {
            if (isMainDishRequest) {
                viewModel.getAllMainDishes().observe(this, new Observer<List<Dish>>() {
                    @Override
                    public void onChanged(List<Dish> dish) {
                        adapter.setDishes(dish);
                    }
                });
            } else {
                viewModel.getAllSideDishes().observe(this, new Observer<List<Dish>>() {
                    @Override
                    public void onChanged(List<Dish> dish) {
                        adapter.setDishes(dish);
                    }
                });
            }
        }
    }

    private void selectDish(Dish dish) {
        Intent intent = new Intent();
        intent.putExtra(DISH_ID, dish.getDishId());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void editDish(Dish dish) {
        Intent intent = new Intent(DishSelectActivity.this, DishAddEditActivity.class);
        intent.putExtra(DISH_ID, dish.getDishId());
        intent.putExtra(DISH_NAME, dish.getDishName());
        startActivityForResult(intent, EDIT_DISH_REQUEST);
//        if (isMainDishRequest) {
//            startActivityForResult(intent, EDIT_DISH_REQUEST);
//        } else {
//            startActivityForResult(intent, EDIT_DISH_REQUEST);
//        }
    }

    private void createDish() {
        Intent intent = new Intent(DishSelectActivity.this, DishAddEditActivity.class);
        startActivityForResult(intent, ADD_DISH_REQUEST);
//        if (isMainDishRequest) {
//            startActivityForResult(intent, ADD_DISH_REQUEST);
//        } else {
//            startActivityForResult(intent, ADD_DISH_REQUEST);
//        }
    }

    private void setDishType() {
        Intent intent = getIntent();
        int requestType = intent.getIntExtra(MenuViewActivity.REQUEST_TYPE, -1);
        if (requestType != -1) {
            isMainDishRequest = requestType == MenuViewActivity.SELECT_MAIN_DISH_REQUEST ? true : false;
        } else {
            isMainDishRequest = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        searchMenu = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setIconifiedByDefault(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.resetItems();
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String dishName = data.getStringExtra(DishSelectActivity.DISH_NAME);
            Dish dish = new Dish(dishName, isMainDishRequest);
            switch (requestCode) {
                case ADD_DISH_REQUEST:
                    viewModel.insert(dish);
                    Intent intent = new Intent(DishSelectActivity.this, IngredientSelectActivity.class);
                    startActivityForResult(intent, ADD_INGREDIENT_REQUEST);
                    break;
                case ADD_INGREDIENT_REQUEST:
                    // updtae dish ingredient join
                    Toast.makeText(this, "Update dish ingredient join", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT_DISH_REQUEST:
                    int dishId = data.getIntExtra(DishSelectActivity.DISH_ID, -1);
                    dish.setDishId(dishId);
                    viewModel.update(dish);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setQuery("", false);
        searchView.setIconified(true);
        invalidateOptionsMenu();
    }
}

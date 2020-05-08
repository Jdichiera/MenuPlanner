package com.example.menuplanner.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.menuplanner.adapter.MainDishSelectionAdapter;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.viewmodel.MainDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainDishSelectActivity extends AppCompatActivity {
    public static final String MAIN_DISH_ID = "com.example.menuplanner.MAIN_DISH_ID";
    public static final String MAIN_DISH_NAME = "com.example.menuplanner.MAIN_DISH_NAME";
    public static final String BOUNCE = "com.example.menuplanner.BOUNCE";
    public static final int ADD_MAIN_DISH_REQUEST = 1;
    public static final int EDIT_MAIN_DISH_REQUEST = 2;
    public MainDishSelectionAdapter adapter;
    private MainDishViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dish_select);

        FloatingActionButton buttonAddMainDish = findViewById(R.id.button_list_add_main_dish);
        buttonAddMainDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMainDish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.main_dish_select_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new MainDishSelectionAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainDishSelectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(MainDish mainDish) {
                selectMainDish(mainDish);
            }

            @Override
            public void onCheckboxClicked(MainDish mainDish) {

            }

            @Override
            public void onEditClicked(MainDish mainDish) {
                editMainDish(mainDish);
            }
        });

        viewModel = ViewModelProviders.of(this).get(MainDishViewModel.class);
        viewModel.getAllMainDishes().observe(this, new Observer<List<MainDish>>() {
            @Override
            public void onChanged(List<MainDish> mainDishes) {
                adapter.setMainDishes(mainDishes);
            }
        });
    }


    private void selectMainDish(MainDish mainDish) {
        Intent intent = new Intent();
        intent.putExtra(MAIN_DISH_ID, mainDish.getMainDishId());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void editMainDish(MainDish mainDish) {
        Intent intent = new Intent(MainDishSelectActivity.this, MainDishAddEditActivity.class);
        intent.putExtra(MAIN_DISH_ID, mainDish.getMainDishId());
        intent.putExtra(MAIN_DISH_NAME, mainDish.getMainDishTitle());
        startActivityForResult(intent, EDIT_MAIN_DISH_REQUEST);
    }

    private void createMainDish() {
        Intent intent = new Intent(MainDishSelectActivity.this, MainDishAddEditActivity.class);
        startActivityForResult(intent, ADD_MAIN_DISH_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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
            String mainDishName = data.getStringExtra(MainDishSelectActivity.MAIN_DISH_NAME);
            MainDish mainDish = new MainDish(mainDishName);

            if (requestCode == ADD_MAIN_DISH_REQUEST) {
                viewModel.insert(mainDish);
//                adapter.addMainDish(mainDish);
            } else if (requestCode == EDIT_MAIN_DISH_REQUEST) {
                int mainDishId = data.getIntExtra(MainDishSelectActivity.MAIN_DISH_ID, -1);
                mainDish.setMainDishId(mainDishId);
                viewModel.update(mainDish);
//                adapter.setMainDishName(mainDishName, mainDishId);
            }
        }
//        adapter.notifyDataSetChanged();
//        invalidateOptionsMenu();
    }

}

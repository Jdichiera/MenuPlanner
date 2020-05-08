package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.MainDishSelectionAdapter;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.viewmodel.MainDishViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainDishSelectActivity extends AppCompatActivity {
    private LiveData<List<MainDish>> mainDishes;
    public static final String MAIN_DISH_ID = "com.example.menuplanner.MAIN_DISH_ID";
    public MainDishSelectionAdapter adapter;
    private MainDishViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dish_select);

        RecyclerView recyclerView = findViewById(R.id.main_dish_select_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new MainDishSelectionAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainDishSelectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                editMainDish(position);
            }

            @Override
            public void onCheckboxClicked(int position) {
                toggleCheckbox(position);
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



    private void getMainDishes() {
//        ArrayList<MainDish> dishes = new ArrayList<>();
//
//        MainDishViewModel viewModel = ViewModelProviders.of(this).get(MainDishViewModel.class);
//        viewModel.getAllMainDishes().observe(this, new Observer<List<MainDish>>() {
//            @Override
//            public void onChanged(List<MainDish> mainDishes) {
//                this.adapter.setMainDishes(mainDishes);
//            }
//        });
//        LiveData<List<MainDish>> mainDishes = Transformations.map(viewModel.getAllMainDishes(), list -> {
//            ArrayList<MainDish> dis = new ArrayList<>();
//            for (MainDish d : list) {
//                dis.add(new MainDish(d.getMainDishTitle()));
//            }
//            return dis;
//        });
//        LiveData<List<MainDish>> mainDishes = viewModel.getAllMainDishes();
//        mainDishes.observe(this, new Observer<List<MainDish>>() {
//            @Override
//            public void onChanged(List<MainDish> mainDishes) {
//                for (MainDish dish : mainDishes) {
//                    dishes.add(new MainDish(dish.getMainDishTitle()));
//                }
////                dishes.add(new MainDish(mainDishes.get(1).getMainDishTitle()));
//            }
//        });
//        return dishes;
//        this.mainDishes = dishes;
        this.mainDishes = viewModel.getAllMainDishes();
    }

    private void buildRecyclerView() {
//        RecyclerView recyclerView = findViewById(R.id.main_dish_select_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        this.adapter = new MainDishSelectionAdapter();
//        recyclerView.setAdapter(this.adapter);
//
//        this.adapter.setOnItemClickListener(new MainDishSelectionAdapter.OnItemClickListener() {
//
//            @Override
//            public void onItemClicked(int position) {
//                editMainDish(position);
//            }
//
//            @Override
//            public void onCheckboxClicked(int position) {
//                toggleCheckbox(position);
//            }
//        });
    }

    private void editMainDish(int position) {
//        String s = this.mainDishes.get(position).getMainDishTitle();
//        Toast.makeText(MainDishSelectActivity.this, "Clicked item: " + s, Toast.LENGTH_SHORT).show();
    }

    private void toggleCheckbox(int position) {
//        MainDish mainDish = this.mainDishes.get(position);
//        if
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
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }
}

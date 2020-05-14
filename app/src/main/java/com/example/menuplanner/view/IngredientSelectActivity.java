package com.example.menuplanner.view;

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
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.DishSelectionAdapter;
import com.example.menuplanner.adapter.IngredientSelectionAdapter;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.example.menuplanner.viewmodel.IngredientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class IngredientSelectActivity extends AppCompatActivity {
    public IngredientSelectionAdapter adapter;
    private IngredientViewModel viewModel;
    private MenuItem searchMenu;
    Button save;
    public static final String INGREDIENT_ID = "com.example.menuplanner.INGREDIENT_ID";
    public static final String INGREDIENT_NAME = "com.example.menuplanner.INGREDIENT_NAME";
    public ArrayList<Integer> dishIngredientIds;
    int dishId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_select);
//        findViewById(R.id.edit_dish_checkbox).setVisibility(View.VISIBLE);
//        findViewById(R.id.edit_dish_ingredients).setVisibility(View.INVISIBLE);
        save = findViewById(R.id.button_add_save_ingredients);
        Intent intent = getIntent();
        dishId = intent.getIntExtra(DishSelectActivity.DISH_ID, -1);
        dishIngredientIds = intent.getIntegerArrayListExtra(DishSelectActivity.DISH_INGREDIENTS);

        FloatingActionButton buttonAddIngredient = findViewById(R.id.button_list_add_ingredient);
        buttonAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIngredient();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.dish_and_ingredient_select_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new IngredientSelectionAdapter();
        adapter.setDishIngredientIds(dishIngredientIds);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new IngredientSelectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Ingredient ingredient) {
            }

            @Override
            public void onCheckboxToggled(Ingredient ingredient) {
            }

            @Override
            public void onDeleteClicked(int position, Ingredient ingredient) {
                deleteIngredient(position, ingredient);
            }
        });

        viewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);
        viewModel.getAllIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                adapter.setIngredients(ingredients);
            }
        });
    }

    private void createIngredient() {
        Intent intent = new Intent();
//        intent.putExtra(INGREDIENT_ID, dish.getDishId());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void saveIngredients(View view) {
        Toast.makeText(this, "Save Ingredients", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra(DishSelectActivity.DISH_INGREDIENTS, adapter.selectedIngredients);
        intent.putIntegerArrayListExtra(DishSelectActivity.DELETED_DISH_INGREDIENTS, adapter.deletedIngredients);
        intent.putExtra(DishSelectActivity.DISH_ID, dishId);
        setResult(RESULT_OK, intent);
        finish();

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

    private void deleteIngredient(int position, Ingredient ingredient) {
        adapter.setDeletedItemCheckbox(position);
        viewModel.delete(ingredient);
        viewModel.deleteAllDishIngredients(ingredient.getIngredientId());
    }
}

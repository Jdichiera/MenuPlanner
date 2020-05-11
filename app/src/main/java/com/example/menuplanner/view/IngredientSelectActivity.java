package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.DishSelectionAdapter;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IngredientSelectActivity extends AppCompatActivity {
    public DishSelectionAdapter adapter;
    private DishViewModel viewModel;
    private MenuItem searchMenu;
    public static final String INGREDIENT_ID = "com.example.menuplanner.INGREDIENT_ID";
    public static final String INGREDIENT_NAME = "com.example.menuplanner.INGREDIENT_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_select);

        FloatingActionButton buttonAddIngredient = findViewById(R.id.button_list_add_ingredient);
        buttonAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIngredient();
            }
        });
    }

    private void createIngredient() {
        Intent intent = new Intent();
//        intent.putExtra(INGREDIENT_ID, dish.getDishId());
        setResult(RESULT_OK, intent);
        finish();
    }
}

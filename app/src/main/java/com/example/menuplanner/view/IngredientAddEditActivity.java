package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menuplanner.R;

public class IngredientAddEditActivity extends AppCompatActivity {
    private EditText ingredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_add_edit);
        ingredientName = findViewById(R.id.ingredient_add_edit_title);

        Intent intent = getIntent();
        if (intent.hasExtra(IngredientSelectActivity.INGREDIENT_ID)) {
            setTitle("Edit Ingredient");
            ingredientName.setText(intent.getStringExtra(IngredientSelectActivity.INGREDIENT_NAME));
        } else {
            setTitle("Add Ingredient");
        }
    }

    public void saveIngredient(View view) {
        String name = ingredientName.getText().toString();
        Intent callingIntent = getIntent();
        int ingredientId = callingIntent.getIntExtra(IngredientSelectActivity.INGREDIENT_ID, -1);
        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a name before saving.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(IngredientSelectActivity.INGREDIENT_NAME, name);

        if (ingredientId != -1) {
            intent.putExtra(IngredientSelectActivity.INGREDIENT_ID, ingredientId);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}

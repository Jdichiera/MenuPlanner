package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menuplanner.R;

public class DishAddEditActivity extends AppCompatActivity {
    private EditText dishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_add_edit);

        dishName = findViewById(R.id.dish_add_edit_title);

        Intent intent = getIntent();
        if (intent.hasExtra(DishSelectActivity.DISH_ID)) {
            setTitle("Edit Dish");
            dishName.setText(intent.getStringExtra(DishSelectActivity.DISH_NAME));
        } else {
            setTitle("Add Dish");
        }
    }

    public void saveDish(View view) {
        String name = dishName.getText().toString();
        Intent callingIntent = getIntent();
        int dishId = callingIntent.getIntExtra(DishSelectActivity.DISH_ID, -1);
        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a name before saving.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(DishSelectActivity.DISH_NAME, name);

        if (dishId != -1) {
            intent.putExtra(DishSelectActivity.DISH_ID, dishId);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}

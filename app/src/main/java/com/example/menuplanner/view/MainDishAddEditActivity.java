package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.MainDishSelectionAdapter;
import com.example.menuplanner.entity.MainDish;

public class MainDishAddEditActivity extends AppCompatActivity {
    private EditText mainDishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dish_add_edit);

        mainDishName = findViewById(R.id.main_dish_title);

        Intent intent = getIntent();
        if (intent.hasExtra(MainDishSelectActivity.MAIN_DISH_ID)) {
            setTitle("Edit Main Dish");
            mainDishName.setText(intent.getStringExtra(MainDishSelectActivity.MAIN_DISH_NAME));
        } else {
            setTitle("Add Main Dish");
        }
    }

    public void saveMainDish(View view) {
        String name = mainDishName.getText().toString();
        Intent callingIntent = getIntent();
        int mainDishId = callingIntent.getIntExtra(MainDishSelectActivity.MAIN_DISH_ID, -1);
        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a name before saving.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(MainDishSelectActivity.MAIN_DISH_NAME, name);

        if (mainDishId != -1) {
            intent.putExtra(MainDishSelectActivity.MAIN_DISH_ID, mainDishId);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}

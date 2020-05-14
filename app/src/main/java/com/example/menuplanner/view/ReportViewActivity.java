package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.menuplanner.R;

public class ReportViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);
        Button menuReport = findViewById(R.id.button_report_menu);
        Button ingredientReport = findViewById(R.id.button_report_ingredient);

        menuReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMenuReport();
            }
        });

        ingredientReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewIngredientReport();
            }
        });
    }

    private void viewMenuReport() {
        Intent intent = new Intent(ReportViewActivity.this, ReportViewMenuActivity.class);
        startActivity(intent);
    }

    private void viewIngredientReport() {
        Intent intent = new Intent(ReportViewActivity.this, ReportViewIngredientActivity.class);
        startActivity(intent);
    }
}

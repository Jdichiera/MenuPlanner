package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.ReportIngredientAdapter;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.viewmodel.IngredientViewModel;

import java.util.List;

public class ReportViewIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view_ingredient);

        RecyclerView recyclerView = findViewById(R.id.report_ingredient_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ReportIngredientAdapter adapter = new ReportIngredientAdapter();
        recyclerView.setAdapter(adapter);

        IngredientViewModel viewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);
        List<Integer> ingredientIds = viewModel.getNeededDishIngredientIds();
        viewModel.getNeededDishIngredients(ingredientIds).observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                adapter.setIngredients(ingredients);
            }
        });
    }
}

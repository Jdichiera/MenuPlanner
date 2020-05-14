package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ReportIngredientAdapter extends RecyclerView.Adapter<ReportIngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredients = new ArrayList<>();

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ingredientView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_item, parent, false);
        return new IngredientViewHolder(ingredientView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredientAtPosition = ingredients.get(position);
        holder.ingredientTitle.setText(ingredientAtPosition.getIngredientName());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientTitle;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTitle = itemView.findViewById(R.id.report_item_text);
        }
    }
}

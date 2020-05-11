package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.INGREDIENTS_TABLE)
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    private int ingredientId;
    private String ingredientName;

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}

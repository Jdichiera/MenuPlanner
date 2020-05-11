package com.example.menuplanner.entity;

import androidx.room.Entity;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.DISH_INGREDIENTS)
public class DishIngredients {
    public int dishId;
    public int ingredientId;
}

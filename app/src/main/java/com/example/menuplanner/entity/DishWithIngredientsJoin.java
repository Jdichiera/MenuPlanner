package com.example.menuplanner.entity;

import androidx.room.Entity;

import com.example.menuplanner.application.MenuPlanner;

@Entity(
        tableName = MenuPlanner.DISH_INGREDIENT_JOIN,
        primaryKeys = {"dishId", "ingredientId"}
        )
public class DishWithIngredientsJoin {

    private int dishId;
    private int ingredientId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }


}

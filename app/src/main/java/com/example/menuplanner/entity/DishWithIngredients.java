package com.example.menuplanner.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class DishWithIngredients {
    @Embedded public Dish dishWithIngredients;
    @Relation(
            parentColumn = "dishId",
            entityColumn = "ingredientId",
            associateBy = @Junction(DishWithIngredientsJoin.class)
    )
    public List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }
}

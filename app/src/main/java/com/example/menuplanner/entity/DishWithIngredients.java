package com.example.menuplanner.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

// https://medium.com/coding-blocks/handling-many-to-many-relationship-in-android-addb60a3a3
public class DishWithIngredients {
    @Embedded public Dish dish;
    @Relation(
            parentColumn = "dishId",
            entityColumn = "ingredientId"
    )
    public List<Ingredient> ingredientsForDish;
}

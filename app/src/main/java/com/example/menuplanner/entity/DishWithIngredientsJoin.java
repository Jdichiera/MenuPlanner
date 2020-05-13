package com.example.menuplanner.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(
        tableName = MenuPlanner.DISH_INGREDIENT_JOIN,
        primaryKeys = {"dishId", "ingredientId"})
//)
public class DishWithIngredientsJoin {
//    @PrimaryKey(autoGenerate = true)
//    private int joinId;
    private int dishId;
    private int ingredientId;

    public int getDishId() {
        return dishId;
    }

//    public int getJoinId() {
//        return joinId;
//    }
//
//    public void setJoinId(int joinId) {
//        this.joinId = joinId;
//    }

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

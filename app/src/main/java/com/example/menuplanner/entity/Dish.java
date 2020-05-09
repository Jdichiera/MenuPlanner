package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.DISH_TABLE)
public class Dish {
    @PrimaryKey(autoGenerate = true)
    private int dishId;
    private String dishName;
    private int isMainDish;

    public Dish() {}

    @Ignore
    public Dish(String dishName, boolean isMainDish) {
        this.dishName = dishName;
        this.isMainDish = isMainDish == true ? 1: 0;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getIsMainDish() {
        return isMainDish;
    }

    public void setIsMainDish(int isMainDish) {
        this.isMainDish = isMainDish;
    }

    public boolean isMainDish() {
        return this.isMainDish == 1 ? true : false;
    }

    public void setIsMainDish(boolean isMainDish) {
        if (isMainDish == true) {
            this.isMainDish = 1;
        } else {
            this.isMainDish = 0;
        }
    }
}

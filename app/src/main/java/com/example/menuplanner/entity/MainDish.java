package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.MAINS_TABLE)
public class MainDish {
    @PrimaryKey(autoGenerate = true)
    private int mainDishId;
    private String mainDishTitle;

    public MainDish(String mainDishTitle) {
        this.mainDishTitle = mainDishTitle;
    }

    public int getMainDishId() {
        return mainDishId;
    }

    public void setMainDishId(int mainDishId) {
        this.mainDishId = mainDishId;
    }

    public String getMainDishTitle() {
        return mainDishTitle;
    }

    public void setMainDishTitle(String mainDishTitle) {
        this.mainDishTitle = mainDishTitle;
    }
}

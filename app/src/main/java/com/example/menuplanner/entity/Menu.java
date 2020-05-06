package com.example.menuplanner.entity;

import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.MENUS_TABLE,
        indices = {@Index("dayId")},
        foreignKeys = {
                @ForeignKey(
                        entity = Day.class,
                        parentColumns = "dayId",
                        childColumns = "menuId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Menu {
    @PrimaryKey(autoGenerate = true)
    private int menuId;
    private int dayId;
    private int mainDishId;
    private int sideDish1Id;
    private int sideDish2Id;
    private int sideDish3Id;
    private int dessertId;

    public Menu(int mainDishId, int sideDish1Id, int sideDish2Id, int sideDish3Id) {
        this.mainDishId = mainDishId;
        this.sideDish1Id = sideDish1Id;
        this.sideDish2Id = sideDish2Id;
        this.sideDish3Id = sideDish3Id;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMainDishId() {
        return mainDishId;
    }

    public void setMainDishId(int mainDishId) {
        this.mainDishId = mainDishId;
    }

    public int getSideDish1Id() {
        return sideDish1Id;
    }

    public void setSideDish1Id(int sideDish1Id) {
        this.sideDish1Id = sideDish1Id;
    }

    public int getSideDish2Id() {
        return sideDish2Id;
    }

    public void setSideDish2Id(int sideDish2Id) {
        this.sideDish2Id = sideDish2Id;
    }

    public int getSideDish3Id() {
        return sideDish3Id;
    }

    public void setSideDish3Id(int sideDish3Id) {
        this.sideDish3Id = sideDish3Id;
    }

    public int getDessertId() {
        return dessertId;
    }

    public void setDessertId(int dessertId) {
        this.dessertId = dessertId;
    }
}

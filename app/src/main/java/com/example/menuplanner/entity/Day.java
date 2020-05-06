package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.DAYS_TABLE)
public class Day {
    @PrimaryKey(autoGenerate = true)
    private int dayId;
    private String dayTitle;
    private int menuId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Day(String dayTitle, int menuId) {
        this.dayTitle = dayTitle;
        this.menuId = menuId;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getDayTitle() {
        return dayTitle;
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }
}

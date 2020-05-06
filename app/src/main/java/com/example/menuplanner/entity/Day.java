package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.DAYS_TABLE)
public class Day {
    @PrimaryKey(autoGenerate = true)
    private int dayId;
    private String dayTitle;

    public Day(String dayTitle) {
        this.dayTitle = dayTitle;
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

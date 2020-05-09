package com.example.menuplanner.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.menuplanner.application.MenuPlanner;

@Entity(tableName = MenuPlanner.SIDES_TABLE)
public class SideDish {
    @PrimaryKey(autoGenerate = true)
    private int sideDishId;
    private String sideDishTitle;

    public SideDish() {}

    @Ignore
    public SideDish(String sideDishTitle) {
        this.sideDishTitle = sideDishTitle;
    }

    public int getSideDishId() {
        return sideDishId;
    }

    public void setSideDishId(int sideDishId) {
        this.sideDishId = sideDishId;
    }

    public String getSideDishTitle() {
        return sideDishTitle;
    }

    public void setSideDishTitle(String sideDishTitle) {
        this.sideDishTitle = sideDishTitle;
    }
}

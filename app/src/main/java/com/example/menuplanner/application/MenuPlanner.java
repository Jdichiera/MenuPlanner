package com.example.menuplanner.application;

import android.app.Application;

public class MenuPlanner extends Application {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "menu_planner_database";
    public static final String USERS_TABLE = "users_table";
    public static final String DAYS_TABLE = "days_table";
    public static final String MENUS_TABLE = "menus_table";
    public static final String DISH_TABLE = "dish_table";
    public static final String INGREDIENTS_TABLE = "ingredients_table";
    public static final String DISH_INGREDIENT_JOIN = "dish_ingredients_join_table";
    public static final String TEST_USER_NAME = "1";
    public static final String TEST_USER_PASSWORD = "1";
    public static final String[] DAYS = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    public static final String NO_MAIN_DISH_SELECTED_MESSAGE = "Click to select a main dish";
    public static final String NO_SIDE_DISH_SELECTED_MESSAGE = "Click to select a side dish";
    public static final String REPORT_NO_MAIN_DISH_SELECTED = "No main dish selected";
    public static final String REPORT_NO_SIDE_DISH_SELECTED = "No side dish selected";
}

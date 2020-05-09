package com.example.menuplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.database.MenuPlannerDatabase;
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.User;
import com.example.menuplanner.utility.DatabaseHelper;
import com.example.menuplanner.viewmodel.DayViewModel;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;
import com.example.menuplanner.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DayViewModel dayViewModel;
    UserViewModel userViewModel;
    MenuViewModel menuViewModel;
    DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MenuPlannerDatabase db = MenuPlannerDatabase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.input_login_username);
        password = findViewById(R.id.input_login_password);
        Button login = findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_initialize_database:
                Toast.makeText(this,
                        "Initializing Database: Deleting existing data. Adding test data.",
                        Toast.LENGTH_SHORT).show();
                initializeDatabase();
                return true;
            case R.id.menu_view_reports:
                viewReports();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void login() {
        if (userViewModel == null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        }

        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (userViewModel.validateLogin(name, pass)) {
            Intent intent = new Intent(LoginActivity.this, DayListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Please check your credentials and try logging in again.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    
    private void viewReports() {
        Toast.makeText(this, "View Reports", Toast.LENGTH_SHORT).show();
    }

    private void initializeDatabase() {
        deleteData();
        addData();
    }

    private void deleteData() {
        deleteSequence();
        deleteUserData();
        deleteDayData();
        deleteMenuData();
        deleteMainDishData();

    }

    private void addData() {
        addUserData();
        addDayData();
        addMenuData();
        addDishData();
    }

    private void deleteSequence() {
        if (userViewModel == null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        }

        if (userViewModel.validateLogin(MenuPlanner.TEST_USER_NAME, MenuPlanner.TEST_USER_PASSWORD)) {
            DatabaseHelper.getInstance(this).deleteSequence();
        }
    }

    private void deleteUserData() {
        if (userViewModel == null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        }

        userViewModel.deleteAllUsers();
    }

    private void deleteDayData() {
        if (dayViewModel == null) {
            dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        }

        dayViewModel.deleteAllDays();
    }

    private void deleteMenuData() {
        if (menuViewModel == null) {
            menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        }

        menuViewModel.deleteAllMenus();
    }

    private void deleteMainDishData() {
        if (dishViewModel == null) {
            dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        }

        dishViewModel.deleteAllDishes();
    }

    private void addUserData() {
        if (userViewModel == null) {
            userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        }
        User testUser = new User(MenuPlanner.TEST_USER_NAME, MenuPlanner.TEST_USER_PASSWORD);
        testUser.setUserId(1);
        userViewModel.insertUser(testUser);
    }

    private void addDayData() {
        if (dayViewModel == null) {
            dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        }

        dayViewModel.insert(new Day("Sunday", 1));
        dayViewModel.insert(new Day("Monday", 2));
        dayViewModel.insert(new Day("Tuesday", 3));
        dayViewModel.insert(new Day("Wednesday", 4));
        dayViewModel.insert(new Day("Thursday", 5));
        dayViewModel.insert(new Day("Friday", 6));
        dayViewModel.insert(new Day("Saturday", 7));

//        for (String day : MenuPlanner.DAYS) {
//            dayViewModel.insert(new Day(day));
//        }
    }

    private void addMenuData() {
        if (menuViewModel == null) {
            menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        }

        menuViewModel.insert(new com.example.menuplanner.entity.Menu(1, 8, 9, 10));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(2, 11, 12, 13));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(3, 14, 15, 16));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(4, 17, 18, 19));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(5, 20, 21, 22));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(6, 23, 24, 25));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(0, 0, 0, 0));
    }

    private void addDishData() {
        if (dishViewModel == null) {
            dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        }

        // Main dishes
        dishViewModel.insert(new Dish("Spagetti", true));
        dishViewModel.insert(new Dish("Some Sandwiches", true));
        dishViewModel.insert(new Dish("Ham and Potatoes", true));
        dishViewModel.insert(new Dish("Icecream and bread that is weird", true));
        dishViewModel.insert(new Dish("Yams with Spice", true));
        dishViewModel.insert(new Dish("Time for some meatballs", true));

        // Side dishes
        dishViewModel.insert(new Dish("Beans", false));
        dishViewModel.insert(new Dish("Rice", false));
        dishViewModel.insert(new Dish("Blue Vegetables", false));
        dishViewModel.insert(new Dish("Some Potatoes", false));
        dishViewModel.insert(new Dish("Ham as a side", false));
        dishViewModel.insert(new Dish("Chips", false));
        dishViewModel.insert(new Dish("Fries", false));
        dishViewModel.insert(new Dish("Fries and Chips", false));
        dishViewModel.insert(new Dish("Candy", false));
        dishViewModel.insert(new Dish("Fruit with sauce", false));
        dishViewModel.insert(new Dish("A fish stick Side", false));
        dishViewModel.insert(new Dish("Part of a waffle side", false));
        dishViewModel.insert(new Dish("Jello", false));
        dishViewModel.insert(new Dish("Part of a sandwich side", false));
        dishViewModel.insert(new Dish("Fruit Snacks", false));
        dishViewModel.insert(new Dish("Candy Bar", false));
        dishViewModel.insert(new Dish("Mashed Potatoes", false));
        dishViewModel.insert(new Dish("Bread with butter and salt", false));
        dishViewModel.insert(new Dish("Celery and Peanut Butter", false));


    }
}

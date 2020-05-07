package com.example.menuplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
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
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.entity.User;
import com.example.menuplanner.utility.DatabaseHelper;
import com.example.menuplanner.viewmodel.DayViewModel;
import com.example.menuplanner.viewmodel.MainDishViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;
import com.example.menuplanner.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DayViewModel dayViewModel;
    UserViewModel userViewModel;
    MenuViewModel menuViewModel;
    MainDishViewModel mainDishViewModel;

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
        addMainDishData();
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
        if (mainDishViewModel == null) {
            mainDishViewModel = ViewModelProviders.of(this).get(MainDishViewModel.class);
        }

        mainDishViewModel.deleteAllMainDishes();
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
        dayViewModel.insert(new Day("Saturday", 0));

//        for (String day : MenuPlanner.DAYS) {
//            dayViewModel.insert(new Day(day));
//        }
    }

    private void addMenuData() {
        if (menuViewModel == null) {
            menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        }

        menuViewModel.insert(new com.example.menuplanner.entity.Menu(1, 1, 1, 1));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(2, 2, 2, 2));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(3, 3, 3, 3));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(4, 4, 4, 4));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(5, 5, 5, 5));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(6, 6, 6, 6));
    }

    private void addMainDishData() {
        if (mainDishViewModel == null) {
            mainDishViewModel = ViewModelProviders.of(this).get(MainDishViewModel.class);
        }

        mainDishViewModel.insert(new MainDish("Spagetti"));
        mainDishViewModel.insert(new MainDish("Some Sandwiches"));
        mainDishViewModel.insert(new MainDish("Ham and Potatoes"));
        mainDishViewModel.insert(new MainDish("Icecream and bread that is weird"));
        mainDishViewModel.insert(new MainDish("Yams with Spice"));
        mainDishViewModel.insert(new MainDish("Time for some meatballs"));
    }
}

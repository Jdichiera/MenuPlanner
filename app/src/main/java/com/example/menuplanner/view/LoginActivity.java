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
import com.example.menuplanner.entity.User;
import com.example.menuplanner.utility.DatabaseHelper;
import com.example.menuplanner.viewmodel.DayViewModel;
import com.example.menuplanner.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DayViewModel dayViewModel;
    UserViewModel userViewModel;

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

    private void initializeDatabase() {
        deleteData();
        addData();
    }

    private void deleteData() {
        deleteSequence();
        deleteUserData();
        deleteDayData();
    }

    private void addData() {
        addUserData();
        addDayData();
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

        for (String day : MenuPlanner.DAYS) {
            dayViewModel.insert(new Day(day));
        }
    }
}

package com.example.menuplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.entity.User;
import com.example.menuplanner.viewmodel.DayViewModel;
import com.example.menuplanner.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DayViewModel dayViewModel;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        if (isValidCredential()) {
            Intent intent = new Intent(LoginActivity.this, DayListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Please check your credentials and try logging in again.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredential() {
        boolean isValid;
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(getApplicationContext()
                .getDatabasePath(MenuPlanner.DATABASE_NAME), null);
        String name = username.getText().toString();
        String pass = password.getText().toString();
        Cursor cursor = database.query(MenuPlanner.USERS_TABLE, new String[]{"userName"},
                "userName = ? AND userPassword = ?", new String[]{name, pass},
                null, null, null, null);

        isValid = (cursor != null) && (cursor.getCount() == 1);
        if (cursor != null) {
            cursor.close();
        }
        database.close();
        return isValid;
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
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(getApplicationContext()
                .getDatabasePath(MenuPlanner.DATABASE_NAME), null);
        database.execSQL("delete from sqlite_sequence");
        database.close();
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

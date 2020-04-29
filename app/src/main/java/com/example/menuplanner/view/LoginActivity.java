package com.example.menuplanner.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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
import com.example.menuplanner.entity.User;
import com.example.menuplanner.viewmodel.UserViewModel;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {
    UserViewModel userViewModel;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);


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
            case R.id.menu_add_sample_data:
                Toast.makeText(this,
                        "Adding sample data and test user",
                        Toast.LENGTH_SHORT).show();
                addData();
                return true;
            case R.id.menu_delete_all_data:
                Toast.makeText(this,
                        "Deleting sample data and test user",
                        Toast.LENGTH_SHORT).show();
                deleteData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addData() {
        User user = new User("TestUser", "123");
        userViewModel.insert(user);
    }

    private void deleteData() {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(getApplicationContext()
                .getDatabasePath("menu_planner_database"), null);
        database.execSQL("DELETE FROM days_table");
        database.execSQL("delete from sqlite_sequence where name = 'days_table'");
        database.execSQL("DELETE FROM users_table");
        database.execSQL("delete from sqlite_sequence where name = 'users_table'");
    }

    private void login() {

        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(getApplicationContext()
                .getDatabasePath("menu_planner_database"), null);
        String name = username.getText().toString();
        String pass = password.getText().toString();
        boolean valid = database.rawQuery("SELECT userName FROM users_table WHERE userName = :name AND userPassword = :pass", null) != null;




//        boolean valid = userViewModel.validateUser(username.getText().toString(), password.getText().toString());
        Toast.makeText(this,
                "Login : " + String.valueOf(valid),
                Toast.LENGTH_SHORT).show();
    }
}

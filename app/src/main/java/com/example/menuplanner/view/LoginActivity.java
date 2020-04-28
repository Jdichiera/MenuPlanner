package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menuplanner.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.input_login_username);
        EditText password = findViewById(R.id.input_login_password);
        Button login = findViewById(R.id.button_login);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    
    private void login() {
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
    }
}

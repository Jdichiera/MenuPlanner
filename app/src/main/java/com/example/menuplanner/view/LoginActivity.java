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
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Ingredient;
import com.example.menuplanner.entity.User;
import com.example.menuplanner.utility.DatabaseHelper;
import com.example.menuplanner.viewmodel.DayViewModel;
import com.example.menuplanner.viewmodel.DishViewModel;
import com.example.menuplanner.viewmodel.IngredientViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;
import com.example.menuplanner.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DayViewModel dayViewModel;
    UserViewModel userViewModel;
    MenuViewModel menuViewModel;
    DishViewModel dishViewModel;
    IngredientViewModel ingredientViewModel;

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
        deleteIngredientData();
        deleteDishIngredientJoinData();

    }

    private void addData() {
        addUserData();
        addDayData();
        addMenuData();
        addDishData();
        addIngredientData();
        addDishIngredientJoinData();
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

    private void deleteIngredientData() {
        if (ingredientViewModel == null) {
            ingredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);
        }

        ingredientViewModel.deleteAllIngredients();
    }

    private void deleteDishIngredientJoinData() {
        if (dishViewModel == null) {
            dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        }
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
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(6, 11, 12, 13));
        menuViewModel.insert(new com.example.menuplanner.entity.Menu(0, 0, 0, 0));
    }

    private void addDishData() {
        if (dishViewModel == null) {
            dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        }

        // Main dishes
        dishViewModel.insert(new Dish("Spagetti", true));
        dishViewModel.insert(new Dish("Tuna Melts", true));
        dishViewModel.insert(new Dish("Ham and Potatoes", true));
        dishViewModel.insert(new Dish("Chili", true));
        dishViewModel.insert(new Dish("Yams with Spice", true));
        dishViewModel.insert(new Dish("Salad", true));

//        dishViewModel.insert(new Dish("1", true));
//        dishViewModel.insert(new Dish("2", true));
//        dishViewModel.insert(new Dish("3", true));
//        dishViewModel.insert(new Dish("4", true));
//        dishViewModel.insert(new Dish("5", true));
//        dishViewModel.insert(new Dish("6", true));

        // Side dishes
//        dishViewModel.insert(new Dish("?-?", false));
//        dishViewModel.insert(new Dish("1-1", false));
//        dishViewModel.insert(new Dish("1-2", false));
//        dishViewModel.insert(new Dish("1-3", false));
//        dishViewModel.insert(new Dish("2-1", false));
//        dishViewModel.insert(new Dish("2-2", false));
//        dishViewModel.insert(new Dish("2-3", false));
//        dishViewModel.insert(new Dish("3-1", false));
//        dishViewModel.insert(new Dish("3-2", false));
//        dishViewModel.insert(new Dish("3-3", false));
//        dishViewModel.insert(new Dish("4-1", false));
//        dishViewModel.insert(new Dish("4-2", false));
//        dishViewModel.insert(new Dish("4-3", false));
//        dishViewModel.insert(new Dish("5-1", false));
//        dishViewModel.insert(new Dish("5-2", false));
//        dishViewModel.insert(new Dish("5-3", false));
//        dishViewModel.insert(new Dish("6-1", false));
//        dishViewModel.insert(new Dish("6-2", false));
//        dishViewModel.insert(new Dish("6-3", false));
//        dishViewModel.insert(new Dish("7-1", false));
//        dishViewModel.insert(new Dish("7-2", false));
//        dishViewModel.insert(new Dish("7-3", false));
//        dishViewModel.insert(new Dish("7-3", false));

        dishViewModel.insert(new Dish("Baked Beans", false));   // 7
        dishViewModel.insert(new Dish("Rice Pilaf", false));   //8
        dishViewModel.insert(new Dish("Mixed Vegetables", false));    // 9
        dishViewModel.insert(new Dish("Rolls and Butter", false));   // 10
        dishViewModel.insert(new Dish("Coleslaw", false));   // 11
        dishViewModel.insert(new Dish("BBQ Chips", false));   // 12
        dishViewModel.insert(new Dish("Fries", false));   // 13
        dishViewModel.insert(new Dish("Greens", false));   // 14
        dishViewModel.insert(new Dish("Pasta Salad", false));   // 15
        dishViewModel.insert(new Dish("Fruit", false));   // 16
        dishViewModel.insert(new Dish("Potato Salad", false));   // 17
        dishViewModel.insert(new Dish("Squash", false));   // 18
        dishViewModel.insert(new Dish("Side Salad", false));   // 19
        dishViewModel.insert(new Dish("Mashed Potatoes", false));   // 20
        dishViewModel.insert(new Dish("Pickled Cabbage", false));   // 21
    }

    private void addIngredientData() {
        if (ingredientViewModel == null) {
            ingredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);
        }

        ingredientViewModel.insert(new Ingredient("Salt")); // 1
        ingredientViewModel.insert(new Ingredient("Butter")); // 2
        ingredientViewModel.insert(new Ingredient("Pepper")); // 3
        ingredientViewModel.insert(new Ingredient("Flour")); // 4
        ingredientViewModel.insert(new Ingredient("Spice")); // 5
        ingredientViewModel.insert(new Ingredient("Sugar")); // 6
        ingredientViewModel.insert(new Ingredient("Noodles")); //7
        ingredientViewModel.insert(new Ingredient("Cinnamon")); // 8
        ingredientViewModel.insert(new Ingredient("Banana extract")); // 9
        ingredientViewModel.insert(new Ingredient("Vanilla extract")); // 10
        ingredientViewModel.insert(new Ingredient("Marinara Sauce")); // 11
        ingredientViewModel.insert(new Ingredient("Tuna Fish")); // 12
        ingredientViewModel.insert(new Ingredient("Bread")); // 13
        ingredientViewModel.insert(new Ingredient("Ham")); // 14
        ingredientViewModel.insert(new Ingredient("Potatoes")); // 15
        ingredientViewModel.insert(new Ingredient("Tomato Sauce")); // 16
        ingredientViewModel.insert(new Ingredient("Chili Powder")); // 17
        ingredientViewModel.insert(new Ingredient("Yams")); // 18
        ingredientViewModel.insert(new Ingredient("Spice 1")); // 19
        ingredientViewModel.insert(new Ingredient("Spice 2")); // 20
        ingredientViewModel.insert(new Ingredient("Cucumbers")); // 21
        ingredientViewModel.insert(new Ingredient("Salad Dressing")); // 22
        ingredientViewModel.insert(new Ingredient("Lettuce")); // 23
        ingredientViewModel.insert(new Ingredient("Rice")); // 24
        ingredientViewModel.insert(new Ingredient("Brown Sugar")); // 25
        ingredientViewModel.insert(new Ingredient("Beans")); // 26
        ingredientViewModel.insert(new Ingredient("Mixed vegetables")); // 27
        ingredientViewModel.insert(new Ingredient("Dinner Rolls")); // 28
        ingredientViewModel.insert(new Ingredient("Cabbage")); // 29
        ingredientViewModel.insert(new Ingredient("Mayonnaise")); // 30
        ingredientViewModel.insert(new Ingredient("BBQ Chips")); // 31
        ingredientViewModel.insert(new Ingredient("Greens")); // 32
        ingredientViewModel.insert(new Ingredient("Oil")); // 33
        ingredientViewModel.insert(new Ingredient("Fruit")); // 34
        ingredientViewModel.insert(new Ingredient("Squash")); // 35
        ingredientViewModel.insert(new Ingredient("Pickling Spice")); // 35
    }

    private void addDishIngredientJoinData() {
        if (dishViewModel == null) {
            dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        }

        dishViewModel.insertDishIngredients(1, 3);
        dishViewModel.insertDishIngredients(1, 11);
        dishViewModel.insertDishIngredients(1, 7);
        dishViewModel.insertDishIngredients(1, 1);
        dishViewModel.insertDishIngredients(2, 13);
        dishViewModel.insertDishIngredients(2, 2);
        dishViewModel.insertDishIngredients(2, 12);
        dishViewModel.insertDishIngredients(2, 1);
        dishViewModel.insertDishIngredients(2, 3);
        dishViewModel.insertDishIngredients(3, 15);
        dishViewModel.insertDishIngredients(3, 2);
        dishViewModel.insertDishIngredients(3, 1);
        dishViewModel.insertDishIngredients(3, 3);
        dishViewModel.insertDishIngredients(3, 4);
        dishViewModel.insertDishIngredients(3, 14);
        dishViewModel.insertDishIngredients(4, 1);
        dishViewModel.insertDishIngredients(4, 3);
        dishViewModel.insertDishIngredients(4, 4);
        dishViewModel.insertDishIngredients(4, 5);
        dishViewModel.insertDishIngredients(4, 16);
        dishViewModel.insertDishIngredients(4, 17);
        dishViewModel.insertDishIngredients(5, 1);
        dishViewModel.insertDishIngredients(5, 2);
        dishViewModel.insertDishIngredients(5, 3);
        dishViewModel.insertDishIngredients(5, 5);
        dishViewModel.insertDishIngredients(5, 18);
        dishViewModel.insertDishIngredients(5, 19);
        dishViewModel.insertDishIngredients(5, 20);
        dishViewModel.insertDishIngredients(6, 21);
        dishViewModel.insertDishIngredients(6, 22);
        dishViewModel.insertDishIngredients(6, 23);
        dishViewModel.insertDishIngredients(7, 1);
        dishViewModel.insertDishIngredients(7, 26);
        dishViewModel.insertDishIngredients(8, 1);
        dishViewModel.insertDishIngredients(8, 2);
        dishViewModel.insertDishIngredients(8, 3);
        dishViewModel.insertDishIngredients(8, 24);
        dishViewModel.insertDishIngredients(9, 25);
        dishViewModel.insertDishIngredients(10, 2);
        dishViewModel.insertDishIngredients(10, 28);
        dishViewModel.insertDishIngredients(11, 1);
        dishViewModel.insertDishIngredients(11, 2);
        dishViewModel.insertDishIngredients(11, 2);
        dishViewModel.insertDishIngredients(11, 29);
        dishViewModel.insertDishIngredients(11, 30);
        dishViewModel.insertDishIngredients(12, 30);
        dishViewModel.insertDishIngredients(13, 1);
        dishViewModel.insertDishIngredients(13, 15);
        dishViewModel.insertDishIngredients(14, 32);
        dishViewModel.insertDishIngredients(14, 1);
        dishViewModel.insertDishIngredients(14, 2);
        dishViewModel.insertDishIngredients(14, 3);
        dishViewModel.insertDishIngredients(15, 1);
        dishViewModel.insertDishIngredients(15, 2);
        dishViewModel.insertDishIngredients(15, 3);
        dishViewModel.insertDishIngredients(15, 33);
        dishViewModel.insertDishIngredients(15, 7);
        dishViewModel.insertDishIngredients(16, 34);
        dishViewModel.insertDishIngredients(17, 1);
        dishViewModel.insertDishIngredients(17, 3);
        dishViewModel.insertDishIngredients(17, 15);
        dishViewModel.insertDishIngredients(17, 30);
        dishViewModel.insertDishIngredients(18, 35);
        dishViewModel.insertDishIngredients(19, 23);
        dishViewModel.insertDishIngredients(19, 21);
        dishViewModel.insertDishIngredients(19, 22);
        dishViewModel.insertDishIngredients(20, 1);
        dishViewModel.insertDishIngredients(20, 2);
        dishViewModel.insertDishIngredients(20, 3);
        dishViewModel.insertDishIngredients(20, 15);
        dishViewModel.insertDishIngredients(21, 35);
        dishViewModel.insertDishIngredients(21, 29);

    }
}

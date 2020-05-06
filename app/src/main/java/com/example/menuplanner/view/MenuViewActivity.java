package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.factory.MenuViewModelFactory;
import com.example.menuplanner.viewmodel.MainDishViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;

import static androidx.lifecycle.ViewModelProviders.of;

public class MenuViewActivity extends AppCompatActivity {
    private Menu menu;
    private TextView dayTitle;
    private TextView mainDishTitle;
    private TextView sideDish1Title;
    private TextView sideDish2Title;
    private TextView sideDish3Title;
    private ImageView mainDishDelete;
    private ImageView sideDish1Delete;
    private ImageView sideDish2Delete;
    private ImageView sideDish3Delete;

    MenuViewModel menuViewModel;
    private MainDishViewModel mainDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);

        dayTitle = findViewById(R.id.title_day);
        mainDishTitle = findViewById(R.id.title_main_dish);
        sideDish1Title = findViewById(R.id.title_side_dish_1);
        sideDish2Title = findViewById(R.id.title_side_dish_2);
        sideDish3Title = findViewById(R.id.title_side_dish_3);

        mainDishDelete = findViewById(R.id.main_dish_delete);
        sideDish1Delete = findViewById(R.id.side_dish__1_delete);
        sideDish2Delete = findViewById(R.id.side_dish__2_delete);
        sideDish3Delete = findViewById(R.id.side_dish__3_delete);

        Intent intent = getIntent();
        String dayName = intent.getStringExtra(DayListActivity.DAY_NAME);
        int menuId = intent.getIntExtra(DayListActivity.MENU_ID, -1);

//        menuViewModel = ViewModelProviders.of(this,
//                new MenuViewModelFactory(this.getApplication(), menuId))
//                .get(MenuViewModel.class);
        menuViewModel = of(this).get(MenuViewModel.class);
        mainDishViewModel = of(this).get(MainDishViewModel.class);
        final LifecycleOwner owner = this;

        if (menuId != -1 || menuId != 0) {
            menuViewModel.getMenu(menuId).observe(owner, new Observer<Menu>() {
                @Override
                public void onChanged(Menu menu) {
                    if (menu != null) {
                        mainDishViewModel.getMainDish(menu.getMainDishId()).observe(owner, new Observer<MainDish>() {
                            @Override
                            public void onChanged(MainDish mainDish) {
                                mainDishTitle.setText(mainDish.getMainDishTitle());
                            }
                        });
                    }
                }
            });
        } else {
            Toast.makeText(this, "Could not load menu :" + menuId, Toast.LENGTH_SHORT).show();
        }

        mainDishDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Main Dish", Toast.LENGTH_SHORT).show();
            }
        });

        sideDish1Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish 1", Toast.LENGTH_SHORT).show();
            }
        });

        sideDish2Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish 2", Toast.LENGTH_SHORT).show();
            }
        });

        sideDish3Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish3", Toast.LENGTH_SHORT).show();
            }
        });

        dayTitle.setText(dayName);
        mainDishTitle.setText("Spagetti Main");
        sideDish1Title.setText("Beans Side 1");
        sideDish2Title.setText("Corn Side 2");
        sideDish3Title.setText("Bread Side 3");
    }
}

package com.example.menuplanner.view;

import androidx.annotation.Nullable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.factory.MenuViewModelFactory;
import com.example.menuplanner.viewmodel.MainDishViewModel;
import com.example.menuplanner.viewmodel.MenuViewModel;

import static androidx.lifecycle.ViewModelProviders.of;

public class MenuViewActivity extends AppCompatActivity {
    private int menuId;
    private TextView dayTitle;
    private TextView mainDishTitle;
    private TextView sideDish1Title;
    private TextView sideDish2Title;
    private TextView sideDish3Title;
    private RelativeLayout mainDishCard;
    private RelativeLayout sideDish1Card;
    private RelativeLayout sideDish2Card;
    private RelativeLayout sideDish3Card;
    private ImageView mainDishDelete;
    private ImageView sideDish1Delete;
    private ImageView sideDish2Delete;
    private ImageView sideDish3Delete;
//    private ImageView nextDayArrow;
//    private ImageView previousDayArrow;

    public static final int SELECT_MAIN_DISH_REQUEST = 1;
    public static final int SELECT_SIDE_DISH_1_REQUEST = 2;
    public static final int SELECT_SIDE_DISH_2_REQUEST = 3;
    public static final int SELECT_SIDE_DISH_3_REQUEST = 4;

    MenuViewModel menuViewModel;
    private MainDishViewModel mainDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);
        final LifecycleOwner lifeCycleOwner = this;

        dayTitle = findViewById(R.id.title_day);
        mainDishTitle = findViewById(R.id.title_main_dish);
        sideDish1Title = findViewById(R.id.title_side_dish_1);
        sideDish2Title = findViewById(R.id.title_side_dish_2);
        sideDish3Title = findViewById(R.id.title_side_dish_3);

        mainDishCard = findViewById(R.id.card_main_dish);
        sideDish1Card = findViewById(R.id.card_side_1);
        sideDish2Card = findViewById(R.id.card_side_2);
        sideDish3Card = findViewById(R.id.card_side_3);

        mainDishDelete = findViewById(R.id.main_dish_delete);
        sideDish1Delete = findViewById(R.id.side_dish__1_delete);
        sideDish2Delete = findViewById(R.id.side_dish__2_delete);
        sideDish3Delete = findViewById(R.id.side_dish__3_delete);

//        nextDayArrow = findViewById(R.id.arrow_next_day);
//        previousDayArrow = findViewById(R.id.arrow_previous_day);

        Intent intent = getIntent();
        String dayName = intent.getStringExtra(DayListActivity.DAY_NAME);
        menuId = intent.getIntExtra(DayListActivity.MENU_ID, -1);

        menuViewModel = of(this).get(MenuViewModel.class);
        mainDishViewModel = of(this).get(MainDishViewModel.class);

        if (menuId > 0) {
            menuViewModel.getMenu(menuId).observe(lifeCycleOwner, new Observer<Menu>() {
                @Override
                public void onChanged(Menu menu) {
                    observeMainDish(menu, lifeCycleOwner);
                    observeSideDish1(menu, lifeCycleOwner);
                    observeSideDish2(menu, lifeCycleOwner);
                    observeSideDish3(menu, lifeCycleOwner);
                }
            });
        } else {
            setupNoMenu();
            Toast.makeText(this, "Could not load menu :" + menuId, Toast.LENGTH_SHORT).show();
        }

        dayTitle.setText(dayName);
        setClickListeners();

    }

    private void setupNoMenu() {
        mainDishTitle.setText(MenuPlanner.NO_MAIN_DISH);
        sideDish1Title.setText(MenuPlanner.NO_SIDE_DISH);
        sideDish2Title.setText(MenuPlanner.NO_SIDE_DISH);
        sideDish3Title.setText(MenuPlanner.NO_SIDE_DISH);

        mainDishDelete.setVisibility(View.INVISIBLE);
        sideDish1Delete.setVisibility(View.INVISIBLE);
        sideDish2Delete.setVisibility(View.INVISIBLE);
        sideDish3Delete.setVisibility(View.INVISIBLE);


    }
//
//    private void setClickListeners() {
////        setDayClickListeners();
//        setClickListeners();
//    }

//    private void setDayClickListeners() {
//        nextDayArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MenuViewActivity.this, "Delete Main Dish", Toast.LENGTH_SHORT).show();
//            }
//        });
//        previousDayArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MenuViewActivity.this, "Delete Main Dish", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void setClickListeners() {
        mainDishCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MenuViewActivity.this, "Delete Main Dish", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuViewActivity.this, MainDishSelectActivity.class);
            startActivityForResult(intent, SELECT_MAIN_DISH_REQUEST);
            }
        });
        sideDish1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish 1", Toast.LENGTH_SHORT).show();
            }
        });
        sideDish2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish 2", Toast.LENGTH_SHORT).show();
            }
        });
        sideDish3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuViewActivity.this, "Delete Side Dish3", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void observeMainDish(Menu menu, LifecycleOwner lifeCycleOwner) {
        mainDishViewModel.getMainDish(menu.getMainDishId()).observe(lifeCycleOwner, new Observer<MainDish>() {
            @Override
            public void onChanged(MainDish mainDish) {
                if (mainDish != null) {
                    mainDishTitle.setText(mainDish.getMainDishTitle());
                } else {
                    mainDishTitle.setText(MenuPlanner.NO_MAIN_DISH);
                }
            }
        });
    }

    private void observeSideDish1(Menu menu, LifecycleOwner lifecycleOwner) {
    }

    private void observeSideDish2(Menu menu, LifecycleOwner lifecycleOwner) {
    }

    private void observeSideDish3(Menu menu, LifecycleOwner lifecycleOwner) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == SELECT_MAIN_DISH_REQUEST) {
            Intent intent = getIntent();
            int mainDishId = intent.getIntExtra(MainDishSelectActivity.MAIN_DISH_ID, -1);
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Menu menu = new Menu();
            menu.setMenuId(menuId);
            menu.setMainDishId(mainDishId);
            menuViewModel.update(menu);
        }
    }
}

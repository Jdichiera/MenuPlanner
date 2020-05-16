package com.example.menuplanner.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Menu;
import com.example.menuplanner.viewmodel.DishViewModel;
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

    private int mainDishId;
    private int sideDish1Id;
    private int sideDish2Id;
    private int sideDish3Id;

    public static final String REQUEST_TYPE = "com.example.menuplanner.REQUEST_TYPE";
    public static final int SELECT_MAIN_DISH_REQUEST = 1;
    public static final int SELECT_SIDE_DISH_1_REQUEST = 2;
    public static final int SELECT_SIDE_DISH_2_REQUEST = 3;
    public static final int SELECT_SIDE_DISH_3_REQUEST = 4;


    MenuViewModel menuViewModel;
    private DishViewModel dishViewModel;

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

        Intent intent = getIntent();
        String dayName = intent.getStringExtra(DayListActivity.DAY_NAME);
        menuId = intent.getIntExtra(DayListActivity.MENU_ID, -1);

        menuViewModel = of(this).get(MenuViewModel.class);
        dishViewModel = of(this).get(DishViewModel.class);

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
            Toast.makeText(this, "Could not load menu :" + menuId, Toast.LENGTH_SHORT).show();
        }

        dayTitle.setText("Menu for " + dayName);
        setClickListeners();

    }

    private void setEmptyDishText(TextView textview, boolean isMainDish) {
        if (isMainDish) {
            textview.setText(MenuPlanner.NO_MAIN_DISH_SELECTED_MESSAGE);
        } else {
            textview.setText(MenuPlanner.NO_SIDE_DISH_SELECTED_MESSAGE);
        }
    }

    private void setClickListeners() {
        mainDishCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuViewActivity.this, DishSelectActivity.class);
                intent.putExtra(REQUEST_TYPE, SELECT_MAIN_DISH_REQUEST);
                startActivityForResult(intent, SELECT_MAIN_DISH_REQUEST);
            }
        });
        sideDish1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuViewActivity.this, DishSelectActivity.class);
                intent.putExtra(REQUEST_TYPE, SELECT_SIDE_DISH_1_REQUEST);
                startActivityForResult(intent, SELECT_SIDE_DISH_1_REQUEST);
            }
        });
        sideDish2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuViewActivity.this, DishSelectActivity.class);
                intent.putExtra(REQUEST_TYPE, SELECT_SIDE_DISH_2_REQUEST);
                startActivityForResult(intent, SELECT_SIDE_DISH_2_REQUEST);
            }
        });
        sideDish3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuViewActivity.this, DishSelectActivity.class);
                intent.putExtra(REQUEST_TYPE, SELECT_SIDE_DISH_3_REQUEST);
                startActivityForResult(intent, SELECT_SIDE_DISH_3_REQUEST);
            }
        });
    }

    private void observeMainDish(Menu menu, LifecycleOwner lifecycleOwner) {
        dishViewModel.getDish(menu.getMainDishId()).observe(lifecycleOwner, new Observer<Dish>() {
            @Override
            public void onChanged(Dish dish) {
                if (dish != null) {
                    mainDishTitle.setText(dish.getDishName());
                    mainDishId = dish.getDishId();
                } else {
                    setEmptyDishText(mainDishTitle, true);
                }
            }
        });
    }

    private void observeSideDish1(Menu menu, LifecycleOwner lifecycleOwner) {
        dishViewModel.getDish(menu.getSideDish1Id()).observe(lifecycleOwner, new Observer<Dish>() {
            @Override
            public void onChanged(Dish dish) {
                if (dish != null) {
                    sideDish1Title.setText(dish.getDishName());
                    sideDish1Id = dish.getDishId();
                } else {
                    setEmptyDishText(sideDish1Title, false);
                }
            }
        });
    }

    private void observeSideDish2(Menu menu, LifecycleOwner lifecycleOwner) {
        dishViewModel.getDish(menu.getSideDish2Id()).observe(lifecycleOwner, new Observer<Dish>() {
            @Override
            public void onChanged(Dish dish) {
                if (dish != null) {
                    sideDish2Title.setText(dish.getDishName());
                    sideDish2Id = dish.getDishId();
                } else {
                    setEmptyDishText(sideDish2Title, false);
                }
            }
        });
    }

    private void observeSideDish3(Menu menu, LifecycleOwner lifecycleOwner) {
        dishViewModel.getDish(menu.getSideDish3Id()).observe(lifecycleOwner, new Observer<Dish>() {
            @Override
            public void onChanged(Dish dish) {
                if (dish != null) {
                    sideDish3Title.setText(dish.getDishName());
                    sideDish3Id = dish.getDishId();
                } else {
                    setEmptyDishText(sideDish3Title, false);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            int dishId = data.getIntExtra(DishSelectActivity.DISH_ID, -1);
            Menu menu = new Menu(mainDishId, sideDish1Id, sideDish2Id, sideDish3Id);
            menu.setMenuId(menuId);

            switch (requestCode) {
                case SELECT_MAIN_DISH_REQUEST:
                    menu.setMainDishId(dishId);
                    break;
                case SELECT_SIDE_DISH_1_REQUEST:
                    menu.setSideDish1Id(dishId);
                    break;
                case SELECT_SIDE_DISH_2_REQUEST:
                    menu.setSideDish2Id(dishId);
                    break;
                case SELECT_SIDE_DISH_3_REQUEST:
                    menu.setSideDish3Id(dishId);
                    break;
            }

            menuViewModel.update(menu);
        }
    }
}

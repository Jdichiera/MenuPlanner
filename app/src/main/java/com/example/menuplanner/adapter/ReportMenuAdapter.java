package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.Dish;
import com.example.menuplanner.entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class ReportMenuAdapter extends RecyclerView.Adapter<ReportMenuAdapter.MenuViewHolder> {
    private List<com.example.menuplanner.entity.Menu> menus = new ArrayList<>();
    private List<Dish> dishes = new ArrayList<>();

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_menu_item, parent, false);
        return new MenuViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.dayTitle.setText(MenuPlanner.DAYS[position]);
        Menu menuAtPosition = menus.get(position);
        Dish mainDish = getDishFromDishes(menuAtPosition.getMainDishId());
        Dish sideDish1 = getDishFromDishes(menuAtPosition.getSideDish1Id());
        Dish sideDish2 = getDishFromDishes(menuAtPosition.getSideDish2Id());
        Dish sideDish3 = getDishFromDishes(menuAtPosition.getSideDish3Id());

        if (mainDish != null) {
            holder.mainDishTitle.setText(mainDish.getDishName());
        } else {
            holder.mainDishTitle.setText(MenuPlanner.REPORT_NO_MAIN_DISH_SELECTED);
        }
        if (sideDish1 != null) {
            holder.sideDish1Title.setText(sideDish1.getDishName());
        } else {
            holder.sideDish1Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
        }
        if (sideDish2 != null) {
            holder.sideDish2Title.setText(sideDish2.getDishName());
        } else {
            holder.sideDish2Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
        }
        if (sideDish3 != null) {
            holder.sideDish3Title.setText(sideDish3.getDishName());
        } else {
            holder.sideDish3Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
        }




//        String mainDishName = null;
//        String sideDish1Name = null;
//        String sideDish2Name = null;
//        String sideDish3Name = null;
//        com.example.menuplanner.entity.
//
//        if (menuAtPosition.getMainDishId() - 1 > 0) {
//            mainDishName = dishes.get(menuAtPosition.getMainDishId() -1).getDishName();
//        }
//        if (menuAtPosition.getSideDish1Id() - 1 > 0) {
//            sideDish1Name = dishes.get(menuAtPosition.getSideDish1Id() -1).getDishName();
//        }
//        if (menuAtPosition.getSideDish2Id() - 1 > 0) {
//            sideDish2Name = dishes.get(menuAtPosition.getSideDish1Id() -1).getDishName();
//        }
//        if (menuAtPosition.getSideDish3Id() - 1 > 0) {
//            sideDish3Name = dishes.get(menuAtPosition.getSideDish3Id() -1).getDishName();
//        }
//
//        if (mainDishName != null) {
//            holder.mainDishTitle.setText(mainDishName);
//        } else {
//            holder.mainDishTitle.setText(MenuPlanner.REPORT_NO_MAIN_DISH_SELECTED);
//        }
//        if (sideDish1Name != null) {
//            holder.sideDish1Title.setText(sideDish1Name);
//        } else {
//            holder.sideDish1Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
//        }
//        if (sideDish2Name != null) {
//            holder.sideDish2Title.setText(sideDish2Name);
//        } else {
//            holder.sideDish2Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
//        }
//        if (sideDish3Name != null) {
//            holder.sideDish3Title.setText(sideDish3Name);
//        } else {
//            holder.sideDish3Title.setText(MenuPlanner.REPORT_NO_SIDE_DISH_SELECTED);
//        }
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView dayTitle;
        private TextView mainDishTitle;
        private TextView sideDish1Title;
        private TextView sideDish2Title;
        private TextView sideDish3Title;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTitle = itemView.findViewById(R.id.report_menu_day_title);
            mainDishTitle = itemView.findViewById(R.id.report_menu_main_title);
            sideDish1Title = itemView.findViewById(R.id.report_menu_side1_title);
            sideDish2Title = itemView.findViewById(R.id.report_menu_side2_title);
            sideDish3Title = itemView.findViewById(R.id.report_menu_side3_title);
        }
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
        notifyDataSetChanged();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    private Dish getDishFromDishes(int dishId) {
        Dish returnDish = null;
        for (Dish dish : this.dishes) {
            if (dish.getDishId() == dishId) {
                returnDish = dish;
            }
        }

        return returnDish;
    }
}

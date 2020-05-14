package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.Dish;

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
        com.example.menuplanner.entity.Menu menuAtPosition = menus.get(position);
        holder.mainDishTitle.setText(dishes.get(menuAtPosition.getMainDishId()).getDishName());
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView mainDishTitle;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mainDishTitle = itemView.findViewById(R.id.report_menu_main_title);
        }
    }
}

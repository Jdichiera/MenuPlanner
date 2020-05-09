package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishSelectionAdapter
        extends RecyclerView.Adapter<DishSelectionAdapter.DishViewHolder>
        implements Filterable {
    private ArrayList<Dish> allDishes;
    private List<Dish> displayedDishes;
    private DishSelectionAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(DishSelectionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    class DishViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        ImageView edit;

        DishViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.dishName = itemView.findViewById((R.id.dish_title));
            this.edit = itemView.findViewById(R.id.edit_dish);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(displayedDishes.get(position));
                        }
                    }
                }
            });
            this.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClicked(displayedDishes.get(position));
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_item, parent, false);
        return new DishViewHolder(view, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = this.displayedDishes.get(position);
        holder.dishName.setText(dish.getDishName());
    }

    @Override
    public Filter getFilter() {
        return this.dishFilter;
    }

    @Override
    public int getItemCount() {
        if (this.displayedDishes != null) {
            return this.displayedDishes.size();
        } else {
            return 0;
        }
    }

    private Filter dishFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Dish> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allDishes);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Dish dish : displayedDishes) {
                    if (dish.getDishName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(dish);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            displayedDishes.clear();
            displayedDishes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void setDishes(List<Dish> dishes) {
        this.displayedDishes = dishes;
        this.allDishes = new ArrayList<>(dishes);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClicked(Dish dish);
        void onEditClicked(Dish dish);
    }
}

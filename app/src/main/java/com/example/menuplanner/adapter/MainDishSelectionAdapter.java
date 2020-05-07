package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.MainDish;

import java.util.ArrayList;
import java.util.List;

public class MainDishSelectionAdapter extends RecyclerView.Adapter<MainDishSelectionAdapter.MainDishViewHolder> implements Filterable {
    private ArrayList<MainDish> mainDishes;
    private OnItemClickListener listener;
    private List<MainDish> allMainDishes;

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onCheckboxClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class MainDishViewHolder extends RecyclerView.ViewHolder {
        public TextView mainDishName;
        public CheckBox checkbox;

        public MainDishViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.mainDishName = itemView.findViewById(R.id.dish_title);
            this.checkbox = itemView.findViewById(R.id.dish_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(position);
                        }
                    }
                }
            });
            this.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCheckboxClicked(position);
                        }
                    }
                }
            });
        }
    }

    public MainDishSelectionAdapter(ArrayList<MainDish> mainDishes) {
//        this.mainDishes = mainDishes;
//        this.allMainDishes = new ArrayList<>(mainDishes);
    }

    public MainDishSelectionAdapter(LiveData<List<MainDish>> mainDishes) {
        this.mainDishes = new ArrayList<>(mainDishes.getValue());
        this.allMainDishes = new ArrayList<>(mainDishes.getValue());
    }

    @NonNull
    @Override
    public MainDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        MainDishViewHolder viewHolder = new MainDishViewHolder(view, this.listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainDishViewHolder holder, int position) {
        MainDish mainDish = this.mainDishes.get(position);
        holder.mainDishName.setText(mainDish.getMainDishTitle());
        holder.checkbox.setChecked(false);
    }


    @Override
    public Filter getFilter() {
        return mainDishFilter;
    }

    @Override
    public int getItemCount() {
        return this.mainDishes.size();
    }

    private Filter mainDishFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MainDish> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allMainDishes);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (MainDish mainDish : allMainDishes) {
                    if (mainDish.getMainDishTitle().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(mainDish);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mainDishes.clear();
            mainDishes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

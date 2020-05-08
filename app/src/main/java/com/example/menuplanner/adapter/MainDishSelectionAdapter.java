package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.MainDish;
import com.example.menuplanner.viewmodel.MainDishViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainDishSelectionAdapter extends RecyclerView.Adapter<MainDishSelectionAdapter.MainDishViewHolder> implements Filterable {
    private ArrayList<MainDish> allMainDishes;
    private OnItemClickListener listener;
    private List<MainDish> mainDishes;
//    private LiveData<List<MainDish>> mainDishLiveData;

    public interface OnItemClickListener {
        void onItemClicked(MainDish mainDish);
        void onCheckboxClicked(MainDish mainDish);
        void onEditClicked(MainDish mainDish);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setMainDishName(String name, int mainDishId) {
        for (MainDish dish : allMainDishes) {
            if (dish.getMainDishId() == mainDishId) {
                dish.setMainDishTitle(name);
            }
        }
    }

    public void addMainDish(MainDish mainDish) {
        allMainDishes.add(mainDish);
    }

    class MainDishViewHolder extends RecyclerView.ViewHolder {
        TextView mainDishName;
        CheckBox checkbox;
        ImageView edit;

        MainDishViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.mainDishName = itemView.findViewById(R.id.dish_title);
            this.checkbox = itemView.findViewById(R.id.dish_checkbox);
            this.edit = itemView.findViewById(R.id.edit_dish);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(mainDishes.get(position));
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
                            listener.onCheckboxClicked(mainDishes.get(position));
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
                            listener.onEditClicked(mainDishes.get(position));
                        }
                    }
                }
            });
        }
    }

    public void setMainDishes(List<MainDish> mainDishes) {
        this.mainDishes = mainDishes;
        this.allMainDishes = new ArrayList<>(mainDishes);
        notifyDataSetChanged();
    }

//    public MainDishSelectionAdapter(ArrayList<MainDish> mainDishes) {
//        this.mainDishes = mainDishes;
//        this.allMainDishes = new ArrayList<>(mainDishes);
//    }

//    public MainDishSelectionAdapter(LiveData<List<MainDish>> mainDishes) {
////        this.mainDishes = new ArrayList<>(mainDishes.getValue());
////        this.allMainDishes = new ArrayList<>(mainDishes.getValue());
//        this.mainDishLiveData = mainDishes;
//
//    }

    @NonNull
    @Override
    public MainDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_item, parent, false);
        return new MainDishViewHolder(view, this.listener);
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
        List<MainDish> filteredList = new ArrayList<>();

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allMainDishes);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MainDish mainDish : mainDishes) {
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

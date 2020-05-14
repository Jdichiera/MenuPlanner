package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientSelectionAdapter
        extends RecyclerView.Adapter<IngredientSelectionAdapter.IngredientViewHolder>
        implements Filterable {
    private ArrayList<Ingredient> allIngredients;
    private List<Ingredient> displayedIngredients;
    private IngredientSelectionAdapter.OnItemClickListener listener;
    public ArrayList<Integer> selectedIngredients;
    public ArrayList<Integer> deletedIngredients;
    public ArrayList<IngredientViewHolder> viewHolderArray = new ArrayList<>();

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView delete;
        CheckBox selectCheckbox;

        IngredientViewHolder(View itemView, final IngredientSelectionAdapter.OnItemClickListener listener) {
            super(itemView);
            this.ingredientName = itemView.findViewById((R.id.dish_title));
            itemView.findViewById(R.id.edit_dish_ingredients).setVisibility(View.INVISIBLE);
            selectCheckbox = itemView.findViewById(R.id.edit_dish_checkbox);
            this.delete = itemView.findViewById(R.id.delete_dish);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(displayedIngredients.get(position));
                        }
                    }
                }
            });

            selectCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCheckboxToggled(displayedIngredients.get(position));
                            int ingredientId = displayedIngredients.get(position).getIngredientId();
                            if (selectCheckbox.isChecked()) {
                                if (!selectedIngredients.contains(ingredientId)) {
                                    selectedIngredients.add(ingredientId);
                                }
                                if (deletedIngredients.contains(ingredientId)) {
                                    deletedIngredients.remove(deletedIngredients.indexOf(ingredientId));
                                }
                            } else {
                                if (!deletedIngredients.contains(ingredientId)) {
                                    deletedIngredients.add(ingredientId);
                                }
                                if (selectedIngredients.contains(ingredientId)) {
                                    selectedIngredients.remove(selectedIngredients.indexOf(ingredientId));
                                }
                            }
                        }
                    }
                }
            });

            this.delete.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClicked(position, displayedIngredients.get(position));
                        }
                    }
                }
            });

            viewHolderArray.add(this);
        }

        public void setSelectCheckbox(int position) {
            if (selectCheckbox.isChecked()) {
                selectCheckbox.setChecked(true);
            } else {
                selectCheckbox.setChecked(false);
            }
        }
    }

    @Override
    public Filter getFilter() {
        return this.ingredientFilter;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_and_ingredient_item, parent, false);
        return new IngredientViewHolder(view, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = this.displayedIngredients.get(position);
        holder.ingredientName.setText(ingredient.getIngredientName());
        if (this.selectedIngredients.contains(ingredient.getIngredientId())) {
            holder.selectCheckbox.setChecked(true);
//            this.viewHolderArray.add(holder);
        }
    }

    @Override
    public int getItemCount() {
        if (this.displayedIngredients != null) {
            return this.displayedIngredients.size();
        } else {
            return 0;
        }
    }

    private Filter ingredientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Ingredient> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allIngredients);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Ingredient ingredient : displayedIngredients) {
                    if (ingredient.getIngredientName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(ingredient);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            displayedIngredients.clear();
            displayedIngredients.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void setDeletedItemCheckbox(int position) {
        boolean checked = this.viewHolderArray.get(position).selectCheckbox.isChecked() ? false : true;
        this.viewHolderArray.get(position).selectCheckbox.setChecked(checked);
        this.viewHolderArray.remove(position);
    }

    public void resetItems() {
        displayedIngredients.clear();
        displayedIngredients.addAll(allIngredients);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.displayedIngredients = ingredients;
        this.allIngredients = new ArrayList<>(ingredients);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(IngredientSelectionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(Ingredient ingredient);
        void onCheckboxToggled(Ingredient ingredient);
        void onDeleteClicked(int position, Ingredient ingredient);
    }

    public void setDishIngredientIds(ArrayList<Integer> dishIngredientIds) {
        this.selectedIngredients = dishIngredientIds;
        this.deletedIngredients = new ArrayList<>();
    }
}

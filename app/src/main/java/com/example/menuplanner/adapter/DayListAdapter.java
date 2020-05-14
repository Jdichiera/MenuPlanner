package com.example.menuplanner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuplanner.R;
import com.example.menuplanner.entity.Day;

import java.util.ArrayList;
import java.util.List;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayListHolder> {
    private List<Day> days = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DayListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dayView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);
        return new DayListHolder(dayView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DayListHolder holder, int position) {
        Day dayAtPosition = days.get(position);
        holder.dayTitle.setText(dayAtPosition.getDayTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onDayClick(days.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void setDays(List<Day> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    class DayListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dayTitle;

        DayListHolder(View itemView) {
            super(itemView);
            dayTitle = itemView.findViewById(R.id.day_title);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public interface OnItemClickListener {
        void onDayClick(Day day);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

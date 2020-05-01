package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.DayListAdapter;
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.viewmodel.DayViewModel;

import java.util.List;

public class DayListActivity extends AppCompatActivity {
    private DayViewModel dayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);

        RecyclerView recyclerView = findViewById(R.id.day_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final DayListAdapter adapter = new DayListAdapter();
        recyclerView.setAdapter(adapter);
        
        adapter.setOnItemClickListener(new DayListAdapter.OnItemClickListener() {
            @Override
            public void onDayClick(Day day) {
                viewDay(day);
            }
        });

        dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        dayViewModel.getDays().observe(this, new Observer<List<Day>>() {
            @Override
            public void onChanged(List<Day> days) {
                adapter.setDays(days);
            }
        });
    }
    
    private void viewDay(Day day) {
        Toast.makeText(this, "View Day", Toast.LENGTH_SHORT).show();
    }
}

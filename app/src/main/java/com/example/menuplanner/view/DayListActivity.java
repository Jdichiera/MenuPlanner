package com.example.menuplanner.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.menuplanner.R;
import com.example.menuplanner.adapter.DayListAdapter;
import com.example.menuplanner.entity.Day;
import com.example.menuplanner.viewmodel.DayViewModel;

import java.util.List;

public class DayListActivity extends AppCompatActivity {
    public static final String DAY_NAME = "com.example.menuplanner.DAY_NAME";
    public static final String DAY_ID = "com.example.menuplanner.DAY_ID";
    public static final String MENU_ID = "com.example.menuplanner.MENU_ID";

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

        DayViewModel dayViewModel = ViewModelProviders.of(this).get(DayViewModel.class);
        dayViewModel.getDays().observe(this, new Observer<List<Day>>() {
            @Override
            public void onChanged(List<Day> days) {
                adapter.setDays(days);
            }
        });

        Button reportButton = findViewById(R.id.button_reports);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReports();
            }
        });
    }
    
    private void viewDay(Day day) {
        Intent intent = new Intent(DayListActivity.this, MenuViewActivity.class);
        intent.putExtra(DAY_NAME, day.getDayTitle());
        intent.putExtra(MENU_ID, day.getMenuId());
        intent.putExtra(DAY_ID, day.getDayId());
        startActivity(intent);
    }

    private void viewReports() {
        Intent intent = new Intent(DayListActivity.this, ReportViewActivity.class);
        startActivity(intent);
    }
}

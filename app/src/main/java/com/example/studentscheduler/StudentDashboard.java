package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.studentscheduler.Database.Schedule;
import com.example.studentscheduler.Database.Student;
import com.example.studentscheduler.adapters.ScheduleAdapter;
import com.example.studentscheduler.adapters.StudentAdapter;

import java.util.List;

public class StudentDashboard extends AppCompatActivity {

    List<Schedule> scheduleList;
    ScheduleAdapter adapter;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        Intent receivedIntent = getIntent();
        int receivedStudentID = receivedIntent.getIntExtra("ID", 0);

        RecyclerView scheduleRecyclerView = findViewById(R.id.student_recycler_view_for_student);
        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        scheduleRecyclerView.setHasFixedSize(true);

        adapter = new ScheduleAdapter();
        scheduleRecyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getScheduleList(receivedStudentID).observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                adapter.setScheduleList(schedules);
            }
        });
    }
}
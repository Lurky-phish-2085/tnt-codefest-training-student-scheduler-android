package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {
    TextView student_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent receivedIntent = getIntent();
        int receivedStudentID = receivedIntent.getIntExtra("ID", 0);
        String receivedStudentName = receivedIntent.getStringExtra("NAME");

        student_name = findViewById(R.id.student_name);
        student_name.setText(receivedStudentName);
    }

    public void switchToAdminDashboard(View view) {
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
    }
}
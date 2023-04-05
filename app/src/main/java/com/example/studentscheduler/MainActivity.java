package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToAdminActivity(View view) {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }

    public void switchToStudentActivity(View view) {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
    }
}
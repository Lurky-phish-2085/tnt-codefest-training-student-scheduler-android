package com.example.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentscheduler.Database.Student;
import com.example.studentscheduler.Fragments.InputStudentFragment;
import com.example.studentscheduler.Interface.MainInterface;
import com.example.studentscheduler.adapters.StudentAdapter;

import java.util.List;

public class AdminDashboard extends AppCompatActivity implements MainInterface {

    List<Student> studentsList;
    StudentAdapter adapter;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        RecyclerView studentRecyclerView = findViewById(R.id.student_recycler_view_for_admin);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentRecyclerView.setHasFixedSize(true);

        adapter = new StudentAdapter(this);
        studentRecyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getStudentList().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentsList = students;
               adapter.setStudents(students);
            }
        });
    }

    @Override
    public void onClickButton(int position, String mode) {
        int id = studentsList.get(position).getId();

        if(mode == "add"){
            Intent intent = new Intent(this, InputActivity.class);
            String studentName = studentsList.get(position).getName();
            int studentID = studentsList.get(position).getId();
            intent.putExtra("ID", studentID);
            intent.putExtra("NAME", studentName);
            startActivity(intent);
        }

        if(mode == "view"){
            Intent intent = new Intent(this, StudentDashboard.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        }
        if(mode == "delete"){
//            System.out.println("Trigger");
            Student toDelete = mainViewModel.getStudentList().getValue().get(position);
//            System.out.println(toDelete.getName());
//            System.out.println(toDelete.getPassword());
            confirmDeletion(toDelete);
//            mainViewModel.delete(toDelete);
        }
    }

    private void confirmDeletion(Student student) {
        DeletionConfirmationModal confirmationModal = new DeletionConfirmationModal(student, mainViewModel);
        confirmationModal.show(getSupportFragmentManager(), "Deletion confirmation modal");
    }

    public void switchToStudentInput(View view) {
        System.out.println("Working!!@");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.input_student_view, new InputStudentFragment());
        fragmentTransaction.commit();
        //Switch To Activity Fragment
//        Intent intent = new Intent(this, InputStudentActivity.class);
//        startActivity(intent);
    }

    public void switchToHomepage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
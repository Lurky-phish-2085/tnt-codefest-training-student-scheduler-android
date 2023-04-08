package com.example.studentscheduler;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Database.MainRepository;
import com.example.studentscheduler.Database.Schedule;
import com.example.studentscheduler.Database.Student;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    MainRepository repository;
    private LiveData<List<Student>> studentList;
    private LiveData<List<Schedule>> scheduleList;

    public MainViewModel(Application application) {
        super(application);
        repository = new MainRepository(application);

        studentList = repository.getStudentList();
    }

    //region Insert
    public void insert(Student student){
        repository.insert(student);
    }

    public void insert(Schedule schedule){
        repository.insert(schedule);
    }
    //endregion

    //region Update
    public void update(Student student){
        repository.update(student);
    }

    public void update(Schedule schedule){
        repository.insert(schedule);
    }
    //endregion

    //region Delete
    public void delete(Student student){
        repository.delete(student);
    }

    public void delete(Schedule schedule){
        repository.insert(schedule);
    }
    //endregion

    //region Read
    public LiveData<List<Student>> getStudentList() {
        return studentList;
    }



    public LiveData<List<Schedule>> getScheduleList(int studentID) {
        scheduleList = repository.getScheduleList(studentID);
        return scheduleList;
    }
    //endregion

}

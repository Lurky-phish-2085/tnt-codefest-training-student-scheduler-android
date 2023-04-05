package com.example.studentscheduler.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert
    void insert(Schedule schedule);

    @Delete
    void delete(Schedule schedule);

    @Update
    void update(Schedule schedule);

    @Query("SELECT * FROM schedule_table Where StudentId is :studentID")
    LiveData<List<Schedule>> getSchedule(int studentID);
}

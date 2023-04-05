package com.example.studentscheduler.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule_table", foreignKeys = {@ForeignKey(entity = Student.class, parentColumns = "id",
        childColumns = "StudentId", onDelete = ForeignKey.CASCADE)})
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int StudentId;

    private int time;

    private int dayOfTheWeek;

    private String subject;

    public Schedule() {
    }

    public Schedule(int studentId, int time, String subject, int dayOfTheWeek) {
        StudentId = studentId;
        this.time = time;
        this.subject = subject;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

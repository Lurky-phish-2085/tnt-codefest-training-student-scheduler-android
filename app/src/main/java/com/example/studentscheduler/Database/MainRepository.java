package com.example.studentscheduler.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//My repo xD
public class MainRepository {
    private StudentDao studentDao;
    private ScheduleDao scheduleDao;

    private LiveData<List<Student>> studentList;


    public MainRepository(Application application) {
        MainDatabase db = MainDatabase.getInstance(application);
        scheduleDao = db.scheduleDao();
        studentDao = db.studentDao();

        studentList = studentDao.getAllStudents();
    }

    //region Insert
    public void insert(Student student){
        new RepoAsyncTask(studentDao, student, 1).execute();
    }

    public void insert(Schedule schedule){
        new RepoAsyncTask(scheduleDao, schedule, 4).execute();
    }
    //endregion

    //region Update
    public void update(Student student){
        new RepoAsyncTask(studentDao, student, 2).execute();
    }

    public void update(Schedule schedule){
        new RepoAsyncTask(scheduleDao, schedule, 5).execute();
    }
    //endregion

    //region Delete
    public void delete(Student student){
        new RepoAsyncTask(studentDao, student, 3).execute();
    }

    public void delete(Schedule schedule){
        new RepoAsyncTask(scheduleDao, schedule, 6).execute();
    }
    //endregion

    //region Read
    public LiveData<List<Student>> getStudentList() {
        return studentList;
    }

    public LiveData<List<Schedule>> getScheduleList(int studentID) {
        return scheduleDao.getSchedule(studentID);
    }
    //endregion

    private static class RepoAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;
        private ScheduleDao scheduleDao;

        private Student student;

        private Schedule schedule;
        private int mode;

        public RepoAsyncTask(StudentDao studentDao, Student student, int mode) {
            this.studentDao = studentDao;
            this.student = student;

            this.mode = mode;
        }

        public RepoAsyncTask(ScheduleDao scheduleDao, Schedule schedule, int mode) {
            this.scheduleDao = scheduleDao;
            this.schedule = schedule;
            this.mode = mode;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            switch (mode) {
                //studentDao
                case 1:
                    studentDao.insert(student);
                    break;
                case 2:
                    studentDao.update(student);
                    break;
                case 3:
                    studentDao.delete(student);
                    break;
                //scheduleDao
                case 4:
                    scheduleDao.insert(schedule);
                    break;
                case 5:
                    scheduleDao.update(schedule);
                    break;
                case 6:
                    scheduleDao.delete(schedule);
                    break;
            }
            System.out.println("repo works");
            return null;
        }
    }
}

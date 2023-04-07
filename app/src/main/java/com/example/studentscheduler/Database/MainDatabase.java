package com.example.studentscheduler.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class, Schedule.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {
    private static MainDatabase instance;
    public abstract ScheduleDao scheduleDao();
    public abstract StudentDao studentDao();

    public static synchronized MainDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, "MainDB")
                    .fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    };

    private static Callback callback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Prepopulate(instance).execute();
        }
    };

    private static class Prepopulate extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;
        private ScheduleDao scheduleDao;

        private Prepopulate(MainDatabase db){
            scheduleDao = db.scheduleDao();
            studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Student Dan = new Student("Dan", "123");
            Student Jet = new Student("Jet", "frontendGods");
            Student Carlo = new Student("Carlo", "Roses_are_red");
            Student Justine = new Student("Justine Mas", "Violets_are_blue");
            Dan.setId(1);
            Jet.setId(2);
            Carlo.setId(3);
            Justine.setId(4);
            studentDao.insert(Dan);
            studentDao.insert(Jet);
            studentDao.insert(Carlo);
            studentDao.insert(Justine);
            scheduleDao.insert(new Schedule(1, 1600, "Data Science", "Saturday"));
            scheduleDao.insert(new Schedule(2, 1700, "Computer Programming", "Tuesday"));
            scheduleDao.insert(new Schedule(3, 1700, "Computer Programming", "Tuesday"));
            studentDao.delete(Carlo);
            return null;
        }
    }
}

package com.mta.trackerapplication.database;

import android.content.Context;

import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TrackerData.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TrackerDao trackerDao();
    public abstract UserDao userDao();

    private static volatile AppDatabase _instance;
    private static final int NUMBER_OF_THREAD = 4;

    public static final ExecutorService databaseWriterExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public static AppDatabase getDatabase(final Context context){
        if(_instance == null){
            synchronized (AppDatabase.class){
                if (_instance == null){
                    _instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Tracker_Database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return _instance;
    }
}

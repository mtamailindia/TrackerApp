package com.mta.trackerapplication.repository;

import android.app.Application;
import com.mta.trackerapplication.database.AppDatabase;
import com.mta.trackerapplication.database.TrackerDao;
import com.mta.trackerapplication.database.UserDao;
import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;

import java.util.List;
import androidx.lifecycle.LiveData;

public class TrackerRepository {

    private LiveData<List<TrackerData>> trackerLiveData;
    private LiveData<List<User>> userLiveData;
    private TrackerDao mTrackerDao;
    private UserDao mUserDao;

    public TrackerRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        mTrackerDao = appDatabase.trackerDao();
        mUserDao = appDatabase.userDao();
        trackerLiveData = mTrackerDao.getAllTrackerRecord();
        userLiveData = mUserDao.getAllUser();
    }

    public LiveData<List<TrackerData>> getAllTracker(){
        return trackerLiveData;
    }

    public LiveData<List<User>> getAllUser(){
        return userLiveData;
    }

    public LiveData<String> getTotalExpense(){
        return mTrackerDao.getTotalExpense();
    }

    public LiveData<String> getTotalExpenseByUser(String mobile){
        return mTrackerDao.getTotalExpenseByUser(mobile);
    }

    public void addNewUser(User user){
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.insert(user);
            }
        });
    }

    public void addNewTracker(TrackerData trackerData){
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTrackerDao.insert(trackerData);
            }
        });
    }

    public User getUserByMobile(String mobile) {
        return mTrackerDao.getUserByMobile(mobile);
    }
}

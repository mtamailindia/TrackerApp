package com.mta.trackerapplication.database;

import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TrackerDao {

    @Query("SELECT * FROM TrackerData")
    LiveData<List<TrackerData>> getAllTrackerRecord();

    @Insert
    void insert(TrackerData trackerData);

    @Delete
    void delete(TrackerData trackerData);

    @Query("SELECT SUM(amount) FROM TrackerData")
    LiveData<String> getTotalExpense();

    @Query("SELECT SUM(amount) FROM TrackerData WHERE mobileNo= :mobile")
    LiveData<String> getTotalExpenseByUser(String mobile);

    @Query("SELECT * FROM User WHERE mobileNo= :mobile")
    User getUserByMobile(String mobile);
}

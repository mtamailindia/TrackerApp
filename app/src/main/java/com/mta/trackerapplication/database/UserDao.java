package com.mta.trackerapplication.database;

import com.mta.trackerapplication.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUser();

    @Insert
    void insert(User trackerData);

    @Delete
    void delete(User trackerData);
}

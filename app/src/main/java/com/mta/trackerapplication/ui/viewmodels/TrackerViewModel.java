package com.mta.trackerapplication.ui.viewmodels;

import android.app.Application;
import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;
import com.mta.trackerapplication.repository.TrackerRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TrackerViewModel extends AndroidViewModel {

    private TrackerRepository mTrackerRepository;

    public TrackerViewModel(@NonNull Application application) {
        super(application);
        mTrackerRepository = new TrackerRepository(application);
    }

    public LiveData<List<TrackerData>> getAllTracker(){
        return mTrackerRepository.getAllTracker();
    }

    public LiveData<List<User>> getAllUser(){
        return mTrackerRepository.getAllUser();
    }

    public void addNewTracker(TrackerData trackerData){
        mTrackerRepository.addNewTracker(trackerData);
    }

    public LiveData<String> getTotalExpense(){
        return mTrackerRepository.getTotalExpense();
    }

    public LiveData<String> getTotalExpenseByUser(String mobile){
        return mTrackerRepository.getTotalExpenseByUser(mobile);
    }

    public User getUserByMobile(String mobile){
        return mTrackerRepository.getUserByMobile(mobile);
    }

    public void addNewUser(User user){
        mTrackerRepository.addNewUser(user);
    }

    public boolean isValid(String name, String email, String mobile){
        if(name == null || name.isEmpty() || email == null || email.isEmpty() || mobile == null || mobile.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean isValidExpenseData(String paidFor, String amount, String mobile){
        if(paidFor == null || paidFor.isEmpty() || amount == null || amount.isEmpty() || mobile == null || mobile.isEmpty()){
            return false;
        }
        return true;
    }

    public TrackerData makeTracker(String paidFor, String mobile, String amount){
        TrackerData data = new TrackerData();
        data.setPaidFor(paidFor);
        data.setMobileNo(mobile);
        data.setAmount(Float.parseFloat(amount));
        return data;
    }

    public User makeUser(String name, String email, String mobile){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobileNo(mobile);
        return user;
    }
}

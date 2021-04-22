package com.mta.trackerapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mta.trackerapplication.R;
import com.mta.trackerapplication.listener.UserItemClickListener;
import com.mta.trackerapplication.model.User;
import com.mta.trackerapplication.ui.adapter.UserAdapter;
import com.mta.trackerapplication.ui.viewmodels.TrackerViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserItemClickListener {

    private TrackerViewModel mViewModel;
    private List<User> mMainList;
    private UserAdapter mAdapter;
    private TextView tvSummery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this, ViewModelProvider.
                AndroidViewModelFactory.getInstance(this.getApplication())).get(TrackerViewModel.class);
        mMainList = new ArrayList<>();
        mAdapter = new UserAdapter(this, mMainList, this);

        setUI();
        observeData();
        observerTotalExpense();
    }

    private void observerTotalExpense() {

        mViewModel.getTotalExpense().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && !s.isEmpty())
                tvSummery.setText(getString(R.string.total)+ s);
            }
        });
    }

    private void setUI() {
        tvSummery = findViewById(R.id.tvSummery);
        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.buttonAddNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNewUserActivity.class));
            }
        });


    }

    private void observeData() {
        mViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> trackerData) {
                mMainList.clear();
                mMainList.addAll(trackerData);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onUserItemClick(User user) {
        Intent intent = new Intent(MainActivity.this, AddNewExpenceActivity.class);
        intent.putExtra("mobile", user.getMobileNo());
        startActivity(intent);
    }
}
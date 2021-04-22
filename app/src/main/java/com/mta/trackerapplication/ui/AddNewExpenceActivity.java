package com.mta.trackerapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mta.trackerapplication.R;
import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.ui.viewmodels.TrackerViewModel;

public class AddNewExpenceActivity extends AppCompatActivity {

    private TrackerViewModel mViewModel;
    private EditText etPaidFor;
    private EditText etAmount;
    private String mobile;
    private TextView tvLastPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Intent intent = getIntent();
        if (intent != null){
            mobile = intent.getStringExtra("mobile");
        }

        mViewModel = new ViewModelProvider(this, ViewModelProvider.
                AndroidViewModelFactory.getInstance(this.getApplication())).get(TrackerViewModel.class);

        setUI();
        observeUserInvestment();
    }

    private void observeUserInvestment() {
        mViewModel.getTotalExpenseByUser(mobile).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && !s.isEmpty()) {
                    String amount = getString(R.string.you_paid) + " " + s;
                    tvLastPaid.setText(amount);
                }else{
                    String amount = getString(R.string.you_paid) + " 0";
                    tvLastPaid.setText(amount);
                }
            }
        });
    }

    private void setUI() {
        etPaidFor = findViewById(R.id.etPaidFor);
        etAmount = findViewById(R.id.etAmount);
        tvLastPaid = findViewById(R.id.amount);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paidFor = etPaidFor.getText().toString();
                String amount = etAmount.getText().toString();
                if (mViewModel.isValidExpenseData(paidFor, amount, mobile)){
                    TrackerData data = mViewModel.makeTracker(paidFor, mobile, amount);
                    mViewModel.addNewTracker(data);
                    clearFields();
                    finish();
                }

            }
        });


    }

    private void clearFields() {
        etPaidFor.setText("");
        etAmount.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
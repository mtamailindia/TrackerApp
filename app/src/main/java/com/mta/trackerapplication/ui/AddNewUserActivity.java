package com.mta.trackerapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mta.trackerapplication.R;
import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;
import com.mta.trackerapplication.ui.viewmodels.TrackerViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddNewUserActivity extends AppCompatActivity {

    private TrackerViewModel mViewModel;
    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        mViewModel = new ViewModelProvider(this, ViewModelProvider.
                AndroidViewModelFactory.getInstance(this.getApplication())).get(TrackerViewModel.class);

        setUI();
    }

    private void setUI() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String mobile = etMobile.getText().toString();
                if (mViewModel.isValid(name, email, mobile)){
                    User user = mViewModel.getUserByMobile(mobile);
                    if (user != null){
                        Toast.makeText(AddNewUserActivity.this, getString(R.string.mobile_already_exist), Toast.LENGTH_SHORT).show();
                    }else{
                        User data = mViewModel.makeUser(name, email, mobile);
                        mViewModel.addNewUser(data);
                        clearFields();
                        finish();
                    }
                }else {
                    Toast.makeText(AddNewUserActivity.this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void clearFields() {
        etName.setText("");
        etEmail.setText("");
        etMobile.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
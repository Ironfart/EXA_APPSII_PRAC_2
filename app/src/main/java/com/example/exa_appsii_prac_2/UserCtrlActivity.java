package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserCtrlActivity extends AppCompatActivity {

    Intent inUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ctrl);
    }

    public void onUserList(View v) {
        inUserList = new Intent(this, UserListActivity.class);
        startActivity(inUserList);
    }
}

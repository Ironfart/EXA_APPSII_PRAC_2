package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class UserListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lstUser;

    User[] user = {};

    ArrayList<User> users = new ArrayList<>(Arrays.asList(user));
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        lstUser = findViewById(R.id.lstUsers);

        adapter = new UserAdapter(this, R.layout.user_list, users);

        lstUser.setAdapter(adapter);
        lstUser.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

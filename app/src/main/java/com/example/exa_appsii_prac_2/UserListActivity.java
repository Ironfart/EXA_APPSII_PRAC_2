package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class UserListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;

    ListView lstUser;

    User[] user = {};

    ArrayList<User> users = new ArrayList<>(Arrays.asList(user));
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        lstUser = findViewById(R.id.lstUsers);

        adapter = new UserAdapter(this, R.layout.user_list, users);

        lstUser.setAdapter(adapter);
        lstUser.setOnItemClickListener(this);

        refreshDB();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent inDatos = new Intent();
        inDatos.putExtra("id", users.get(i).getId() );
        setResult(Activity.RESULT_OK, inDatos);

        //Toast.makeText(this, users.get(i).getId()+"", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void refreshDB() {
        users.clear();
        adapter.notifyDataSetChanged();
        String sql = "select * from users";
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(c1.getColumnIndex("name"));
            String lastname = c1.getString(c1.getColumnIndex("lastname"));
            String username = c1.getString(c1.getColumnIndex("username"));
            // do something with the record here...
            users.add(new User(recId, name, lastname, username));
        }
    }
}

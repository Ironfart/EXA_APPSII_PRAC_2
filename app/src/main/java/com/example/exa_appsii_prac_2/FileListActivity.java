package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FileListActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    SQLiteDatabase db;

    ListView lstFiles;

    int userID, dbUserID;
    String[] file = {}, noID;
    ArrayList<String> files = new ArrayList<>(Arrays.asList(file));

    ArrayAdapter adapter;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        Intent intent = getIntent();
        b = intent.getExtras();

        userID = b.getInt("userID");

        lstFiles = findViewById(R.id.lstFiles);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,files);
        lstFiles.setAdapter(adapter);
        lstFiles.setOnItemClickListener(this);

        refreshDB();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent inDatos = new Intent();
        inDatos.putExtra("name",  files.get(i) );
        setResult(Activity.RESULT_OK, inDatos);

        //Toast.makeText(this, users.get(i).getId()+"", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void refreshDB() {
        files.clear();
        adapter.notifyDataSetChanged();
        String sql = "select * from files where userID = '" + userID +"'" ;
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String filename = c1.getString(c1.getColumnIndex("filename"));
            dbUserID = c1.getInt(2);
            // do something with the record here...
            noID = filename.split("&");
            files.add(noID[1]);
        }
    }
}

package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    final Context context = this;

    Intent inFileCtrl, inUserCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        db.execSQL("create table if not exists users ("
                + " recID integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " lastname text, "
                + " username text unique, "
                + " password text ); " );
    }

    public void onUserCtrl(View v) {
        inUserCtrl = new Intent(this, UserCtrlActivity.class);
        startActivity(inUserCtrl);
    }

    public void onFileCtrl(View v) {
        inFileCtrl = new Intent(this, FileCtrlActivity.class);
        startActivity(inFileCtrl);
    }

    public void onExit(View v) {
        finishAndRemoveTask();
    }
}

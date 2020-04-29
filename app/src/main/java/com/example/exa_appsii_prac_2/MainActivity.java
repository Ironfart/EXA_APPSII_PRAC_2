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

    Intent inUserCtrl, inFileLogin;

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

        db.execSQL("create table if not exists files ("
                + " recID integer PRIMARY KEY autoincrement, "
                + " filename text unique, "
                + " userID int ); " );

        //db.execSQL("drop table  files");
    }

    public void onUserCtrl(View v) {
        inUserCtrl = new Intent(this, UserCtrlActivity.class);
        startActivity(inUserCtrl);
    }

    public void onFileCtrl(View v) {
        inFileLogin = new Intent(this, FileLoginActivity.class);
        startActivity(inFileLogin);
    }

    public void onExit(View v) {
        finishAndRemoveTask();
    }
}

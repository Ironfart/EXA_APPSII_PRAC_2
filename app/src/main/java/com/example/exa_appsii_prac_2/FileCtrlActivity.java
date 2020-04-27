package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FileCtrlActivity extends AppCompatActivity {

    Intent inFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_ctrl);
    }

    public void onNewFile(View v) {

    }

    public void onSaveFile(View v) {

    }

    public void onOpenFile(View v) {
        inFileList = new Intent(this, FileListActivity.class);
        startActivity(inFileList);
    }
}

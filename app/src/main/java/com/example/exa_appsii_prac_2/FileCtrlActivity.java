package com.example.exa_appsii_prac_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileCtrlActivity extends AppCompatActivity {

    SQLiteDatabase db;

    Intent inFileList;

    EditText txtData;

    Bundle b, bL;

    String sRutaSD, fileName;
    final int PERMISO_ESCRITURA = 1000;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_ctrl);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        Intent intent = getIntent();
        b = intent.getExtras();

        txtData = findViewById(R.id.edTxtFileEdit);

        sRutaSD = getExternalFilesDir(null).getPath(); //Environment.getExternalStorageDirectory().getAbsolutePath();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISO_ESCRITURA);

        }

        fileName = "";

        if (fileName.equals("")) {
            onFileName();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        fileName = data.getStringExtra("name");

        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK){
                try {
                    //InputStream is = openFileInput(ARCHIVO);
                    File file = new File(getExternalFilesDir(null), "ID" + b.getInt("recID") + "&" +
                            data.getStringExtra("name"));
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis);

                    BufferedReader br = new BufferedReader(isr);

                    String string;
                    txtData.setText("");
                    while ((string = br.readLine())!= null){
                        txtData.append(string + "\n");
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onNewFile(View v) {
        txtData.setText("");
        onFileName();
        fileName = "";
    }

    public void onSaveFile(View v) {
        String string = txtData.getText().toString();
        String name = "ID"+b.getInt("recID")+"&"+fileName;

        try {
            File file = new File(getExternalFilesDir(null), name);
            //FileOutputStream fos=new FileOutputStream(sRutaSD);
            FileOutputStream fos = new FileOutputStream(file);
            //OutputStream os = openFileOutput(ARCHIVO, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(string);
            bw.close();

            int userID;
            ContentValues cv = new ContentValues();

            userID = b.getInt("recID");


            cv.put("filename", name);
            cv.put("userID", userID);

            long rowPosition = db.insert("files", null, cv);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onOpenFile(View v) {
        inFileList = new Intent(this, FileListActivity.class);
        bL = new Bundle();
        bL.putInt("userID", b.getInt("recID"));
        inFileList.putExtras(bL);
        startActivityForResult(inFileList,100);
    }

    public void onFileName(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);

        //Vincular widgets del cuadro de dialogo
        final EditText edTxtName;
        Button btnOK;

        edTxtName = dialog.findViewById(R.id.edTxtName);
        btnOK = dialog.findViewById(R.id.btnOk);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName = edTxtName.getText().toString();

                Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

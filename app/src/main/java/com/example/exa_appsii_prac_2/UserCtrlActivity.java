package com.example.exa_appsii_prac_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserCtrlActivity extends AppCompatActivity {

    SQLiteDatabase db;

    Intent inUserList;

    TextView txtName, txtLName, txtUsername, txtPass;
    EditText edTxtName, edTxtLName, edTxtUsername, edTxtPass;

    Button btnUserSave, btnNewUser, btnDelUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ctrl);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        edTxtName = findViewById(R.id.edTxtName);
        edTxtLName = findViewById(R.id.edTxtLName);
        edTxtUsername = findViewById(R.id.edTxtUsername);
        edTxtPass = findViewById(R.id.edTxtPass);
    }

    public void onDBSave(View v) {
        String name, lastname, username, pass;
        ContentValues cv = new ContentValues();


        if (edTxtName.getText().toString().equals("") || edTxtLName.getText().toString().equals("") ||
                edTxtUsername.getText().toString().equals("") || edTxtPass.getText().toString().equals("")){
            Toast.makeText(UserCtrlActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            name = edTxtName.getText().toString();
            lastname = edTxtLName.getText().toString();
            username = edTxtUsername.getText().toString();
            pass = edTxtPass.getText().toString();

            cv.put("name", name);
            cv.put("lastname", lastname);
            cv.put("username", username);
            cv.put("password", pass);

            long rowPosition = db.insert("users", null, cv);

            Toast.makeText(UserCtrlActivity.this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }
    }

    public void onUserList(View v) {
        inUserList = new Intent(this, UserListActivity.class);
        startActivityForResult(inUserList,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String sql = "select * from users where recID = ? ";
                String[] args = { data.getIntExtra("id", 100) + "" };
                Cursor c1 = db.rawQuery(sql, args);

                c1.moveToPosition(-1);
                while ( c1.moveToNext() ){
                    int recId = c1.getInt(0);
                    String name = c1.getString(c1.getColumnIndex("name"));
                    String lastname = c1.getString(c1.getColumnIndex("lastname"));
                    String username = c1.getString(c1.getColumnIndex("username"));
                    String password = c1.getString(c1.getColumnIndex("password"));
                    // do something with the record here...
                    edTxtLName.setText(lastname);
                    edTxtName.setText(name);
                    edTxtUsername.setText(username);
                    edTxtPass.setText(password);
                }
            }
        }
    }

    public void onNewUser(View v) {
        Toast.makeText(this, "Generando nuevo usuario....", Toast.LENGTH_SHORT).show();
        edTxtLName.setText("");
        edTxtName.setText("");
        edTxtUsername.setText("");
        edTxtPass.setText("");
    }

    public void onDBDelete(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Elminiar usuario...")
                .setMessage("Estás seguro que deseas eliminar al usuario?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Action
                        Toast.makeText(getApplicationContext(), "Boton OK", Toast.LENGTH_SHORT).show();
                        String[] whereArgs = { edTxtUsername.getText().toString() };

                        int recAffected = db.delete("users", "username = ?", whereArgs);

                        edTxtLName.setText("");
                        edTxtName.setText("");
                        edTxtUsername.setText("");
                        edTxtPass.setText("");

                        Toast.makeText(UserCtrlActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Action
                        Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                })
                .create().show();
        /**/
    }

}

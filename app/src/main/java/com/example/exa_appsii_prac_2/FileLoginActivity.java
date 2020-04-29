package com.example.exa_appsii_prac_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileLoginActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText edTxtUser, edTxtPass;
    Button btnLog;

    Intent inFileCtrl;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_login);

        db = this.openOrCreateDatabase("usersDB", MODE_PRIVATE, null);

        edTxtUser = findViewById(R.id.edTxtLogUser);
        edTxtPass = findViewById(R.id.edTxtLogPass);
        btnLog = findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edTxtUser.getText().toString();
                String pass = edTxtPass.getText().toString();
                if (onLogin(user, pass).booleanValue() == true){
                    Toast.makeText(getApplicationContext(), "Bienvenido, " + user, Toast.LENGTH_SHORT).show();
                    inFileCtrl = new Intent(FileLoginActivity.this, FileCtrlActivity.class);
                    inFileCtrl.putExtras(b);
                    startActivity(inFileCtrl);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos, " + user, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public Boolean onLogin(String user, String pass){
        Boolean log = false;
        String sql = "select recID, username, password from users where username = '" + user + "' and password = '" + pass + "'";

        Cursor c1 = db.rawQuery(sql,null);
        //preguntamos si el cursor tiene algun valor almacenado
        if(c1.moveToFirst() == true){
            //capturamos los valores del cursos y lo almacenamos en variable
            int dbRecID = c1.getInt(0);
            String dbUser = c1.getString(1);
            String dbPass = c1.getString(2);

            //preguntamos si los datos ingresados son iguales
            if (user.equals(dbUser) && pass.equals(dbPass)){
                b = new Bundle();
                b.putInt("recID", dbRecID);
                b.putString("user",dbUser);
                log = true;
            } else {
                log = false;
            }
        }
        return log;
    }
}

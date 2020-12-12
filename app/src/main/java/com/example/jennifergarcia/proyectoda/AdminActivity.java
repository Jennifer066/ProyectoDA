package com.example.jennifergarcia.proyectoda;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
//import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//import dmax.dialog.SpotsDialog;//

public class AdminActivity extends AppCompatActivity {
    Button recuperar, btnEnviar;
    TextInputEditText gmail, password;
    private FirebaseAuth mAuth;
    AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        /* Agregar en cada codigo de Java la línea de abajo para centrar el nombre de la app titulo centrado action bar*/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        mAuth =FirebaseAuth.getInstance();
        recuperar = (Button) findViewById(R.id.recuperar);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        gmail = (TextInputEditText)findViewById(R.id.gmail);
        password = (TextInputEditText)findViewById(R.id.password);

        alerta = new AlertDialog.Builder(AdminActivity.this).setMessage("Por favor espere...").show();

        getRecuperar();
        loginAdmin();
    }
    private  void  limpiar(){
        gmail.setText("");
        password.setText("");
    }
    private void  loginAdmin(){
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = gmail.getText().toString().trim();
                String passE = password.getText().toString().trim();
                if (TextUtils.isEmpty(userE)) {
                    Toast.makeText(getApplicationContext(), "Ingrese el usuario",
                            Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(passE)) {
                    Toast.makeText(getApplicationContext(), "Ingrese la contraseña",
                            Toast.LENGTH_LONG).show();
                } else {
                    alerta.show();
                    mAuth.signInWithEmailAndPassword(userE, passE)
                            .addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Credenciales incorrectas",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        limpiar();
                                        Intent i = new Intent(AdminActivity.this, MenuAdminActivity.class);
                                        startActivity(i);
                                    }
                                    alerta.dismiss();

                            }
                            });
                }
            }
        });
    }
public void getRecuperar(){
    recuperar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
        Intent i = new Intent(AdminActivity.this, RecuperarActivity.class);
        startActivity(i);
    }
    });
    }
}

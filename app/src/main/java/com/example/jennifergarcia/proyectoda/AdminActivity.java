package com.example.jennifergarcia.proyectoda;

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
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.muddzdev.styleabletoast.StyleableToast;
//import com.skydoves.elasticviews.ElasticCheckButton;

//import dmax.dialog.SpotsDialog;//

public class AdminActivity extends AppCompatActivity {
    Button recuperar, btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        /* Agregar en cada codigo de Java la línea de abajo para centrar el nombre de la app titulo centrado action bar*/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        recuperar = (Button) findViewById(R.id.recuperar);
        getRecuperar();
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

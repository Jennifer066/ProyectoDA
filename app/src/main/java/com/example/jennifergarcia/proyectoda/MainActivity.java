package com.example.jennifergarcia.proyectoda;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView formulario, admin, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Agregar en cada codigo de Java la línea de abajo para centrar el nombre de la app */
         /* Main de elementos interfaz principal */
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);

        //mandar a llamar elementos
        formulario = (CardView) findViewById(R.id.idcard1);
        admin = (CardView) findViewById(R.id.idcard2);
        info = (CardView) findViewById(R.id.idcard4);
        //llamando el método
        getFormulario();
        getAdmin();
        getInfo();
    }

    //inicio del método formulario
    private void getFormulario() {
        formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(i);
            }
        });
    }

    //inicio del método administrador
    private void getAdmin() {
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(i);

            }
        });
    }

    //inicio del método información
    private void getInfo() {
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InformacionActivity.class);
                startActivity(i);
            }
        });
    }
}

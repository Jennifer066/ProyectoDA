package com.example.jennifergarcia.proyectoda;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.support.design.widget.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;

public class RecuperarActivity extends AppCompatActivity {
    private TextInputEditText gmail;
    private Button recuperar;
    private ProgressDialog progress;
    private String correo;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
 /* Agregar en cada codigo de Java la línea de abajo para centrar el nombre de la app titulo centrado action bar*/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        gmail = (TextInputEditText) findViewById(R.id.gmail);
        recuperar = (Button) findViewById(R.id.btnRecuperar);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        getRecuperar();
    }
    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = gmail.getText().toString().trim();
                if(!correo.isEmpty()){
                    progress.setMessage("Espere un momento..");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    getEnviarCorreo();
                }else
                {
                    Toast.makeText(getApplicationContext(), "El correo no se pudo enviar",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void getEnviarCorreo(){
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Por favor revise su correo para restaurar la contraseña",
                            Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecuperarActivity.this, AdminActivity.class);
                    startActivity(i);
                    finish();
                }else
                {
                Toast.makeText(getApplicationContext(), "El correo no se pudo enviar",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

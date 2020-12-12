package com.example.jennifergarcia.proyectoda;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularioActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button consultarLatLong, btnGuardar;
    EditText edtLat, edtLong, edtnombre, edtdesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        /* Agregar en cada codigo de Java la línea de abajo para centrar el nombre de la app titulo centrado action bar*/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        btnGuardar = (Button) findViewById(R.id.btnEnviar);
        consultarLatLong = (Button) findViewById(R.id.botton);
        edtLat = (EditText) findViewById(R.id.txtLat);
        edtLong = (EditText) findViewById(R.id.txtLon);
        edtnombre = (EditText) findViewById(R.id.txtNo);
        edtdesc = (EditText) findViewById(R.id.txtDes);
        getLocalizacion();
        getCargarLocalizacion();
        guardarDatos();

    }
    public void guardarDatos(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = edtLat.getText().toString().trim();
                String logitud = edtLong.getText().toString().trim();
                String nombre = edtnombre.getText().toString().trim();
                String descripcion = edtdesc.getText().toString().trim();

                if(TextUtils.isEmpty(latitud)){
                    Toast.makeText(getApplicationContext(), "Por favor pulsa el botón de generar ubicación",
                            Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(logitud)){
                    Toast.makeText(getApplicationContext(), "Por favor pulsa el botón de generar ubicación",
                            Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(nombre)){
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el nombre completo",
                            Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(descripcion)){
                    Toast.makeText(getApplicationContext(), "Por favor ingresar una descripción del lugar",
                            Toast.LENGTH_LONG).show();
                }else{
                    Formrespuestas formrespuestas = new Formrespuestas(Double.valueOf(latitud),Double.valueOf(logitud), nombre, descripcion);

                    databaseReference.child("Respuestas").child(nombre).setValue(formrespuestas);
                    Toast.makeText(getApplicationContext(), "Datos enviados correctamente",
                            Toast.LENGTH_LONG).show();
                    Intent i = new Intent(FormularioActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private void getCargarLocalizacion() {
        consultarLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) FormularioActivity.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        edtLat.setText(""+location.getLatitude());
                        edtLong.setText(""+location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                int permiso = ContextCompat.checkSelfPermission(FormularioActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Toast.makeText(FormularioActivity.this, "Ubicación generada con éxito", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getLocalizacion(){
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
        }

    }
}

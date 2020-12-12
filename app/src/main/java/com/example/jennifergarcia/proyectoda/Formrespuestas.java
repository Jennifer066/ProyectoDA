package com.example.jennifergarcia.proyectoda;

/**
 * Created by Jennifer Garcia on 11/12/2020.
 */

public class Formrespuestas {
    private Double latitud;
    private Double longitud;
    private String nombre;
    private String descripcion;

    public Formrespuestas(){
        super();
    }
    public Formrespuestas(Double latitud, Double longitud, String nombre, String descripcion){
        this.latitud=latitud;
        this.longitud=longitud;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}




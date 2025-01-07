package org.example.cookies.entities;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


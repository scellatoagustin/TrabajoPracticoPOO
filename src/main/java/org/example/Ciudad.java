package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ciudad {
    private String nombre;
    private int cubosEnfermedad;
    private List<Ciudad> conexiones;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.cubosEnfermedad = 0;
        this.conexiones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCubosEnfermedad() {
        return cubosEnfermedad;
    }

    public void agregarCubosEnfermedad(int cantidad) {
        cubosEnfermedad += cantidad;
    }

    public void curarEnfermedad() {
        if (cubosEnfermedad > 0) {
            cubosEnfermedad--;
        }
    }

    public void agregarConexion(Ciudad ciudad) {
        conexiones.add(ciudad);
    }

    public List<Ciudad> getConexiones() {
        return conexiones;
    }

    public void mostrarConexiones() {
        System.out.print("Conectada con: ");
        for (Ciudad conexion : conexiones) {
            System.out.print(conexion.getNombre() + " ");
        }
        System.out.println();
    }
}

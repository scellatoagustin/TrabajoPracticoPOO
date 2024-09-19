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

    public void agregarCubosEnfermedad(int cubos) {
        this.cubosEnfermedad += cubos;
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
        for (Ciudad ciudad : conexiones) {
            System.out.print(ciudad.getNombre() + " ");
        }
        System.out.println();
    }
}

package org.example;

import java.util.List;
import java.util.ArrayList;

public class Ciudad {
    private String nombre; // atributo que almacena el nombre de la ciudad
    private int cubosEnfermedad; // atributo que guarda la cantidad de cubos de enfermedad en la ciudad
    private List<Ciudad> conexiones; // almacena las ciudades conectadas a esta ciudad

    // inicializa una nueva ciudad con un nombre dado
    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.cubosEnfermedad = 0;
        this.conexiones = new ArrayList<>();
    }

    // devuelve el nombre de la ciudad
    public String getNombre() {
        return nombre;
    }

    // devuelve la cantidad de cubos de enfermedad en la ciudad
    public int getCubosEnfermedad() {
        return cubosEnfermedad;
    }

    // se agrega una cantidad específica de cubos de enfermedad a la ciudad
    public void agregarCubosEnfermedad(int cantidad) {
        cubosEnfermedad += cantidad; // aumenta la cantidad de cubos de enfermedad en la ciudad
    }

    // se cura un cubo de enfermedad si hay cubos disponibles
    public void curarEnfermedad() {
        if (cubosEnfermedad > 0) {
            cubosEnfermedad--;
        }
    }

    // agrega una ciudad a la lista de conexiones
    public void agregarConexion(Ciudad ciudad) {
        conexiones.add(ciudad);
    }

    // devuelve la lista de ciudades conectadas
    public List<Ciudad> getConexiones() {
        return conexiones;
    }

    // muestra las ciudades conectadas a esta ciudad
    public void mostrarConexiones() {
        System.out.print("Conectada con: "); // Mensaje inicial
        for (Ciudad conexion : conexiones) { // Itera sobre cada ciudad conectada
            System.out.print(conexion.getNombre() + " "); // Imprime el nombre de la ciudad conectada
        }
        System.out.println();
    }
}

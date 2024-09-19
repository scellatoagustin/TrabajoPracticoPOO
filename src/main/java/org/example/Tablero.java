package org.example;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private Map<String, Ciudad> ciudades;

    public Tablero() {
        ciudades = new HashMap<>();
    }

    public void agregarCiudad(Ciudad ciudad) {
        ciudades.put(ciudad.getNombre(), ciudad);
    }

    public Ciudad getCiudad(String nombre) {
        return ciudades.get(nombre);
    }

    public Map<String, Ciudad> getCiudades() { // Método agregado para resolver el error
        return ciudades;
    }

    public void mostrarEstado() {
        for (Ciudad ciudad : ciudades.values()) {
            System.out.println(ciudad.getNombre() + ": " + ciudad.getCubosEnfermedad() + " cubos de enfermedad");
            ciudad.mostrarConexiones();
        }
    }

    public void agregarCubosEpidemia() {
        for (Ciudad ciudad : ciudades.values()) {
            if (ciudad.getCubosEnfermedad() < 3) {
                ciudad.agregarCubosEnfermedad(1);
                System.out.println("¡Epidemia! Se añadieron cubos a " + ciudad.getNombre());
                break;
            }
        }
    }
}

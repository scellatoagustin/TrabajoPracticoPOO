package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tablero {
    private Map<String, Ciudad> ciudades;
    private Random random;

    public Tablero() {
        ciudades = new HashMap<>();
        random = new Random();
    }

    public void agregarCiudad(Ciudad ciudad) {
        ciudades.put(ciudad.getNombre(), ciudad);
    }

    public Ciudad getCiudad(String nombre) {
        return ciudades.get(nombre);
    }

    public Map<String, Ciudad> getCiudades() {
        return ciudades;
    }

    public void mostrarEstado() {
        for (Ciudad ciudad : ciudades.values()) {
            System.out.println(ciudad.getNombre() + ": " + ciudad.getCubosEnfermedad() + " cubos de enfermedad");
            ciudad.mostrarConexiones();
        }
    }

    public List<Ciudad> agregarCubosEpidemia() {
        int numCubos = random.nextInt(100) < 70 ? 1 : 2;  // 70% de probabilidad para 1 cubo, 30% para 2 cubos
        List<Ciudad> ciudadesAfectadas = new ArrayList<>();
        List<Ciudad> listaCiudades = new ArrayList<>(ciudades.values());

        for (int i = 0; i < numCubos; i++) {
            Ciudad ciudad = listaCiudades.get(random.nextInt(listaCiudades.size()));
            if (ciudad.getCubosEnfermedad() < 4) { // Cambiar a 4 para la condición de derrota
                ciudad.agregarCubosEnfermedad(1);
                ciudadesAfectadas.add(ciudad);
                System.out.println("¡Epidemia! Se añadió un cubo a " + ciudad.getNombre());
            }
        }
        return ciudadesAfectadas; // Retorna las ciudades afectadas
    }

    public boolean verificarDerrota() {
        for (Ciudad ciudad : ciudades.values()) {
            if (ciudad.getCubosEnfermedad() >= 4) { // Cambiar a 4 para la condición de derrota
                return true;
            }
        }
        return false;
    }

    public boolean verificarVictoria() {
        for (Ciudad ciudad : ciudades.values()) {
            if (ciudad.getCubosEnfermedad() > 0) {
                return false;
            }
        }
        return true;
    }
}

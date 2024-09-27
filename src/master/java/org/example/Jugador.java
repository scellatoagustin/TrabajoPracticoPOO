package org.example;

public class Jugador {
    private String nombre; // Nombre del jugador
    private Ciudad ciudadActual; // Ciudad en la que se encuentra el jugador


    public Jugador(String nombre, Ciudad ciudadInicial) {
        this.nombre = nombre; // Asigna el nombre pasado como argumento al atributo 'nombre'
        this.ciudadActual = ciudadInicial; // Establece la ciudad inicial donde comienza el jugador
    }

    // obtener el nombre del jugador
    public String getNombre() {
        return nombre; // Devuelve el nombre del jugador
    }

    // obtener la ciudad actual del jugador
    public Ciudad getCiudadActual() {
        return ciudadActual; // Devuelve la ciudad en la que se encuentra el jugador
    }

    // mover al jugador a una nueva ciudad
    public void mover(Ciudad nuevaCiudad) {
        ciudadActual = nuevaCiudad; // Actualiza la ciudad actual al nuevo valor proporcionado
    }

    // curar enfermedades en la ciudad actual
    public void curar() {
        ciudadActual.curarEnfermedad(); // Llama al metodo curarEnfermedad() de la ciudad actual
        System.out.println("--------------------");
        System.out.println(nombre + " está curando enfermedades en " + ciudadActual.getNombre());
        System.out.println("--------------------");
    }
}

package org.example;

public class Jugador {
    private String nombre;
    private Ciudad ciudadActual;

    public Jugador(String nombre, Ciudad ciudadInicial) {
        this.nombre = nombre;
        this.ciudadActual = ciudadInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    public void mover(Ciudad nuevaCiudad) {
        ciudadActual = nuevaCiudad;
    }

    public void curar() {
        ciudadActual.curarEnfermedad();
        System.out.println(nombre + " está curando enfermedades en " + ciudadActual.getNombre());
    }

    public void investigar() {
        if (ciudadActual.getCubosEnfermedad() > 0) {
            System.out.println(nombre + " no puede investigar en " + ciudadActual.getNombre() + " porque hay cubos de enfermedad.");
        } else {
            System.out.println(nombre + " está investigando en " + ciudadActual.getNombre());
        }
    }
}

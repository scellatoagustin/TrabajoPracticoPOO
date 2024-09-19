package org.example;

import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private Jugador jugadorA;
    private Jugador jugadorB;
    private Scanner scanner;

    public Juego() {
        tablero = new Tablero();
        scanner = new Scanner(System.in);
        inicializarCiudades();
        inicializarJugadores();
    }

    private void inicializarCiudades() {
        Ciudad madrid = new Ciudad("Madrid");
        Ciudad barcelona = new Ciudad("Barcelona");
        Ciudad paris = new Ciudad("París");
        Ciudad londres = new Ciudad("Londres");

        madrid.agregarConexion(barcelona);
        madrid.agregarConexion(paris);

        barcelona.agregarConexion(madrid);
        barcelona.agregarConexion(londres);

        paris.agregarConexion(madrid);

        londres.agregarConexion(barcelona);

        tablero.agregarCiudad(madrid);
        tablero.agregarCiudad(barcelona);
        tablero.agregarCiudad(paris);
        tablero.agregarCiudad(londres);
    }

    private void inicializarJugadores() {
        Ciudad ciudadInicial = tablero.getCiudad("Madrid");
        jugadorA = new Jugador("A", ciudadInicial);
        jugadorB = new Jugador("B", ciudadInicial);
    }

    public void jugar() {
        boolean juegoTerminado = false;
        Jugador jugadorActual = jugadorA;

        while (!juegoTerminado) {
            System.out.println("Es el turno de " + jugadorActual.getNombre());
            boolean accionRealizada = false;

            while (!accionRealizada) {
                mostrarOpciones();
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> {
                        jugadorActual.curar();
                        accionRealizada = true;
                    }
                    case 2 -> {
                        moverJugador(jugadorActual);
                        accionRealizada = true;
                    }
                    case 3 -> {
                        tablero.mostrarEstado();
                    }
                    case 4 -> {
                        accionRealizada = true;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }

            tablero.agregarCubosEpidemia();
            juegoTerminado = verificarFinDelJuego();
            jugadorActual = (jugadorActual == jugadorA) ? jugadorB : jugadorA;
        }
    }

    private void moverJugador(Jugador jugador) {
        Ciudad ciudadActual = jugador.getCiudadActual();
        System.out.println("Te encuentras en " + ciudadActual.getNombre() + ". Estas son las ciudades conectadas:");
        for (Ciudad conexion : ciudadActual.getConexiones()) {
            System.out.println("- " + conexion.getNombre());
        }
        System.out.print("Ingresa el nombre de la ciudad a la que te quieres mover: ");
        String nombreCiudad = scanner.next();
        Ciudad nuevaCiudad = tablero.getCiudad(nombreCiudad);
        if (nuevaCiudad != null) {
            jugador.mover(nuevaCiudad);
            System.out.println("Te has movido a " + nuevaCiudad.getNombre());
        } else {
            System.out.println("Ciudad no válida.");
        }
    }

    private void mostrarOpciones() {
        System.out.println("1. Realizar acciones");
        System.out.println("2. Mover a otra ciudad");
        System.out.println("3. Ver el estado del tablero");
        System.out.println("4. Terminar turno");
        System.out.print("Selecciona una opción: ");
    }

    private boolean verificarFinDelJuego() {
        for (Ciudad ciudad : tablero.getCiudades().values()) {
            if (ciudad.getCubosEnfermedad() >= 4) {
                System.out.println("¡Perdiste!");
                return true;
            }
        }

        if (tablero.getCiudad("Madrid").getCubosEnfermedad() == 0 &&
                tablero.getCiudad("Barcelona").getCubosEnfermedad() == 0 &&
                tablero.getCiudad("París").getCubosEnfermedad() == 0 &&
                tablero.getCiudad("Londres").getCubosEnfermedad() == 0) {
            System.out.println("¡Ganaste!");
            return true;
        }

        return false;
    }
}

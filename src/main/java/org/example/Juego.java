package org.example;

import java.util.List;
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
        iniciarInfecciones();  // Inicia el juego con algunas infecciones
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

    private void iniciarInfecciones() {
        tablero.getCiudad("Madrid").agregarCubosEnfermedad(2);
        tablero.getCiudad("Barcelona").agregarCubosEnfermedad(1);
        System.out.println("Las ciudades han sido infectadas.");
    }

    public void jugar() {
        boolean juegoTerminado = false;
        Jugador jugadorActual = jugadorA;

        while (!juegoTerminado) {
            System.out.println("\n--- Turno de " + jugadorActual.getNombre() + " ---");
            System.out.println("Te encuentras en: " + jugadorActual.getCiudadActual().getNombre());
            int accionesRestantes = 2;  // Cada jugador tiene 2 acciones por turno

            while (accionesRestantes > 0) {
                mostrarOpciones(accionesRestantes);
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> {
                        jugadorActual.curar();
                        accionesRestantes--;
                    }
                    case 2 -> {
                        moverJugador(jugadorActual);
                        accionesRestantes--;
                    }
                    case 3 -> {
                        mostrarEstadoTablero();
                    }
                    case 4 -> {
                        accionesRestantes = 0;  // Termina el turno
                    }
                    default -> System.out.println("Opción no válida.");
                }

                // Solo verificar victoria o derrota después de cada turno completo
                if (accionesRestantes == 0) {
                    List<Ciudad> ciudadesAfectadas = tablero.agregarCubosEpidemia();  // Progresar el juego con epidemias
                    if (tablero.verificarVictoria()) {
                        System.out.println("¡Ganaste! Todos los cubos de enfermedad han sido eliminados.");
                        juegoTerminado = true;
                    } else if (tablero.verificarDerrota()) {
                        System.out.print("¡Perdiste! Las siguientes ciudades tienen 4 o más cubos de enfermedad: ");
                        for (Ciudad ciudad : ciudadesAfectadas) {
                            if (ciudad.getCubosEnfermedad() >= 4) {
                                System.out.print(ciudad.getNombre() + " ");
                            }
                        }
                        System.out.println();
                        juegoTerminado = true;
                    } else {
                        jugadorActual = (jugadorActual == jugadorA) ? jugadorB : jugadorA;  // Cambio de turno
                    }
                }
            }
        }

        // Mostrar el estado final del tablero
        System.out.println("\n--- Estado Final del Tablero ---");
        tablero.mostrarEstado();
        System.out.println("-------------------------------");
    }

    private void mostrarEstadoTablero() {
        System.out.println("\n--- Estado del Tablero ---");
        tablero.mostrarEstado();
        System.out.println("---------------------------");
    }

    private void moverJugador(Jugador jugador) {
        Ciudad ciudadActual = jugador.getCiudadActual();
        System.out.println("\n--- Mover Jugador ---");
        System.out.println("Te encuentras en " + ciudadActual.getNombre() + ". Estas son las ciudades conectadas:");
        for (Ciudad conexion : ciudadActual.getConexiones()) {
            System.out.println("- " + conexion.getNombre());
        }
        System.out.print("Ingresa el nombre de la ciudad a la que te quieres mover: ");
        String nombreCiudadDestino = scanner.next();
        Ciudad ciudadDestino = tablero.getCiudad(nombreCiudadDestino);

        if (ciudadDestino != null && ciudadActual.getConexiones().contains(ciudadDestino)) {
            jugador.mover(ciudadDestino);
            System.out.println("Te has movido a " + ciudadDestino.getNombre());
        } else {
            System.out.println("Movimiento no válido.");
        }
    }

    private void mostrarOpciones(int accionesRestantes) {
        System.out.println("Tienes " + accionesRestantes + " acciones restantes.");
        System.out.println("1. Realizar acciones");
        System.out.println("2. Mover a otra ciudad");
        System.out.println("3. Ver el estado del tablero");
        System.out.println("4. Terminar turno");
        System.out.print("Selecciona una opción: ");
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.jugar();
    }
}

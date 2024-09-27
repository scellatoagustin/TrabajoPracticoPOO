package org.example;

import java.util.List;
import java.util.Scanner;

public class Juego {
    private Tablero tablero; // representa el tablero del juego
    private Jugador jugadorA; // Primer jugador
    private Jugador jugadorB; // Segundo jugador
    private Scanner scanner; // captura entrada del usuario

    public Juego() {
        tablero = new Tablero(); // Inicializa el tablero del juego
        scanner = new Scanner(System.in); // Inicializa el escáner para entrada de usuario
        inicializarCiudades();
        inicializarJugadores();
        iniciarInfecciones(); // Inicia el juego con algunas infecciones
    }

    // inicializar las ciudades y sus conexiones
    private void inicializarCiudades() {
        // Crea varias instancias de ciudades
        Ciudad madrid = new Ciudad("Madrid");
        Ciudad barcelona = new Ciudad("Barcelona");
        Ciudad paris = new Ciudad("París");
        Ciudad londres = new Ciudad("Londres");

        // Establece conexiones entre las ciudades
        madrid.agregarConexion(barcelona);
        madrid.agregarConexion(paris);

        barcelona.agregarConexion(madrid);
        barcelona.agregarConexion(londres);

        paris.agregarConexion(madrid);

        londres.agregarConexion(barcelona);

        // Agrega las ciudades al tablero
        tablero.agregarCiudad(madrid);
        tablero.agregarCiudad(barcelona);
        tablero.agregarCiudad(paris);
        tablero.agregarCiudad(londres);
    }

    //  inicializa los jugadores
    private void inicializarJugadores() {
        Ciudad ciudadInicial = tablero.getCiudad("Madrid"); // Establece la ciudad inicial como Madrid
        jugadorA = new Jugador("Jugador_1", ciudadInicial);
        jugadorB = new Jugador("Jugador_2", ciudadInicial);
    }

    // inicia infecciones en las ciudades
    private void iniciarInfecciones() {
        tablero.getCiudad("Madrid").agregarCubosEnfermedad(2);
        tablero.getCiudad("Barcelona").agregarCubosEnfermedad(1);
        System.out.println("Las ciudades han sido infectadas.");
    }

    // metodo principal que controla el flujo del juego
    public void jugar() {
        boolean juegoTerminado = false; // Variable que indica si el juego ha terminado
        Jugador jugadorActual = jugadorA; // Variable que indica que el jugadorA empieza

        // Ciclo del juego
        while (!juegoTerminado) {
            System.out.println("\n--- Turno de " + jugadorActual.getNombre() + " ---");
            System.out.println("Te encuentras en: " + jugadorActual.getCiudadActual().getNombre()); // Muestra la ciudad actual del jugador
            int accionesRestantes = 2; // cada jugador tiene 2 acciones por turno

            // Ciclo para las acciones del jugador
            while (accionesRestantes > 0) {
                mostrarOpciones(accionesRestantes); // Muestra las opciones disponibles al jugador
                int opcion = scanner.nextInt(); // Captura la opción seleccionada

                // Ejecuta la acción seleccionada por el jugador
                switch (opcion) {
                    case 1 -> {
                        jugadorActual.curar(); // El jugador intenta curar
                        accionesRestantes--; //
                    }
                    case 2 -> {
                        moverJugador(jugadorActual); // El jugador intenta moverse a otra ciudad
                        accionesRestantes--;
                    }
                    case 3 -> {
                        mostrarEstadoTablero(); // Muestra el estado actual del tablero
                    }
                    case 4 -> {
                        accionesRestantes = 0; // Termina el turno del jugador
                    }
                    default -> System.out.println("Opción no válida.");
                }

                // Verifica si se han terminado las acciones
                if (accionesRestantes == 0) {
                    List<Ciudad> ciudadesAfectadas = tablero.agregarCubosEpidemia(); // Progresar el juego con epidemias
                    // Verifica condiciones de victoria o derrota
                    if (tablero.verificarVictoria()) {
                        System.out.println("¡Ganaste! Todos los cubos de enfermedad han sido eliminados."); // Mensaje de victoria
                        juegoTerminado = true; // Termina el juego
                    } else if (tablero.verificarDerrota()) {
                        System.out.print("¡Perdiste! Las siguientes ciudades tienen 4 o más cubos de enfermedad: ");
                        for (Ciudad ciudad : ciudadesAfectadas) {
                            if (ciudad.getCubosEnfermedad() >= 4) {
                                System.out.print(ciudad.getNombre() + " "); // Muestra las ciudades con condiciones de derrota
                            }
                        }
                        System.out.println();
                        juegoTerminado = true; // Termina el juego
                    } else {
                        // Cambia de turno al siguiente jugador
                        jugadorActual = (jugadorActual == jugadorA) ? jugadorB : jugadorA;
                    }
                }
            }
        }

        // Muestra el estado final del tablero
        System.out.println("\n--- Estado Final del Tablero ---");
        tablero.mostrarEstado();
        System.out.println("-------------------------------");
    }

    // metodo para mostrar el estado del tablero
    private void mostrarEstadoTablero() {
        System.out.println("\n---- Estado del Tablero ----");
        tablero.mostrarEstado(); // Muestra el estado actual de todas las ciudades
        System.out.println("---------------------------");
    }

    // mover al jugador a otra ciudad
    private void moverJugador(Jugador jugador) {
        Ciudad ciudadActual = jugador.getCiudadActual(); // Obtiene la ciudad actual del jugador
        System.out.println("\n--- Mover Jugador ---");
        System.out.println("Te encuentras en " + ciudadActual.getNombre() + ". Estas son las ciudades conectadas:");
        for (Ciudad conexion : ciudadActual.getConexiones()) {
            System.out.println("- " + conexion.getNombre()); // Muestra las ciudades conectadas
        }
        System.out.print("Ingresa el nombre de la ciudad a la que te quieres mover: ");
        String nombreCiudadDestino = scanner.next(); // se introduce el nombre de la ciudad a mover
        Ciudad ciudadDestino = tablero.getCiudad(nombreCiudadDestino); // Busca la ciudad en el tablero

        // Verifica si el movimiento es valido
        if (ciudadDestino != null && ciudadActual.getConexiones().contains(ciudadDestino)) {
            jugador.mover(ciudadDestino); // Mueve al jugador a la nueva ciudad
            System.out.println("Te has movido a " + ciudadDestino.getNombre());
        } else {
            System.out.println("Movimiento no válido.");
        }
    }

    // mostrar las opciones de accion al jugador
    private void mostrarOpciones(int accionesRestantes) {
        System.out.println("Tienes " + accionesRestantes + " acciones restantes."); // Muestra las acciones restantes
        System.out.println("1. Realizar acciones");
        System.out.println("2. Mover a otra ciudad");
        System.out.println("3. Ver el estado del tablero");
        System.out.println("4. Terminar turno");
        System.out.print("Selecciona una opción: ");
    }

    // Metodo principal que inicia el juego
    public static void main(String[] args) {
        Juego juego = new Juego(); // Crea una instancia del juego
        juego.jugar();
    }
}

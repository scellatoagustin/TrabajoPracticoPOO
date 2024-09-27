package org.example;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private Map<String, Ciudad> ciudades; // Mapa que almacena las ciudades, donde la clave es el nombre de la ciudad
    private Random random; // Generador de números aleatorios


    public Tablero() {
        ciudades = new HashMap<>(); // inicializa el mapa de ciudades
        random = new Random(); // inicializa el generador aleatorio
        inicializarCiudades(); // llama a metodo para inicializar las ciudades y sus conexiones
    }

    // Metodo privado para inicializar ciudades y sus conexiones
    private void inicializarCiudades() {
        // Crea las ciudades
        Ciudad madrid = new Ciudad("Madrid");
        Ciudad barcelona = new Ciudad("Barcelona");
        Ciudad paris = new Ciudad("Paris");
        Ciudad londres = new Ciudad("Londres");

        // Definir conexiones predefinidas entre las ciudades
        madrid.agregarConexion(barcelona);
        madrid.agregarConexion(paris);

        barcelona.agregarConexion(madrid);
        barcelona.agregarConexion(londres);

        paris.agregarConexion(madrid);

        londres.agregarConexion(barcelona);

        // Agregar las ciudades al mapa de ciudades
        agregarCiudad(madrid);
        agregarCiudad(barcelona);
        agregarCiudad(paris);
        agregarCiudad(londres);
    }

    // agrega una ciudad al mapa
    public void agregarCiudad(Ciudad ciudad) {
        ciudades.put(ciudad.getNombre(), ciudad); // Almacena la ciudad en el mapa usando su nombre como clave
    }

    // obtener una ciudad del mapa dado su nombre
    public Ciudad getCiudad(String nombre) {
        return ciudades.get(nombre); // Devuelve la ciudad correspondiente al nombre
    }

    // mostrar el estado actual de todas las ciudades en el tablero
    public void mostrarEstado() {
        for (Ciudad ciudad : ciudades.values()) {// itera sobre todas las ciudades en el mapa

            System.out.println(ciudad.getNombre() + ": " + ciudad.getCubosEnfermedad() + " cubos de enfermedad");
            ciudad.mostrarConexiones();
            System.out.println("-----------");
        }
    }

    // se agrega cubos de epidemia de forma aleatoria a las ciudades
    public List<Ciudad> agregarCubosEpidemia() {
        int numCubos = random.nextInt(100) < 70 ? 1 : 2; // 70% de probabilidad para agregar 1 cubo, 30% para 2
        List<Ciudad> ciudadesAfectadas = new ArrayList<>(); // Lista de ciudades afectadas
        List<Ciudad> listaCiudades = new ArrayList<>(ciudades.values()); // lista con todas las ciudades

        // Agrega los cubos a las ciudades seleccionadas aleatoriamente
        for (int i = 0; i < numCubos; i++) {
            Ciudad ciudad = listaCiudades.get(random.nextInt(listaCiudades.size())); // Selecciona una ciudad aleatoria
            if (ciudad.getCubosEnfermedad() < 4) {          // Verifica que la ciudad no tenga 4 o más cubos
                ciudad.agregarCubosEnfermedad(1);   // Agrega un cubo de enfermedad a la ciudad
                ciudadesAfectadas.add(ciudad);              // Añade la ciudad a la lista de afectadas
                System.out.println("EPIDEMIA Se añadió un cubo a " + ciudad.getNombre());
            }
        }
        return ciudadesAfectadas; // Devuelve la lista de ciudades afectadas
    }

    // Metodo para verificar si hay derrota en el juego
    public boolean verificarDerrota() {
        for (Ciudad ciudad : ciudades.values()) { // Itera sobre todas las ciudades
            if (ciudad.getCubosEnfermedad() >= 4) {
                return true; // Retorna verdadero si hay derrota
            }
        }
        return false; // Retorna falso si no hay derrota
    }

    // verificar si hay victoria en el juego
    public boolean verificarVictoria() {
        for (Ciudad ciudad : ciudades.values()) { // Itera sobre todas las ciudades
            if (ciudad.getCubosEnfermedad() > 0) { // Verifica si alguna ciudad tiene cubos de enfermedad
                return false; // Retorna falso si hay cubos, osea que no se ganó
            }
        }
        return true; // Retorna verdadero si todas las ciudades están libres de cubos
    }
}

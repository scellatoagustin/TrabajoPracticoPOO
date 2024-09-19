package org.example;

import java.util.*;

public class GestorCartas {
    private List<Carta> mazoInfeccion;
    private List<Carta> mazoEventos;
    private List<Carta> pilaDescarteInfeccion;
    private List<Carta> pilaDescarteEventos;

    public GestorCartas() {
        mazoInfeccion = new ArrayList<>();
        mazoEventos = new ArrayList<>();
        pilaDescarteInfeccion = new ArrayList<>();
        pilaDescarteEventos = new ArrayList<>();
        inicializarMazos();
    }

    public void inicializarMazos() {
        // Inicializa los mazos de cartas
    }

    public void barajarMazos() {
        Collections.shuffle(mazoInfeccion);
        Collections.shuffle(mazoEventos);
    }

    public Carta robarCartaInfeccion() {
        if (mazoInfeccion.isEmpty()) {
            restablecerMazoInfeccion();
        }
        Carta carta = mazoInfeccion.remove(mazoInfeccion.size() - 1);
        pilaDescarteInfeccion.add(carta);
        return carta;
    }

    public Carta robarCartaEvento() {
        if (mazoEventos.isEmpty()) {
            restablecerMazoEventos();
        }
        Carta carta = mazoEventos.remove(mazoEventos.size() - 1);
        pilaDescarteEventos.add(carta);
        return carta;
    }

    public void restablecerMazoInfeccion() {
        mazoInfeccion.addAll(pilaDescarteInfeccion);
        pilaDescarteInfeccion.clear();
        barajarMazos();
    }

    public void restablecerMazoEventos() {
        mazoEventos.addAll(pilaDescarteEventos);
        pilaDescarteEventos.clear();
        barajarMazos();
    }

    public List<Carta> obtenerPilaDescarteInfeccion() {
        return pilaDescarteInfeccion;
    }

    public List<Carta> obtenerPilaDescarteEventos() {
        return pilaDescarteEventos;
    }
}

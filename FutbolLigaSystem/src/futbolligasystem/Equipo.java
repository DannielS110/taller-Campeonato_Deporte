/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

import java.util.List;
import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    public void añadirJugador(Jugador jugador) {
        jugadores.add(jugador);
        System.out.println("Jugador " + jugador.getNombre() + " añadido al equipo " + this.nombre);
    }

    public void eliminarJugador(Jugador jugador) {
        if (jugadores.remove(jugador)) {
            System.out.println("Jugador " + jugador.getNombre() + " eliminado del equipo " + this.nombre);
        } else {
            System.out.println("El jugador " + jugador.getNombre() + " no pertenece al equipo " + this.nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
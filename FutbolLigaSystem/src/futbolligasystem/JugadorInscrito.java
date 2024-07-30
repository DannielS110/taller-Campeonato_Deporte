/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

/**
 *
 * @author Dannn
 */
import java.util.Date;

public class JugadorInscrito {
    private Jugador jugador;
    private EquipoInscrito equipoInscrito;
    private Date fechaInscripcion;

    public JugadorInscrito(Jugador jugador, EquipoInscrito equipoInscrito) {
        this.jugador = jugador;
        this.equipoInscrito = equipoInscrito;
        this.fechaInscripcion = new Date();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public EquipoInscrito getEquipoInscrito() {
        return equipoInscrito;
    }

    public void setEquipoInscrito(EquipoInscrito equipoInscrito) {
        this.equipoInscrito = equipoInscrito;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
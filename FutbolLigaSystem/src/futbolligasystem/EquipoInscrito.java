/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipoInscrito implements IGestionable {
    private Equipo equipo;
    private Campeonato campeonato;
    private Date fechaInscripcion;
    private boolean pagado;
    private List<JugadorInscrito> jugadoresInscritos;
    private EstadoInscripcion estado;
    private Estadistica estadistica;

    public EquipoInscrito(Equipo equipo, Campeonato campeonato) {
        this.equipo = equipo;
        this.campeonato = campeonato;
        this.fechaInscripcion = new Date();
        this.pagado = false;
        this.jugadoresInscritos = new ArrayList<>();
        this.estado = EstadoInscripcion.PENDIENTE;
        this.estadistica = new Estadistica(this);
    }

    public void realizarInscripcion() {
        if (this.estado == EstadoInscripcion.PENDIENTE) {
            this.estado = EstadoInscripcion.APROBADA;
            this.pagado = true;
            System.out.println("Inscripción realizada con éxito para el equipo " + equipo.getNombre() + " en el campeonato " + campeonato.getNombre());
        } else {
            System.out.println("No se puede realizar la inscripción. Estado actual: " + this.estado);
        }
    }

    public void añadirJugadorInscrito(Jugador jugador) {
        JugadorInscrito jugadorInscrito = new JugadorInscrito(jugador, this);
        jugadoresInscritos.add(jugadorInscrito);
        System.out.println("Jugador " + jugador.getNombre() + " inscrito en el equipo " + equipo.getNombre() + " para el campeonato");
    }

    public void eliminarJugadorInscrito(Jugador jugador) {
        jugadoresInscritos.removeIf(ji -> ji.getJugador().equals(jugador));
        System.out.println("Jugador " + jugador.getNombre() + " eliminado de la inscripción del equipo " + equipo.getNombre());
    }

    @Override
    public void gestionar() {
        System.out.println("Gestionando equipo inscrito: " + equipo.getNombre());
        System.out.println("Verificando pago de inscripción");
        System.out.println("Actualizando lista de jugadores inscritos");
        System.out.println("Revisando estado de la inscripción");
    }

    // Getters y setters
    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public List<JugadorInscrito> getJugadoresInscritos() {
        return jugadoresInscritos;
    }

    public void setJugadoresInscritos(List<JugadorInscrito> jugadoresInscritos) {
        this.jugadoresInscritos = jugadoresInscritos;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }
}
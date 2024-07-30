/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

/**
 *
 * @author Dannn
 */
public class Jugador implements IRegistrable {
    private String nombre;
    private int numero;
    private PosicionJugador posicion;
    private Equipo equipoActual;

    public Jugador(String nombre, int numero, PosicionJugador posicion) {
        this.nombre = nombre;
        this.numero = numero;
        this.posicion = posicion;
    }

    public void cambiarEquipo(Equipo nuevoEquipo) {
        if (this.getEquipoActual() != null) {
            this.getEquipoActual().eliminarJugador(this);
        }
        this.setEquipoActual(nuevoEquipo);
        nuevoEquipo.añadirJugador(this);
        System.out.println("Jugador " + this.getNombre() + " ha cambiado al equipo " + nuevoEquipo.getNombre());
    }

    
    public void registrar() {
        System.out.println("Registrando jugador: " + this.getNombre() + " (Número: " + this.getNumero() + ", Posición: " + this.getPosicion() + ")");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public PosicionJugador getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionJugador posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipoActual() {
        return equipoActual;
    }

    public void setEquipoActual(Equipo equipoActual) {
        this.equipoActual = equipoActual;
    }
}
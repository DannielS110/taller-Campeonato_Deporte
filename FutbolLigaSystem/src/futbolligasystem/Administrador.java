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

public class Administrador {
    private String nombre;
    private String email;

    public Administrador(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Campeonato crearCampeonato(String nombre, Date fechaInicio, Date fechaFin, TipoCampeonato tipo) {
        return new Campeonato(nombre, fechaInicio, fechaFin, tipo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
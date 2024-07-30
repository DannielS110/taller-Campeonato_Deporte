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
import java.util.List;
import java.util.ArrayList;

public class Campeonato {
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoCampeonato tipo;
    private List<EquipoInscrito> equiposInscritos;
    private List<Partido> partidos;
    private TablaPosiciones tablaPosiciones;

    public Campeonato(String nombre, Date fechaInicio, Date fechaFin, TipoCampeonato tipo) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
        this.equiposInscritos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.tablaPosiciones = new TablaPosiciones();
    }

    public void crearPartido(EquipoInscrito equipoLocal, EquipoInscrito equipoVisitante) {
        Partido partido = new Partido(equipoLocal, equipoVisitante);
        partidos.add(partido);
    }

    public void generarTablaPosiciones() {
        // Implementación para generar la tabla de posiciones
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoCampeonato getTipo() {
        return tipo;
    }

    public void setTipo(TipoCampeonato tipo) {
        this.tipo = tipo;
    }

    public List<EquipoInscrito> getEquiposInscritos() {
        return equiposInscritos;
    }

    public void setEquiposInscritos(List<EquipoInscrito> equiposInscritos) {
        this.equiposInscritos = equiposInscritos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public TablaPosiciones getTablaPosiciones() {
        return tablaPosiciones;
    }

    public void setTablaPosiciones(TablaPosiciones tablaPosiciones) {
        this.tablaPosiciones = tablaPosiciones;
    }
}
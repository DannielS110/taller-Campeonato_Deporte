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

public class Partido {
    private Date fecha;
    private EquipoInscrito equipoLocal;
    private EquipoInscrito equipoVisitante;
    private Arbitro arbitro;
    private int golesLocal;
    private int golesVisitante;

    public Partido(EquipoInscrito equipoLocal, EquipoInscrito equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = new Date();
    }

    public void registrarResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        // Actualizar estadísticas
    }

    public ResultadoPartido obtenerResultado(EquipoInscrito equipo) {
        if (golesLocal == golesVisitante) return ResultadoPartido.EMPATADO;
        if (equipo == equipoLocal) {
            return golesLocal > golesVisitante ? ResultadoPartido.GANADO : ResultadoPartido.PERDIDO;
        } else {
            return golesVisitante > golesLocal ? ResultadoPartido.GANADO : ResultadoPartido.PERDIDO;
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EquipoInscrito getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(EquipoInscrito equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public EquipoInscrito getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(EquipoInscrito equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

/**
 *
 * @author Dannn
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class TablaPosiciones {
    private List<Estadistica> estadisticas;

    public TablaPosiciones() {
        this.estadisticas = new ArrayList<>();
    }

    public void actualizarTabla(Partido partido) {
        Estadistica estadisticaLocal = getEstadisticaEquipo(partido.getEquipoLocal());
        Estadistica estadisticaVisitante = getEstadisticaEquipo(partido.getEquipoVisitante());
        
        estadisticaLocal.actualizarEstadisticas(partido);
        estadisticaVisitante.actualizarEstadisticas(partido);
        
        ordenarTabla();
    }

    public int obtenerPosicion(EquipoInscrito equipo) {
        for (int i = 0; i < estadisticas.size(); i++) {
            if (estadisticas.get(i).getEquipoInscrito().equals(equipo)) {
                return i + 1;
            }
        }
        return -1; // Si el equipo no está en la tabla
    }

    public Estadistica obtenerResultadoEquipo(EquipoInscrito equipo) {
        return estadisticas.stream()
                .filter(e -> e.getEquipoInscrito().equals(equipo))
                .findFirst()
                .orElse(null);
    }

    private Estadistica getEstadisticaEquipo(EquipoInscrito equipo) {
        return estadisticas.stream()
                .filter(e -> e.getEquipoInscrito().equals(equipo))
                .findFirst()
                .orElseGet(() -> {
                    Estadistica nuevaEstadistica = new Estadistica(equipo);
                    estadisticas.add(nuevaEstadistica);
                    return nuevaEstadistica;
                });
    }

    private void ordenarTabla() {
        estadisticas.sort((e1, e2) -> {
            int comparePuntos = Integer.compare(e2.calcularPuntos(), e1.calcularPuntos());
            if (comparePuntos != 0) return comparePuntos;
            
            int compareDiferenciaGoles = Integer.compare(
                (e2.getGolesFavor() - e2.getGolesContra()),
                (e1.getGolesFavor() - e1.getGolesContra())
            );
            if (compareDiferenciaGoles != 0) return compareDiferenciaGoles;
            
            return Integer.compare(e2.getGolesFavor(), e1.getGolesFavor());
        });
    }

    public List<Estadistica> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<Estadistica> estadisticas) {
        this.estadisticas = estadisticas;
    }
}
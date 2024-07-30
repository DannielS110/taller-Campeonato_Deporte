package futbolligasystem;

import java.util.List;
import java.util.ArrayList;

public class Estadistica {
    private EquipoInscrito equipoInscrito;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;
    private int puntos;
    private List<ResultadoPartido> resultados;

    public Estadistica(EquipoInscrito equipoInscrito) {
        this.equipoInscrito = equipoInscrito;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.puntos = 0;
        this.resultados = new ArrayList<>();
    }

    public void actualizarEstadisticas(Partido partido) {
        partidosJugados++;

        int golesAFavor, golesEnContra;
        ResultadoPartido resultado;

        if (partido.getEquipoLocal().equals(this.equipoInscrito)) {
            golesAFavor = partido.getGolesLocal();
            golesEnContra = partido.getGolesVisitante();
            resultado = partido.obtenerResultado(this.equipoInscrito);
        } else {
            golesAFavor = partido.getGolesVisitante();
            golesEnContra = partido.getGolesLocal();
            resultado = partido.obtenerResultado(this.equipoInscrito);
        }

        golesFavor += golesAFavor;
        golesContra += golesEnContra;

        switch (resultado) {
            case GANADO:
                partidosGanados++;
                puntos += 3;
                break;
            case EMPATADO:
                partidosEmpatados++;
                puntos += 1;
                break;
            case PERDIDO:
                partidosPerdidos++;
                break;
        }

        resultados.add(resultado);
    }

    public EquipoInscrito getEquipoInscrito() {
        return equipoInscrito;
    }

    public void setEquipoInscrito(EquipoInscrito equipoInscrito) {
        this.equipoInscrito = equipoInscrito;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<ResultadoPartido> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoPartido> resultados) {
        this.resultados = resultados;
    }
}
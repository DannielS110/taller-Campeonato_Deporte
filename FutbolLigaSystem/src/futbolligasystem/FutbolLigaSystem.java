package futbolligasystem;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FutbolLigaSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del administrador:");
        String nombreAdmin = scanner.nextLine();
        System.out.println("Ingrese el email del administrador:");
        String emailAdmin = scanner.nextLine();
        Administrador admin = new Administrador(nombreAdmin, emailAdmin);

        System.out.println("Ingrese el nombre del campeonato:");
        String nombreCampeonato = scanner.nextLine();
        Campeonato campeonato = admin.crearCampeonato(nombreCampeonato, new Date(), new Date(), TipoCampeonato.LIGA);

        System.out.println("Ingrese la cantidad de equipos que participaran en el campeonato:");
        int cantidadEquipos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<EquipoInscrito> equiposInscritos = new ArrayList<>();
        for (int i = 1; i <= cantidadEquipos; i++) {
            System.out.println("Ingrese el nombre del Equipo " + i + ":");
            String nombreEquipo = scanner.nextLine();
            Equipo equipo = new Equipo(nombreEquipo);

            for (int j = 1; j <= 11; j++) {
                System.out.println("Ingrese el nombre del Jugador " + j + " del " + nombreEquipo + ":");
                String nombreJugador = scanner.nextLine();
                System.out.println("Ingrese el numero del Jugador " + nombreJugador + ":");
                int numeroJugador = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la posicion del Jugador " + nombreJugador + " (PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO):");
                PosicionJugador posicion = PosicionJugador.valueOf(scanner.nextLine().toUpperCase());

                Jugador jugador = new Jugador(nombreJugador, numeroJugador, posicion);
                equipo.añadirJugador(jugador);
            }

            EquipoInscrito equipoInscrito = new EquipoInscrito(equipo, campeonato);
            equipoInscrito.realizarInscripcion();
            equiposInscritos.add(equipoInscrito);
        }

        // Simular algunos partidos
        for (int i = 0; i < equiposInscritos.size(); i++) {
            for (int j = i + 1; j < equiposInscritos.size(); j++) {
                EquipoInscrito equipoLocal = equiposInscritos.get(i);
                EquipoInscrito equipoVisitante = equiposInscritos.get(j);
                Partido partido = new Partido(equipoLocal, equipoVisitante);

                System.out.println("Partido: " + equipoLocal.getEquipo().getNombre() + " vs " + equipoVisitante.getEquipo().getNombre());
                System.out.println("Ingrese los goles del equipo local (" + equipoLocal.getEquipo().getNombre() + "):");
                int golesLocal = scanner.nextInt();
                System.out.println("Ingrese los goles del equipo visitante (" + equipoVisitante.getEquipo().getNombre() + "):");
                int golesVisitante = scanner.nextInt();
                partido.registrarResultado(golesLocal, golesVisitante);
            }
        }

        campeonato.generarTablaPosiciones();

        TablaPosiciones tabla = campeonato.getTablaPosiciones();
        for (EquipoInscrito equipo : equiposInscritos) {
            System.out.println("Posicion de " + equipo.getEquipo().getNombre() + ": " + tabla.obtenerPosicion(equipo));
        }

        for (EquipoInscrito equipo : equiposInscritos) {
            Estadistica estadistica = tabla.obtenerResultadoEquipo(equipo);
            if (estadistica != null) {
                System.out.println("Estadisticas de " + equipo.getEquipo().getNombre() + ":");
                System.out.println("Partidos jugados: " + estadistica.getPartidosJugados());
                System.out.println("Partidos ganados: " + estadistica.getPartidosGanados());
                System.out.println("Partidos empatados: " + estadistica.getPartidosEmpatados());
                System.out.println("Partidos perdidos: " + estadistica.getPartidosPerdidos());
                System.out.println("Goles a favor: " + estadistica.getGolesFavor());
                System.out.println("Goles en contra: " + estadistica.getGolesContra());
                System.out.println("Puntos: " + estadistica.calcularPuntos());
                System.out.println();
            }
        }

        scanner.close();
    }
}
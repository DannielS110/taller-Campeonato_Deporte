/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package futbolligasystem;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FutbolLigaSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Campeonato campeonato;
    private static Administrador admin;

    public static void main(String[] args) {
        inicializarCampeonato();
        registrarEquiposIniciales();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    inscribirNuevoEquipo();
                    break;
                case 2:
                    registrarResultadoPartido();
                    break;
                case 3:
                    verTablaPosiciones();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void inicializarCampeonato() {
        System.out.println("Ingrese el nombre del administrador:");
        String nombreAdmin = scanner.nextLine();
        System.out.println("Ingrese el email del administrador:");
        String emailAdmin = scanner.nextLine();
        admin = new Administrador(nombreAdmin, emailAdmin);

        System.out.println("Ingrese el nombre del campeonato:");
        String nombreCampeonato = scanner.nextLine();
        campeonato = admin.crearCampeonato(nombreCampeonato, new Date(), new Date(), TipoCampeonato.LIGA);
    }

    private static void registrarEquiposIniciales() {
        System.out.println("Ingrese la cantidad de equipos que participarán inicialmente en el campeonato:");
        int cantidadEquipos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 1; i <= cantidadEquipos; i++) {
            inscribirEquipo();
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Inscribir nuevo equipo");
        System.out.println("2. Registrar resultado de partido");
        System.out.println("3. Ver tabla de posiciones");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void inscribirNuevoEquipo() {
        inscribirEquipo();
    }

    private static void inscribirEquipo() {
        System.out.println("Ingrese el nombre del Equipo:");
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
        campeonato.getEquiposInscritos().add(equipoInscrito);
    }

    private static void registrarResultadoPartido() {
        List<EquipoInscrito> equipos = campeonato.getEquiposInscritos();
        if (equipos.size() < 2) {
            System.out.println("Se necesitan al menos dos equipos para registrar un partido.");
            return;
        }

        System.out.println("Seleccione el equipo local:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getEquipo().getNombre());
        }
        int indexLocal = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Seleccione el equipo visitante:");
        for (int i = 0; i < equipos.size(); i++) {
            if (i != indexLocal) {
                System.out.println((i + 1) + ". " + equipos.get(i).getEquipo().getNombre());
            }
        }
        int indexVisitante = scanner.nextInt() - 1;
        scanner.nextLine();

        EquipoInscrito equipoLocal = equipos.get(indexLocal);
        EquipoInscrito equipoVisitante = equipos.get(indexVisitante);
        Partido partido = new Partido(equipoLocal, equipoVisitante);

        System.out.println("Ingrese los goles del equipo local (" + equipoLocal.getEquipo().getNombre() + "):");
        int golesLocal = scanner.nextInt();
        System.out.println("Ingrese los goles del equipo visitante (" + equipoVisitante.getEquipo().getNombre() + "):");
        int golesVisitante = scanner.nextInt();
        scanner.nextLine();

        partido.registrarResultado(golesLocal, golesVisitante);
        campeonato.getPartidos().add(partido);
        campeonato.getTablaPosiciones().actualizarTabla(partido);
    }

    private static void verTablaPosiciones() {
        TablaPosiciones tabla = campeonato.getTablaPosiciones();
        List<Estadistica> estadisticas = tabla.getEstadisticas();
        
        System.out.println("\n--- Tabla de Posiciones ---");
        System.out.println("Posición | Equipo | PJ | PG | PE | PP | GF | GC | DG | Puntos");
        
        for (int i = 0; i < estadisticas.size(); i++) {
            Estadistica est = estadisticas.get(i);
            EquipoInscrito equipo = est.getEquipoInscrito();
            int diferenciaGoles = est.getGolesFavor() - est.getGolesContra();
            
            System.out.printf("%-8d | %-6s | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-6d\n",
                    (i + 1),
                    equipo.getEquipo().getNombre(),
                    est.getPartidosJugados(),
                    est.getPartidosGanados(),
                    est.getPartidosEmpatados(),
                    est.getPartidosPerdidos(),
                    est.getGolesFavor(),
                    est.getGolesContra(),
                    diferenciaGoles,
                    est.getPuntos());
        }
    }
}
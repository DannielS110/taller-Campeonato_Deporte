package futbolligasystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FutbolLigaSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Campeonato> campeonatos = new ArrayList<>();
    private static Administrador admin;

    public static void main(String[] args) {
        inicializarSistema();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearCampeonato();
                    break;
                case 2:
                    inscribirEquipo();
                    break;
                case 3:
                    registrarResultadoPartido();
                    break;
                case 4:
                    verTablaPosiciones();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        System.out.println("Gracias por usar el sistema de gestión de campeonatos.");
        scanner.close();
    }

    private static void inicializarSistema() {
        System.out.println("Bienvenido al Sistema de Gestión de Campeonatos de Fútbol");
        System.out.println("Ingrese el nombre del administrador:");
        String nombreAdmin = scanner.nextLine();
        System.out.println("Ingrese el email del administrador:");
        String emailAdmin = scanner.nextLine();
        admin = new Administrador(nombreAdmin, emailAdmin);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Crear nuevo campeonato");
        System.out.println("2. Inscribir equipo en campeonato");
        System.out.println("3. Registrar resultado de partido");
        System.out.println("4. Ver tabla de posiciones");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearCampeonato() {
        System.out.println("Ingrese el nombre del nuevo campeonato:");
        String nombreCampeonato = scanner.nextLine();
        Campeonato campeonato = admin.crearCampeonato(nombreCampeonato, new Date(), new Date(), TipoCampeonato.LIGA);
        campeonatos.add(campeonato);
        System.out.println("Campeonato '" + nombreCampeonato + "' creado exitosamente.");
    }

    private static void inscribirEquipo() {
        if (campeonatos.isEmpty()) {
            System.out.println("No hay campeonatos creados. Por favor, cree un campeonato primero.");
            return;
        }

        System.out.println("Seleccione el campeonato para inscribir el equipo:");
        for (int i = 0; i < campeonatos.size(); i++) {
            System.out.println((i + 1) + ". " + campeonatos.get(i).getNombre());
        }
        int indexCampeonato = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        Campeonato campeonatoSeleccionado = campeonatos.get(indexCampeonato);

        System.out.println("Ingrese el nombre del equipo:");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = new Equipo(nombreEquipo);

        for (int j = 1; j <= 11; j++) {
            System.out.println("Ingrese el nombre del Jugador " + j + ":");
            String nombreJugador = scanner.nextLine();
            System.out.println("Ingrese el número del Jugador " + nombreJugador + ":");
            int numeroJugador = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.println("Ingrese la posición del Jugador " + nombreJugador + " (PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO):");
            PosicionJugador posicion = PosicionJugador.valueOf(scanner.nextLine().toUpperCase());

            Jugador jugador = new Jugador(nombreJugador, numeroJugador, posicion);
            equipo.añadirJugador(jugador);
        }

        EquipoInscrito equipoInscrito = new EquipoInscrito(equipo, campeonatoSeleccionado);
        equipoInscrito.realizarInscripcion();
        campeonatoSeleccionado.agregarEquipoInscrito(equipoInscrito);
        System.out.println("Equipo '" + nombreEquipo + "' inscrito exitosamente en el campeonato '" + campeonatoSeleccionado.getNombre() + "'.");
    }

    private static void registrarResultadoPartido() {
        if (campeonatos.isEmpty()) {
            System.out.println("No hay campeonatos creados. Por favor, cree un campeonato primero.");
            return;
        }

        System.out.println("Seleccione el campeonato para registrar el resultado:");
        for (int i = 0; i < campeonatos.size(); i++) {
            System.out.println((i + 1) + ". " + campeonatos.get(i).getNombre());
        }
        int indexCampeonato = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        Campeonato campeonatoSeleccionado = campeonatos.get(indexCampeonato);

        if (campeonatoSeleccionado.getEquiposInscritos().size() < 2) {
            System.out.println("Se necesitan al menos dos equipos para registrar un partido.");
            return;
        }

        System.out.println("Equipos disponibles:");
        for (int i = 0; i < campeonatoSeleccionado.getEquiposInscritos().size(); i++) {
            System.out.println((i + 1) + ". " + campeonatoSeleccionado.getEquiposInscritos().get(i).getEquipo().getNombre());
        }

        System.out.println("Seleccione el número del equipo local:");
        int indexLocal = scanner.nextInt() - 1;
        System.out.println("Seleccione el número del equipo visitante:");
        int indexVisitante = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        EquipoInscrito equipoLocal = campeonatoSeleccionado.getEquiposInscritos().get(indexLocal);
        EquipoInscrito equipoVisitante = campeonatoSeleccionado.getEquiposInscritos().get(indexVisitante);

        System.out.println("Ingrese los goles del equipo local (" + equipoLocal.getEquipo().getNombre() + "):");
        int golesLocal = scanner.nextInt();
        System.out.println("Ingrese los goles del equipo visitante (" + equipoVisitante.getEquipo().getNombre() + "):");
        int golesVisitante = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Partido partido = new Partido(equipoLocal, equipoVisitante);
        partido.registrarResultado(golesLocal, golesVisitante);
        campeonatoSeleccionado.agregarPartido(partido);

        System.out.println("Resultado registrado: " + equipoLocal.getEquipo().getNombre() + " " + golesLocal +
                " - " + golesVisitante + " " + equipoVisitante.getEquipo().getNombre());
    }

    private static void verTablaPosiciones() {
        if (campeonatos.isEmpty()) {
            System.out.println("No hay campeonatos creados. Por favor, cree un campeonato primero.");
            return;
        }

        System.out.println("Seleccione el campeonato para ver la tabla de posiciones:");
        for (int i = 0; i < campeonatos.size(); i++) {
            System.out.println((i + 1) + ". " + campeonatos.get(i).getNombre());
        }
        int indexCampeonato = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        Campeonato campeonatoSeleccionado = campeonatos.get(indexCampeonato);

        System.out.println("\nTabla de Posiciones - " + campeonatoSeleccionado.getNombre());
        System.out.println("Pos | Equipo       | PJ | PG | PE | PP | GF | GC | DG | Pts");
        System.out.println("------------------------------------------------------------");

        TablaPosiciones tabla = campeonatoSeleccionado.getTablaPosiciones();
        for (Estadistica est : tabla.getEstadisticas()) {
            EquipoInscrito equipo = est.getEquipoInscrito();
            System.out.printf("%-3d | %-12s | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-3d\n",
                    tabla.obtenerPosicion(equipo),
                    equipo.getEquipo().getNombre(),
                    est.getPartidosJugados(),
                    est.getPartidosGanados(),
                    est.getPartidosEmpatados(),
                    est.getPartidosPerdidos(),
                    est.getGolesFavor(),
                    est.getGolesContra(),
                    est.getGolesFavor() - est.getGolesContra(),
                    est.getPuntos());
        }
    }
}
package co.edu.uniquindio.Escenario3;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        SistemaTaxi sistema = new SistemaTaxi();

        long inicio100 = System.nanoTime();

        for (int i = 1; i <= 100000; i++) {
            Usuario usuario = new Usuario("Usuario" + i, i);

            Solicitud solicitud = new Solicitud(
                    i,
                    LocalDate.of(2026, 9, 30),
                    usuario
            );

            sistema.agregarSolicitud(solicitud);
        }

        long fin100 = System.nanoTime();

        System.out.println(
                "100 solicitudes: " + (fin100 - inicio100) + " nanosegundos"
        );

        long memoriaDespues = runtime.totalMemory() - runtime.freeMemory();

        long memoriaUsada = memoriaDespues - memoriaAntes;

        System.out.println("Memoria utilizada: " + memoriaUsada + " bytes");


        // ---------------- MENÚ INTERACTIVO ----------------

        Scanner teclado = new Scanner(System.in);

        int numero = 0;

        while (numero != 5) {

            System.out.println("\n===== SISTEMA DE TAXIS =====");
            System.out.println("1. Agregar solicitud");
            System.out.println("2. Atender solicitud");
            System.out.println("3. Cancelar solicitud");
            System.out.println("4. Mostrar solicitudes pendientes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            numero = teclado.nextInt();

            switch (numero) {

                case 1:

                    int idSolicitud;

                    while (true) {

                        System.out.print("Ingrese el ID de la solicitud: ");
                        idSolicitud = teclado.nextInt();

                        if (!sistema.existeSolicitud(idSolicitud)) {
                            break;
                        }

                        System.out.println("Ese ID ya existe. Ingrese otro.");
                    }

                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = teclado.next();

                    System.out.print("Ingrese el ID del usuario: ");
                    int idUsuario = teclado.nextInt();

                    Usuario usuario = new Usuario(nombre, idUsuario);

                    Solicitud solicitud = new Solicitud(
                            idSolicitud,
                            LocalDate.now(),
                            usuario
                    );

                    sistema.agregarSolicitud(solicitud);

                    System.out.println("Solicitud agregada correctamente.");

                    break;

                case 2:
                    Solicitud atendida = sistema.atenderSolicitud();

                    if (atendida != null) {
                        System.out.println("Solicitud atendida:");
                        System.out.println(atendida);
                    } else {
                        System.out.println("No hay solicitudes pendientes.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la solicitud a cancelar: ");
                    int idCancelar = teclado.nextInt();

                    boolean cancelada = sistema.cancelarSolicitud(idCancelar);

                    if (cancelada) {
                        System.out.println("Solicitud cancelada correctamente.");
                    } else {
                        System.out.println("No se encontró una solicitud con ese ID.");
                    }
                    break;

                case 4:
                    System.out.println("\nSolicitudes pendientes:");

                    sistema.consultarSolicitudesPendientes();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        teclado.close();
    }
}
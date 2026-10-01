package co.edu.uniquindio.Escenario1;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        pruebaFuncional();
        pruebaRendimiento();
    }


    private static void pruebaFuncional() {

        System.out.println("PRUEBA FUNCIONAL\n");

        RegistroPacientes registro =
                new RegistroPacientes();


        System.out.println("1. Registrando pacientes...");

        registro.registrarPaciente(
                "Ana",
                "1001",
                4
        );

        registro.registrarPaciente(
                "Luis",
                "1002",
                1
        );

        registro.registrarPaciente(
                "Marta",
                "1003",
                3
        );

        registro.registrarPaciente(
                "Pedro",
                "1004",
                1
        );

        System.out.println(
                "   Pacientes registrados: " +
                        registro.cantidadPacientes()
        );


        System.out.println(
                "\n2. Intentando registrar a Ana otra vez..."
        );

        boolean aceptado =
                registro.registrarPaciente(
                        "Ana",
                        "1001",
                        4
                );

        System.out.println(
                "   ¿Se aceptó? " +
                        aceptado
        );


        System.out.println(
                "\n3. Pacientes en orden de llegada:"
        );

        List<Paciente> lista =
                registro.listarPorLlegada();

        for (Paciente paciente : lista) {

            System.out.println(
                    "   " + paciente
            );
        }


        System.out.println(
                "\n4. Buscando cédula 1003..."
        );

        Paciente encontrado =
                registro.buscarPorCedulaPaciente(
                        "1003"
                );

        System.out.println(
                "   Encontrado: " +
                        encontrado
        );

        System.out.println(
                "   Buscando cédula 9999: " +
                        registro.buscarPorCedulaPaciente(
                                "9999"
                        )
        );


        System.out.println(
                "\n5. Orden de atención por gravedad:"
        );

        while (registro.cantidadPacientes() > 0) {

            System.out.println(
                    "   Atendiendo a: " +
                            registro.atenderPorGravedad()
            );
        }
    }


    private static void pruebaRendimiento() {

        System.out.println(
                "\nPARTE 2: PRUEBA DE RENDIMIENTO\n"
        );

        System.out.println(
                "Calentando la JVM, espere un momento...\n"
        );

        MedidorRendimiento medidor =
                new MedidorRendimiento();

        medidor.calentarJVM();

        imprimirEncabezado();


        int[] tamanos =
                {
                        100,
                        1_000,
                        10_000,
                        100_000,
                        1_000_000
                };


        for (int n : tamanos) {

            int repeticiones =
                    (n >= 1_000_000) ? 3 : 5;

            imprimirFila(
                    "HashMap+Deque+PQ",
                    n,
                    medidor.medirConMediana(
                            true,
                            n,
                            repeticiones
                    )
            );
        }


        System.out.println(
                "\nComparación con la versión que usa solo ArrayList:"
        );


        int[] tamanosLista =
                {
                        100,
                        1_000,
                        10_000,
                        100_000
                };


        for (int n : tamanosLista) {

            int repeticiones =
                    (n >= 100_000) ? 1 : 5;

            imprimirFila(
                    "Solo ArrayList",
                    n,
                    medidor.medirConMediana(
                            false,
                            n,
                            repeticiones
                    )
            );
        }
    }


    private static void imprimirEncabezado() {

        System.out.printf(
                "%-18s %10s %16s %15s %16s %14s%n",
                "Estructura",
                "n",
                "Registro (ms)",
                "Búsqueda (µs)",
                "Duplicado (µs)",
                "Memoria (MB)"
        );

        System.out.println(
                "-".repeat(94)
        );
    }


    private static void imprimirFila(
            String nombre,
            int n,
            double[] r
    ) {

        System.out.printf(
                "%-18s %10d %16.3f %15.3f %16.3f %14.2f%n",
                nombre,
                n,
                r[0],
                r[1],
                r[2],
                r[3]
        );
    }
}
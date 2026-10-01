package co.edu.uniquindio.Escenario1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MedidorRendimiento {

    private final Random rnd =
            new Random(42);


    private List<String> generarCedulas(int n) {

        Set<String> set =
                new LinkedHashSet<>();

        while (set.size() < n) {

            set.add(
                    String.valueOf(
                            10_000_000 +
                                    rnd.nextInt(90_000_000)
                    )
            );
        }

        return new ArrayList<>(set);
    }


    private long memoriaUsada() {

        Runtime rt =
                Runtime.getRuntime();

        for (int i = 0; i < 3; i++) {

            System.gc();
        }

        return rt.totalMemory()
                - rt.freeMemory();
    }


    private List<String> muestra(
            List<String> cedulas,
            int k
    ) {

        List<String> copia =
                new ArrayList<>(cedulas);

        Collections.shuffle(
                copia,
                rnd
        );

        return copia.subList(
                0,
                Math.min(k, copia.size())
        );
    }


    public double[] medirSolucionPropuesta(int n) {

        List<String> cedulas =
                generarCedulas(n);

        long memAntes =
                memoriaUsada();


        RegistroPacientes registro =
                new RegistroPacientes();


        long inicio =
                System.nanoTime();


        for (String cedula : cedulas) {

            registro.registrarPaciente(
                    "Paciente " + cedula,
                    cedula,
                    1 + rnd.nextInt(5)
            );
        }


        double tRegistro =
                (System.nanoTime() - inicio)
                        / 1e6;


        double memoria =
                (memoriaUsada() - memAntes)
                        / (1024.0 * 1024.0);


        List<String> muestra =
                muestra(
                        cedulas,
                        1000
                );


        inicio =
                System.nanoTime();


        for (String cedula : muestra) {

            registro.buscarPorCedulaPaciente(
                    cedula
            );
        }


        double tBusqueda =
                (System.nanoTime() - inicio)
                        / 1e3
                        / muestra.size();


        inicio =
                System.nanoTime();


        for (String cedula : muestra) {

            registro.registrarPaciente(
                    "Duplicado",
                    cedula,
                    5
            );
        }


        double tDuplicado =
                (System.nanoTime() - inicio)
                        / 1e3
                        / muestra.size();


        return new double[]{
                tRegistro,
                tBusqueda,
                tDuplicado,
                Math.max(0, memoria)
        };
    }


    public double[] medirSoloLista(int n) {

        List<String> cedulas =
                generarCedulas(n);

        long memAntes =
                memoriaUsada();


        RegistroSoloLista registro =
                new RegistroSoloLista();


        long inicio =
                System.nanoTime();


        for (String cedula : cedulas) {

            registro.registrarPaciente(
                    "Paciente " + cedula,
                    cedula,
                    1 + rnd.nextInt(5)
            );
        }


        double tRegistro =
                (System.nanoTime() - inicio)
                        / 1e6;


        double memoria =
                (memoriaUsada() - memAntes)
                        / (1024.0 * 1024.0);


        List<String> muestra =
                muestra(
                        cedulas,
                        200
                );


        inicio =
                System.nanoTime();


        for (String cedula : muestra) {

            registro.buscarPorCedulaPaciente(
                    cedula
            );
        }


        double tBusqueda =
                (System.nanoTime() - inicio)
                        / 1e3
                        / muestra.size();


        inicio =
                System.nanoTime();


        for (String cedula : muestra) {

            registro.registrarPaciente(
                    "Duplicado",
                    cedula,
                    5
            );
        }


        double tDuplicado =
                (System.nanoTime() - inicio)
                        / 1e3
                        / muestra.size();


        return new double[]{
                tRegistro,
                tBusqueda,
                tDuplicado,
                Math.max(0, memoria)
        };
    }


    public double[] medirConMediana(
            boolean propuesta,
            int n,
            int repeticiones
    ) {

        double[][] resultados =
                new double[repeticiones][];


        for (int i = 0;
             i < repeticiones;
             i++) {

            if (propuesta) {

                resultados[i] =
                        medirSolucionPropuesta(n);

            } else {

                resultados[i] =
                        medirSoloLista(n);
            }
        }


        double[] mediana =
                new double[4];


        for (int j = 0;
             j < 4;
             j++) {

            double[] columna =
                    new double[repeticiones];


            for (int i = 0;
                 i < repeticiones;
                 i++) {

                columna[i] =
                        resultados[i][j];
            }


            Arrays.sort(columna);


            mediana[j] =
                    Math.max(
                            0,
                            columna[
                                    repeticiones / 2
                                    ]
                    );
        }


        return mediana;
    }


    public void calentarJVM() {

        for (int i = 0; i < 5; i++) {

            medirSolucionPropuesta(
                    10_000
            );

            medirSoloLista(
                    2_000
            );
        }
    }
}
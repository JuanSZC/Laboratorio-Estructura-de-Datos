package co.edu.uniquindio.Escenario2;

import java.util.List;
import java.util.Random;

public class Main {

    private static final String[] CATEGORIAS =
            {
                    "Electronica",
                    "Hogar",
                    "Ropa",
                    "Juguetes",
                    "Deportes"
            };


    public static void main(String[] args) {

        int[] tamanos =
                {
                        100,
                        1000,
                        10000,
                        100000
                };

        Random random =
                new Random(42);

        System.out.println();


        for (int n : tamanos) {

            CatalogoProductos catalogo =
                    new CatalogoProductos();

            Runtime runtime =
                    Runtime.getRuntime();


            runtime.gc();

            long memAntes =
                    runtime.totalMemory()
                            - runtime.freeMemory();


            long inicioInsercion =
                    System.nanoTime();


            for (int i = 0; i < n; i++) {

                String codigo =
                        "PROD-" + i;

                String categoria =
                        CATEGORIAS[
                                random.nextInt(
                                        CATEGORIAS.length
                                )
                                ];

                double precio =
                        Math.round(
                                (
                                        1000 +
                                                random.nextDouble() * 9000
                                ) * 100.0
                        ) / 100.0;


                Producto producto =
                        new Producto(
                                codigo,
                                "Producto " + i,
                                precio,
                                categoria
                        );

                catalogo.agregarProducto(producto);
            }


            long finInsercion =
                    System.nanoTime();

            long memDespues =
                    runtime.totalMemory()
                            - runtime.freeMemory();


            int busquedas =
                    Math.min(1000, n);


            long inicioBusqueda =
                    System.nanoTime();


            for (int i = 0; i < busquedas; i++) {

                String codigoBuscado =
                        "PROD-" +
                                random.nextInt(n);

                catalogo.buscarPorCodigo(
                        codigoBuscado
                );
            }


            long finBusqueda =
                    System.nanoTime();


            long inicioFiltro =
                    System.nanoTime();


            List<Producto> filtrados =
                    catalogo.filtrarPorCategoria(
                            "Electronica"
                    );


            long finFiltro =
                    System.nanoTime();


            long inicioOrden =
                    System.nanoTime();


            catalogo.listarOrdenadoPorPrecio();


            long finOrden =
                    System.nanoTime();


            double msInsercion =
                    (
                            finInsercion -
                                    inicioInsercion
                    ) / 1_000_000.0;


            double msBusquedaProm =
                    (
                            finBusqueda -
                                    inicioBusqueda
                    ) / 1_000_000.0 / busquedas;


            double msFiltro =
                    (
                            finFiltro -
                                    inicioFiltro
                    ) / 1_000_000.0;


            double msOrden =
                    (
                            finOrden -
                                    inicioOrden
                    ) / 1_000_000.0;


            double memUsadaMB =
                    (
                            memDespues -
                                    memAntes
                    ) / (1024.0 * 1024.0);


            System.out.printf(
                    "N = %,d productos%n",
                    n
            );

            System.out.printf(
                    "  Inserción total:          %10.3f ms  (%.6f ms/producto)%n",
                    msInsercion,
                    msInsercion / n
            );

            System.out.printf(
                    "  Búsqueda por código:      %10.6f ms/promedio%n",
                    msBusquedaProm
            );

            System.out.printf(
                    "  Filtrar por categoría:    %10.3f ms  (%,d resultados)%n",
                    msFiltro,
                    filtrados.size()
            );

            System.out.printf(
                    "  Listar ordenado (precio): %10.3f ms%n",
                    msOrden
            );

            System.out.printf(
                    "  Memoria aproximada:       %10.2f MB%n",
                    memUsadaMB
            );

            System.out.println();
        }


        System.out.println(
                "Nota: la medición de memoria con Runtime es aproximada,"
        );

        System.out.println(
                "porque depende de cuándo corre el Garbage Collector."
        );
    }
}
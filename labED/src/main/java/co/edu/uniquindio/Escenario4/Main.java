package co.edu.uniquindio.Escenario4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, Producto> productos = new HashMap<>();
    static TreeMap<Integer, ArrayList<String>> ordenPrecio = new TreeMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bienvenido al sistema de productos de E-commerce");

        int sel = 0;

        while (sel != 8) {

            System.out.println("\nSelecciona lo que deseas hacer:");
            System.out.println("\t1. Agregar producto");
            System.out.println("\t2. Consultar por código");
            System.out.println("\t3. Mostrar productos ordenados por precio");
            System.out.println("\t4. Actualizar producto");
            System.out.println("\t5. Eliminar producto");
            System.out.println("\t6. Mostrar todos los productos");
            System.out.println("\t7. Realizar pruebas de rendimiento");
            System.out.println("\t8. Salir");

            try {
                sel = sc.nextInt();

                if (sel < 1 || sel > 8) {
                    System.out.println("Selección inválida");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida");
                sc.nextLine();
                continue;
            }

            switch (sel) {

                case 1:
                    registrar();
                    break;

                case 2:
                    consultar();
                    break;

                case 3:
                    mostrarOrdenPrecio();
                    break;

                case 4:
                    actualizar();
                    break;

                case 5:
                    eliminar();
                    break;

                case 6:
                    mostrarTodos();
                    break;

                case 7:
                    pruebasRendimiento();
                    break;

                case 8:
                    System.out.println("Programa finalizado.");
                    break;
            }
        }
    }

    public static void registrar() {

        System.out.println("Ingresa el código del producto: ");
        String cod = sc.next();

        if (productos.containsKey(cod)) {
            System.out.println("Código ya existente");
            return;
        }

        System.out.println("Ingresa el nombre del producto: ");
        String nombre = sc.next();

        int precio = 0;

        while (true) {

            System.out.println("Ingresa el precio del producto: ");

            try {
                precio = sc.nextInt();

                if (precio < 0) {
                    System.out.println("Precio inválido");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida");
                sc.nextLine();
            }
        }

        Producto p = new Producto(cod, nombre, precio);

        productos.put(cod, p);

        if (!ordenPrecio.containsKey(precio)) {
            ordenPrecio.put(precio, new ArrayList<>());
        }

        ordenPrecio.get(precio).add(cod);

        System.out.println("Producto agregado exitosamente");
    }

    public static void consultar() {

        System.out.println("Ingresa el código del producto: ");
        String cod = sc.next();

        if (productos.containsKey(cod)) {

            Producto p = productos.get(cod);

            System.out.println("Código: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Precio: " + p.getPrecio());

        } else {
            System.out.println("Código no encontrado");
        }
    }

    public static void mostrarOrdenPrecio() {

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\nProductos ordenados por precio:");

        for (Integer precio : ordenPrecio.keySet()) {

            ArrayList<String> codigos = ordenPrecio.get(precio);

            for (String cod : codigos) {

                Producto p = productos.get(cod);

                System.out.println("Código: " + p.getCodigo());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("-------------------------");
            }
        }
    }

    public static void actualizar() {

        System.out.println("Ingresa el código del producto que deseas actualizar: ");
        String cod = sc.next();

        if (!productos.containsKey(cod)) {
            System.out.println("Código no encontrado");
            return;
        }

        Producto p = productos.get(cod);

        int precioAnterior = p.getPrecio();

        System.out.println("Ingresa el nuevo nombre: ");
        String nombre = sc.next();

        int nuevoPrecio = 0;

        while (true) {

            System.out.println("Ingresa el nuevo precio: ");

            try {
                nuevoPrecio = sc.nextInt();

                if (nuevoPrecio < 0) {
                    System.out.println("Precio inválido");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida");
                sc.nextLine();
            }
        }

        if (precioAnterior != nuevoPrecio) {

            ArrayList<String> listaAnterior = ordenPrecio.get(precioAnterior);

            listaAnterior.remove(cod);

            if (listaAnterior.isEmpty()) {
                ordenPrecio.remove(precioAnterior);
            }

            if (!ordenPrecio.containsKey(nuevoPrecio)) {
                ordenPrecio.put(nuevoPrecio, new ArrayList<>());
            }

            ordenPrecio.get(nuevoPrecio).add(cod);
        }

        p.setNombre(nombre);
        p.setPrecio(nuevoPrecio);

        System.out.println("Producto actualizado exitosamente");
    }

    public static void eliminar() {

        System.out.println("Ingresa el código del producto que deseas eliminar: ");
        String cod = sc.next();

        if (!productos.containsKey(cod)) {
            System.out.println("Código no encontrado");
            return;
        }

        Producto p = productos.get(cod);

        int precio = p.getPrecio();

        ArrayList<String> lista = ordenPrecio.get(precio);

        lista.remove(cod);

        if (lista.isEmpty()) {
            ordenPrecio.remove(precio);
        }

        productos.remove(cod);

        System.out.println("Producto eliminado exitosamente");
    }

    public static void mostrarTodos() {

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\nTodos los productos:");

        for (String cod : productos.keySet()) {

            Producto p = productos.get(cod);

            System.out.println("Código: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Precio: " + p.getPrecio());
            System.out.println("-------------------------");
        }
    }

    public static void pruebasRendimiento() {

        int[] cantidades = {100, 1000, 10000, 100000};

        for (int n : cantidades) {

            HashMap<String, Producto> lista = new HashMap<>();
            TreeMap<Integer, ArrayList<String>> orden = new TreeMap<>();

            Runtime memoria = Runtime.getRuntime();
            System.gc();
            long memoriaInicio = memoria.totalMemory() - memoria.freeMemory();
            long inicioInsercion = System.nanoTime();

            for (int i = 0; i < n; i++) {

                String cod = "P" + i;
                String nombre = "Producto" + i;
                int precio = 1000 + (i % 1000);

                Producto p = new Producto(cod, nombre, precio);

                lista.put(cod, p);

                if (!orden.containsKey(precio)) {
                    orden.put(precio, new ArrayList<>());
                }

                orden.get(precio).add(cod);
            }

            long finInsercion = System.nanoTime();

            long inicioBusqueda = System.nanoTime();

            for (int i = 0; i < n; i++) {

                String cod = "P" + i;

                Producto p = lista.get(cod);
            }

            long finBusqueda = System.nanoTime();
            long inicioRecorrido = System.nanoTime();

            for (Integer precio : orden.keySet()) {

                ArrayList<String> codigos = orden.get(precio);

                for (String cod : codigos) {

                    Producto p = lista.get(cod);
                }
            }

            long finRecorrido = System.nanoTime();

            System.gc();

            long memoriaFinal = memoria.totalMemory() - memoria.freeMemory();

            long tiempoInsercion = finInsercion - inicioInsercion;
            long tiempoBusqueda = finBusqueda - inicioBusqueda;
            long tiempoRecorrido = finRecorrido - inicioRecorrido;

            long memoriaUsada = memoriaFinal - memoriaInicio;

            System.out.println("\nCantidad de productos: " + n);
            System.out.println("Tiempo de inserción: " + tiempoInsercion / 1000000.0 + " ms");
            System.out.println("Tiempo de búsqueda: " + tiempoBusqueda / 1000000.0 + " ms");
            System.out.println("Tiempo de recorrido ordenado: " + tiempoRecorrido / 1000000.0 + " ms");
            System.out.println("Memoria aproximada: " + memoriaUsada / 1024.0 + " KB");
        }
    }
}
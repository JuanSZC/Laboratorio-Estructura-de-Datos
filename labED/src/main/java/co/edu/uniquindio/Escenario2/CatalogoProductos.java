package co.edu.uniquindio.Escenario2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CatalogoProductos {

    private int totalProductos = 0;

    private final LinkedList<Producto> ordenIngreso = new LinkedList<>();

    private final Map<String, Producto> porCodigo = new HashMap<>();

    private final TreeMap<Double, List<Producto>> porPrecio = new TreeMap<>();

    private final Map<String, List<Producto>> porCategoria = new HashMap<>();


    public void agregarProducto(Producto producto) {

        porCodigo.put(producto.getCodigo(), producto);

        ordenIngreso.addFirst(producto);


        if (!porPrecio.containsKey(producto.getPrecio())) {

            porPrecio.put(
                    producto.getPrecio(),
                    new ArrayList<>()
            );
        }

        porPrecio
                .get(producto.getPrecio())
                .add(producto);


        if (!porCategoria.containsKey(producto.getCategoria())) {

            porCategoria.put(
                    producto.getCategoria(),
                    new ArrayList<>()
            );
        }

        porCategoria
                .get(producto.getCategoria())
                .add(producto);

        totalProductos++;
    }


    public List<Producto> listarOrdenIngreso() {

        return Collections.unmodifiableList(
                ordenIngreso
        );
    }


    public Producto buscarPorCodigo(String codigo) {

        return porCodigo.get(codigo);
    }


    public List<Producto> listarOrdenadoPorPrecio() {

        List<Producto> resultado =
                new ArrayList<>();

        for (Double precio : porPrecio.keySet()) {

            List<Producto> productos =
                    porPrecio.get(precio);

            for (Producto producto : productos) {

                resultado.add(producto);
            }
        }

        return resultado;
    }


    public List<Producto> filtrarPorCategoria(String categoria) {

        if (porCategoria.containsKey(categoria)) {

            return porCategoria.get(categoria);
        }

        return new ArrayList<>();
    }


    public int size() {

        return totalProductos;
    }
}
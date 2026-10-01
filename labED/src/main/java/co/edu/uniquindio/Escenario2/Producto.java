package co.edu.uniquindio.Escenario2;

import java.time.LocalDateTime;

public class Producto {

    private final String codigo;
    private final String nombre;
    private final double precio;
    private final String categoria;
    private final LocalDateTime fechaIngreso;

    public Producto(String codigo, String nombre, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaIngreso = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    @Override
    public String toString() {
        return codigo + " | " +
                nombre + " | $" +
                precio + " | " +
                categoria + " | " +
                fechaIngreso;
    }
}
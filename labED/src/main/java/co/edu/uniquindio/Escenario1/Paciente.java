package co.edu.uniquindio.Escenario1;

public class Paciente {

    String cedula;
    String nombre;
    int gravedad;
    long llegada;

    public Paciente(String nombre, String cedula, int gravedad, long llegada) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.gravedad = gravedad;
        this.llegada = llegada;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGravedad() {
        return gravedad;
    }

    public long getLlegada() {
        return llegada;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", gravedad=" + gravedad +
                ", llegada=" + llegada +
                '}';
    }
}
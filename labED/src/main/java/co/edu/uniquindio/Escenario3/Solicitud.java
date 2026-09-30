package co.edu.uniquindio.Escenario3;

import java.time.LocalDate;

public class Solicitud {
    private int solicitudId;
    private LocalDate solicitudFecha;
    private Usuario usuario;


    public Solicitud(int solicitudId,  LocalDate solicitudFecha,  Usuario usuario) {
        this.solicitudId = solicitudId;
        this.solicitudFecha = solicitudFecha;
        this.usuario = usuario;

    }

    public int getSolicitudId() {
        return solicitudId;
    }
    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public LocalDate getSolicitudFecha() {
        return solicitudFecha;
    }
    public void setSolicitudFecha(LocalDate solicitudFecha) {
        this.solicitudFecha = solicitudFecha;
    }

    @Override
    public String toString() {
        return "Id: " + solicitudId +
                "\nUsuario: " + usuario.getNombre() +
                "\nFecha: " + solicitudFecha;
    }
}
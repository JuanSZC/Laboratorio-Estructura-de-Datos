package co.edu.uniquindio.Escenario3;

import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class SistemaTaxi {

    private Queue<Solicitud> solicitudes;

    public SistemaTaxi() {
        solicitudes = new LinkedList<>();
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudes.offer(solicitud);
    }

    public Solicitud atenderSolicitud() {
        if (solicitudes.isEmpty()) {
            return null;
        }
        return solicitudes.poll();
    }

    public boolean cancelarSolicitud(int id){
        Iterator<Solicitud> iterator = solicitudes.iterator();
        while (iterator.hasNext()) {
            Solicitud solicitud = iterator.next();
            if (solicitud.getSolicitudId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void consultarSolicitudesPendientes() {
        for (Solicitud solicitud : solicitudes) {
            System.out.println(solicitud);
            System.out.println();
        }
    }

    public boolean existeSolicitud(int id) {
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getSolicitudId() == id) {
                return true;
            }
        }

        return false;
    }
}
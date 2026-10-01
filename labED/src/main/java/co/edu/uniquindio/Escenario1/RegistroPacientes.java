package co.edu.uniquindio.Escenario1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class RegistroPacientes {

    private final HashMap<String, Paciente> porDocumento = new HashMap<>();
    private final ArrayDeque<Paciente> porLlegada = new ArrayDeque<>();

    private final PriorityQueue<Paciente> porGravedad =
            new PriorityQueue<>((p1, p2) -> {

                if (p1.getGravedad() != p2.getGravedad()) {
                    return Integer.compare(
                            p1.getGravedad(),
                            p2.getGravedad()
                    );
                }

                return Long.compare(
                        p1.getLlegada(),
                        p2.getLlegada()
                );
            });

    private long contadorLlegada = 0;


    public boolean registrarPaciente(
            String nombre,
            String cedula,
            int gravedad
    ) {

        if (porDocumento.containsKey(cedula)) {
            return false;
        }

        contadorLlegada++;

        Paciente paciente =
                new Paciente(
                        nombre,
                        cedula,
                        gravedad,
                        contadorLlegada
                );

        porDocumento.put(cedula, paciente);
        porLlegada.addLast(paciente);
        porGravedad.add(paciente);

        return true;
    }


    public Paciente buscarPorCedulaPaciente(String cedula) {

        return porDocumento.get(cedula);
    }


    public boolean existe(String cedula) {

        return porDocumento.containsKey(cedula);
    }


    public List<Paciente> listarPorLlegada() {

        List<Paciente> lista =
                new ArrayList<>();

        for (Paciente paciente : porLlegada) {

            lista.add(paciente);
        }

        return lista;
    }


    public Paciente atenderPorLlegada() {

        if (porLlegada.isEmpty()) {
            return null;
        }

        Paciente paciente =
                porLlegada.poll();

        porDocumento.remove(
                paciente.getCedula()
        );

        porGravedad.remove(paciente);

        return paciente;
    }


    public Paciente atenderPorGravedad() {

        if (porGravedad.isEmpty()) {
            return null;
        }

        Paciente paciente =
                porGravedad.poll();

        porDocumento.remove(
                paciente.getCedula()
        );

        porLlegada.remove(paciente);

        return paciente;
    }


    public int cantidadPacientes() {

        return porDocumento.size();
    }
}
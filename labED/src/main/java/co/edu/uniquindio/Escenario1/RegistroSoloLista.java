package co.edu.uniquindio.Escenario1;

import java.util.ArrayList;
import java.util.List;

public class RegistroSoloLista {

    private final List<Paciente> pacientes =
            new ArrayList<>();


    public boolean registrarPaciente(
            String nombre,
            String cedula,
            int gravedad
    ) {

        for (Paciente paciente : pacientes) {

            if (paciente.getCedula().equals(cedula)) {
                return false;
            }
        }

        Paciente paciente =
                new Paciente(
                        nombre,
                        cedula,
                        gravedad,
                        pacientes.size() + 1
                );

        pacientes.add(paciente);

        return true;
    }


    public Paciente buscarPorCedulaPaciente(String cedula) {

        for (Paciente paciente : pacientes) {

            if (paciente.getCedula().equals(cedula)) {
                return paciente;
            }
        }

        return null;
    }


    public boolean existe(String cedula) {

        for (Paciente paciente : pacientes) {

            if (paciente.getCedula().equals(cedula)) {
                return true;
            }
        }

        return false;
    }
}
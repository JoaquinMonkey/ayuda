package excepciones;

@SuppressWarnings("serial")
public class PacienteYaReservadoException extends Exception {
	public PacienteYaReservadoException() {
		super("El paciente ya tiene una reserva para esta consulta");
	}
}

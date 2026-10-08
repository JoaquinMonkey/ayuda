package excepciones;

@SuppressWarnings("serial")
public class MedicoSinEspecialidadException extends Exception {
	public MedicoSinEspecialidadException(String mensaje) {
		super(mensaje);
	}
}

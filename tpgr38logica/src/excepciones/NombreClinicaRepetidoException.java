package excepciones;

@SuppressWarnings("serial")
public class NombreClinicaRepetidoException extends Exception {

	public NombreClinicaRepetidoException(String mensaje) {
		super(mensaje);
	}
}

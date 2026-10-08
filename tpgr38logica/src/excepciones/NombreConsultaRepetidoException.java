package excepciones;

@SuppressWarnings("serial")
public class NombreConsultaRepetidoException extends Exception {
	public NombreConsultaRepetidoException(String mensaje) {
		super(mensaje);
	}
}

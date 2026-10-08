package excepciones;

@SuppressWarnings("serial")
public class NombreEspecialidadRepetidoException extends RuntimeException {
	public NombreEspecialidadRepetidoException(String msg) {
		super(msg);
	}
}

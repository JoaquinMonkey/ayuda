package excepciones;

@SuppressWarnings("serial")
public class EspecialidadNoExisteException extends RuntimeException {
	public EspecialidadNoExisteException(String msg) {
		super(msg);
	}

}

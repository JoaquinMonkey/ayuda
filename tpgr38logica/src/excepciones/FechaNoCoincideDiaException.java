package excepciones;

@SuppressWarnings("serial")
public class FechaNoCoincideDiaException extends Exception {
	public FechaNoCoincideDiaException(String mensaje) {
		super(mensaje);
	}
}

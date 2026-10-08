package excepciones;

@SuppressWarnings("serial")
public class ReservaFueraDeFechaException extends Exception {
	public ReservaFueraDeFechaException() {
		super("La consulta a reservar ya ocurrio");
	}
}
